package org.bluett.service;

import com.dtflys.forest.Forest;
import lombok.extern.log4j.Log4j2;
import org.bluett.request.ImageProcessClient;

@Log4j2
public class ImageProcessService {
    public void hello() {
        ImageProcessClient client = Forest.client(ImageProcessClient.class);
        client.helloWorld();
    }
}
