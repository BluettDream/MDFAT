package org.bluett.request;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import org.bluett.interceptor.RequestGlobalInterceptor;

@BaseRequest(baseURL = "${textProcessURL}", interceptor = RequestGlobalInterceptor.class)
public interface TextProcessClient {
    @Get("/")
    String helloWorld();
}
