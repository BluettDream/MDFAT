package org.bluett.service;

import com.dtflys.forest.Forest;
import lombok.extern.log4j.Log4j2;
import org.bluett.core.http.ImageProcessClient;

@Log4j2
public class TextProcessService {
    public void hello() {
        ImageProcessClient client = Forest.client(ImageProcessClient.class);
        String ret = client.helloWorld();
        log.info(ret);
    }
}
