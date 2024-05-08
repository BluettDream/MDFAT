package org.bluett.core.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
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
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestImageVO;
import org.bluett.entity.vo.TestTextVO;
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

    private final ImageRecognizer imageRecognizer = new ImageRecognizer();
    private final TextRecognizer textRecognizer = new TextRecognizer();
    private final TestImageService imageService = new TestImageService();
    private final TestTextService textService = new TestTextService();
    private final AutomaticOperator automaticOperator = new PCAutoMaticOperatorImpl();

    @Override
    public Boolean get() {
        List<TestCaseDTO> caseDtoList = buildTestCaseDTOList();
        StopWatch stopwatch = StopWatch.createStarted();
        List<CompletableFuture<TestCaseDTO>> caseDTOFutureList = caseDtoList.stream()
                .map(this::buildTestCaseDTOCompletableFuture)
                .toList();
        CompletableFuture.allOf(caseDTOFutureList.toArray(new CompletableFuture[0]))
                .orTimeout(caseDtoList.getFirst().getRunTime() + caseDtoList.getFirst().getTimeout(), TimeUnit.SECONDS)
                .join();
        // TODO: 过滤掉失败的用例，并且排序获取优先级最高的用例，执行对应测试用例的操作
        // TODO: 判断是否有下一个测试用例，如果有则发送testCaseExecutor事件，否则发送testSuiteExecutor事件
        return true;
    }

    private CompletableFuture<TestCaseDTO> buildTestCaseDTOCompletableFuture(TestCaseDTO dto) {
        return CompletableFuture.supplyAsync(() -> {
            boolean success = false;
            if (Objects.nonNull(dto.getTestImageDTO())) {
                TestImageDTO imageDTO = dto.getTestImageDTO();
                captureScreenAndSaveImage();
                RecognitionResp resp = imageRecognizer.recongnize(buildImageRecognitionReq(imageDTO));
                imageDTO.setResp(resp);
                success = resp.getSuccess();
            }
            if (Objects.nonNull(dto.getTestTextDTO())) {
                TestTextDTO textDTO = dto.getTestTextDTO();
                RecognitionResp resp = textRecognizer.recongnize(buildTextRecognitionReq(textDTO));
                textDTO.setResp(resp);
                success = success || resp.getSuccess();
            }
            dto.setSuccess(success);
            return dto;
        }, caseThreadPool);
    }

    private TextRecognitionReq buildTextRecognitionReq(TestTextDTO textDTO) {
        return TextRecognitionReq.builder()
                .text(textDTO.getText())
                .confidence(textDTO.getConfidence())
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
