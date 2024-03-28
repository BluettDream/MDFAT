package org.bluett.core.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bluett.core.operation.AutomaticOperation;
import org.bluett.entity.dto.ImageProcessDTO;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestImageVO;
import org.bluett.helper.TestCaseCallableHelper;
import org.bluett.service.ImageProcessService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

@Log4j2
@RequiredArgsConstructor
public class TestCaseExecutor implements Callable<ImageProcessDTO>, Supplier<ImageProcessDTO> {
    private final TestCaseVO testCaseVO;
    private final ImageProcessService imageProcessService;
    private final AutomaticOperation automaticOperation;

    @Override
    public ImageProcessDTO call() throws Exception {
        TestCaseCallableHelper.RUNNING_TEST_CASE_COUNT.getAndIncrement();
        TestImageVO imageVO = testCaseVO.getImageVO();
        if(Objects.isNull(imageVO)){
            throw new Exception("ImageVO is null");
        }

        if(StringUtils.isNotBlank(imageVO.getPath())){
            BufferedImage screenCapture = automaticOperation.screenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            return imageProcessService.getMatchLocation(imageVO.getPath(), screenCapture, imageVO.getConfidence());
        }
        return null;
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
