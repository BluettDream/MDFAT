package org.bluett.core.http;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;

@BaseRequest(baseURL = "${imageProcessURL}", charset = "UTF-8", contentEncoding = "UTF-8")
public interface ImageProcessClient {
    @Get("/")
    String helloWorld();
}
