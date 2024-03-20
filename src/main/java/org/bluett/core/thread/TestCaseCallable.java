package org.bluett.core.thread;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.service.ImageProcessService;

import java.util.concurrent.Callable;

@Log4j2
@RequiredArgsConstructor
public class TestCaseCallable implements Callable<Boolean> {

    private final TestCaseVO testCaseVO;
    private final ImageProcessService imageProcessService;

    @Override
    public Boolean call() {
        try {
            imageProcessService.hello();
        }catch (Exception e){
            log.error(ExceptionUtils.getRootCause(e));
        }
        return null;
    }
}
