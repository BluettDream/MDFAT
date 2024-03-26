package org.bluett.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;

@BaseRequest(baseURL = "${textProcessURL}")
public interface TextProcessClient {
    @Get("/")
    String helloWorld();
}
