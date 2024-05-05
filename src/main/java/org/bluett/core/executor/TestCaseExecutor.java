package org.bluett.core.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bluett.core.operation.AutomaticOperation;
import org.bluett.entity.BaseConstants;
import org.bluett.entity.dto.ImageProcessDTO;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestImageVO;
import org.bluett.helper.TestCaseCallableHelper;
import org.bluett.service.ImageProcessService;
import org.bluett.service.TestImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

@Log4j2
@RequiredArgsConstructor
public class TestCaseExecutor implements Callable<ImageProcessDTO>, Supplier<ImageProcessDTO> {
    private final TestCaseVO testCaseVO;
    private final ImageProcessService imageProcessService;
    private final AutomaticOperation automaticOperation;
    private final TestImageService imageService = new TestImageService();

    private final static Path SCREEN_CAPTURE_PATH = BaseConstants.BASE_PATH.resolve("img").resolve("screenCapture.png");

    @Override
    public ImageProcessDTO call() throws Exception {
        TestCaseCallableHelper.RUNNING_TEST_CASE_COUNT.getAndIncrement();
        TestImageVO imageVO = imageService.selectByCaseId(testCaseVO.getId());
        if(Objects.isNull(imageVO)){
            throw new Exception("ImageVO is null");
        }
        BufferedImage screenCapture = automaticOperation.screenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(screenCapture, "png", FileUtils.toFile(SCREEN_CAPTURE_PATH.toUri().toURL()));
        return imageProcessService.getMatchLocation(imageVO.getLink(), screenCapture, imageVO.getConfidence());
    }

    @Override
    public ImageProcessDTO get() {
        try {
            return call();
        } catch (Exception e) {
            log.error("Error in TestCaseExecutor:", ExceptionUtils.getRootCause(e));
        }
        return null;
    }
}
