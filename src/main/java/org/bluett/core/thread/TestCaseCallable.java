package org.bluett.core.thread;

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

@Log4j2
@RequiredArgsConstructor
public class TestCaseCallable implements Callable<ImageProcessDTO> {
    private final TestCaseVO testCaseVO;
    private final ImageProcessService imageProcessService;
    private final AutomaticOperation automaticOperation;

    @Override
    public ImageProcessDTO call() {
        TestCaseCallableHelper.RUNNING_TEST_CASE_COUNT.getAndIncrement();
        try {
            TestImageVO imageVO = testCaseVO.getImageVO();
            if(Objects.nonNull(imageVO) && StringUtils.isNotBlank(imageVO.getPath())){
                BufferedImage screenCapture = automaticOperation.screenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                return imageProcessService.getMatchLocation(imageVO.getPath(), screenCapture, imageVO.getConfidence());
            }
            return null;
        }catch (Exception e){
            log.error(ExceptionUtils.getRootCause(e));
        } finally {
            TestCaseCallableHelper.RUNNING_TEST_CASE_COUNT.getAndDecrement();
        }
        return null;
    }
}
