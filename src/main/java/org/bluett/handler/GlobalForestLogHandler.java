package org.bluett.handler;

import com.dtflys.forest.logging.DefaultLogHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GlobalForestLogHandler extends DefaultLogHandler {
    @Override
    public void logContent(String content) {
        super.logContent(content);
        log.debug(content);
    }
}
