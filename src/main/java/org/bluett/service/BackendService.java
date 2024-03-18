package org.bluett.service;

import com.dtflys.forest.Forest;
import lombok.extern.log4j.Log4j2;
import org.bluett.core.http.BackendClient;

@Log4j2
public class BackendService {
    public void hello() {
        BackendClient client = Forest.client(BackendClient.class);
        String ret = client.helloWorld();
        log.info(ret);
    }
}
