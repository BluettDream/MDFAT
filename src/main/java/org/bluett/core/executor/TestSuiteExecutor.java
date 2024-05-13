package org.bluett.core.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.bluett.config.ThreadPoolConfig;
import org.bluett.core.operator.AutomaticOperator;
import org.bluett.core.operator.impl.PCAutoMaticOperatorImpl;
import org.bluett.core.recognizer.ImageRecognizer;
import org.bluett.core.recognizer.TextRecognizer;
import org.bluett.entity.BaseConstants;
import org.bluett.entity.dto.ImageRecognitionReq;
import org.bluett.entity.dto.RecognitionResp;
import org.bluett.entity.dto.TestCaseDTO;
import org.bluett.entity.dto.TestImageDTO;
import org.bluett.entity.dto.TestTextDTO;
import org.bluett.entity.dto.TextRecognitionReq;
import org.bluett.entity.enums.TestCaseStatusEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestImageVO;
import org.bluett.entity.vo.TestTextVO;
import org.bluett.service.OperationService;
import org.bluett.service.TestCaseService;
import org.bluett.service.TestImageService;
import org.bluett.service.TestTextService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Log4j2
@RequiredArgsConstructor
public class TestSuiteExecutor implements Supplier<Boolean> {
    private final static Path SCREEN_CAPTURE_PATH = BaseConstants.BASE_PATH.resolve("img").resolve("screenCapture.png");
    private final static String PNG = "png";
    private final ThreadPoolExecutor caseThreadPool = ThreadPoolConfig.TEST_CASE_THREAD_POOL;
    private final List<TestCaseVO> testCaseVOList;
    private final Integer timeout;

    private final ImageRecognizer imageRecognizer = new ImageRecognizer();
    private final TextRecognizer textRecognizer = new TextRecognizer();
    private final TestImageService imageService = new TestImageService();
    private final TestTextService textService = new TestTextService();
    private final AutomaticOperator automaticOperator = new PCAutoMaticOperatorImpl();
    private final OperationService operationService = new OperationService();
    private final TestCaseService caseService = new TestCaseService();

    @Override
    public Boolean get() {
        List<TestCaseDTO> caseDtoList = buildTestCaseDTOList();
        StopWatch stopwatch = StopWatch.createStarted();
        while (!checkIsTimeout(stopwatch) && !Thread.currentThread().isInterrupted()) {
            log.debug("开始执行测试用例, 当前用时: {}s", stopwatch.getTime(TimeUnit.SECONDS));
            List<CompletableFuture<TestCaseDTO>> caseDTOFutureList = caseDtoList.stream()
                    .map(this::buildTestCaseDTOCompletableFuture)
                    .toList();
            Integer maxTotalTimeout = getMaxTestCaseTimeout(caseDtoList);
            try {
                CompletableFuture.allOf(caseDTOFutureList.toArray(new CompletableFuture[0]))
                        .orTimeout(maxTotalTimeout, TimeUnit.SECONDS)
                        .join();
            } catch (Exception e) {
                log.error("测试用例执行超时", e);
            }
            TestCaseDTO resultTestCase = getRecognizeAndMaxPriorityTestCase(caseDTOFutureList);
            if (Objects.isNull(resultTestCase)) {
                continue;
            }
            operationService.execute(resultTestCase);
            // 结束测试用例已经成功,则跳出循环
            if (caseDTOFutureList.stream()
                    .anyMatch(testCaseDTOCompletableFuture -> {
                        TestCaseDTO caseDTO = testCaseDTOCompletableFuture.join();
                        return caseDTO.getSuccess() && caseDTO.getStatus().equals(TestCaseStatusEnum.END.name());
                    })) {
                stopwatch.stop();
                return true;
            }
            Long nextId = resultTestCase.getNextId();
            while (nextId != -1L) {
                TestCaseVO testCaseVO = caseService.selectById(nextId);
                if (Objects.isNull(testCaseVO)) {
                    break;
                }
                Boolean result = new TestCaseExecutor(testCaseVO).get();
                if (!result) {
                    stopwatch.stop();
                    return false;
                }
                nextId = testCaseVO.getNextId();
            }
        }
        stopwatch.stop();
        return false;
    }

    private static TestCaseDTO getRecognizeAndMaxPriorityTestCase(List<CompletableFuture<TestCaseDTO>> caseDTOFutureList) {
        return caseDTOFutureList.stream()
                .map(CompletableFuture::join)
                .filter(TestCaseDTO::getSuccess)
                .min((o1, o2) -> o2.getPriority() - o1.getPriority())
                .orElse(null);
    }

    private Integer getMaxTestCaseTimeout(List<TestCaseDTO> caseDtoList) {
        return caseDtoList.stream()
                .map(testCaseDTO -> testCaseDTO.getRunTime() + testCaseDTO.getTimeout())
                .max(Integer::compareTo)
                .orElse(0);
    }

    private boolean checkIsTimeout(StopWatch stopwatch) {
        return stopwatch.getTime(TimeUnit.SECONDS) > timeout;
    }

    private CompletableFuture<TestCaseDTO> buildTestCaseDTOCompletableFuture(TestCaseDTO dto) {
        return CompletableFuture.supplyAsync(() -> {
            boolean success = false;
            if (Objects.nonNull(dto.getTestImageDTO()) && StringUtils.isNotBlank(dto.getTestImageDTO().getLink())) {
                TestImageDTO imageDTO = dto.getTestImageDTO();
                captureScreenAndSaveImage();
                RecognitionResp resp = imageRecognizer.recognize(buildImageRecognitionReq(imageDTO));
                if(Objects.nonNull(resp)){
                    imageDTO.setResp(resp);
                    success = resp.getSuccess();
                }
            }
            if (Objects.nonNull(dto.getTestTextDTO()) && StringUtils.isNotBlank(dto.getTestTextDTO().getText())) {
                TestTextDTO textDTO = dto.getTestTextDTO();
                RecognitionResp resp = textRecognizer.recognize(buildTextRecognitionReq(textDTO));
                if (Objects.nonNull(resp)) {
                    textDTO.setResp(resp);
                    success = success || resp.getSuccess();
                }
            }
            dto.setSuccess(success);
            return dto;
        }, caseThreadPool);
    }

    private TextRecognitionReq buildTextRecognitionReq(TestTextDTO textDTO) {
        return TextRecognitionReq.builder()
                .text(textDTO.getText())
                .confidence(textDTO.getConfidence())
                .captureLink(SCREEN_CAPTURE_PATH.toAbsolutePath().toString())
                .x(textDTO.getX())
                .y(textDTO.getY())
                .build();
    }

    private ImageRecognitionReq buildImageRecognitionReq(TestImageDTO imageDTO) {
        return ImageRecognitionReq.builder()
                .imageLink(imageDTO.getLink())
                .captureLink(SCREEN_CAPTURE_PATH.toAbsolutePath().toString())
                .confidence(imageDTO.getConfidence())
                .x(imageDTO.getX())
                .y(imageDTO.getY())
                .width(imageDTO.getWidth())
                .height(imageDTO.getHeight())
                .build();
    }

    private void captureScreenAndSaveImage() {
        BufferedImage screenCapture = null;
        try {
            screenCapture = automaticOperator.screenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(screenCapture, PNG, FileUtils.toFile(SCREEN_CAPTURE_PATH.toUri().toURL()));
        } catch (Exception e) {
            log.error("截屏并保存图片异常:", ExceptionUtils.getRootCause(e));
        }
    }

    private List<TestCaseDTO> buildTestCaseDTOList() {
        return testCaseVOList.stream()
                .map(testCaseVO -> TestCaseDTO.builder()
                        .id(testCaseVO.getId())
                        .name(testCaseVO.getName())
                        .suiteId(testCaseVO.getSuiteId())
                        .nextId(testCaseVO.getNextId())
                        .runTime(testCaseVO.getRunTime())
                        .timeout(testCaseVO.getTimeout())
                        .priority(testCaseVO.getPriority())
                        .status(testCaseVO.getStatus().name())
                        .success(false)
                        .testImageDTO(buildTestImageDTO(testCaseVO.getId()))
                        .testTextDTO(buildTestTextDTO(testCaseVO.getId()))
                        .build())
                .toList();
    }

    private TestTextDTO buildTestTextDTO(Long caseId) {
        TestTextVO textVO = textService.selectByCaseId(caseId);
        if (Objects.isNull(textVO)) {
            return null;
        }
        return TestTextDTO.builder()
                .id(textVO.getId())
                .text(textVO.getText())
                .confidence(textVO.getConfidence())
                .x(textVO.getX())
                .y(textVO.getY())
                .build();
    }

    private TestImageDTO buildTestImageDTO(Long caseId) {
        TestImageVO imageVO = imageService.selectByCaseId(caseId);
        if (Objects.isNull(imageVO)) {
            return null;
        }
        return TestImageDTO.builder()
                .id(imageVO.getId())
                .link(imageVO.getLink())
                .x(imageVO.getX())
                .y(imageVO.getY())
                .width(imageVO.getWidth())
                .height(imageVO.getHeight())
                .confidence(imageVO.getConfidence())
                .build();
    }
}
