package org.bluett.request;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.DataFile;
import com.dtflys.forest.annotation.Post;
import org.bluett.interceptor.RequestGlobalInterceptor;

import java.awt.*;
import java.util.List;
import java.util.Map;

@BaseRequest(baseURL = "${imageProcessURL}", interceptor = RequestGlobalInterceptor.class)
public interface ImageProcessClient {
    @Post("/matchPoints")
    List<Point> matchPoints(@DataFile(value = "${_key}", fileName = "${_key}") Map<String, byte[]> byteArrayMap, @Body("threshold") double threshold);
}
