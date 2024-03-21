package org.bluett.request;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.BinaryBody;
import com.dtflys.forest.annotation.Post;
import org.bluett.interceptor.RequestGlobalInterceptor;

import java.awt.*;
import java.util.List;

@BaseRequest(baseURL = "${imageProcessURL}", interceptor = RequestGlobalInterceptor.class)
public interface ImageProcessClient {
    @Post("/matchPoints")
    List<Point> matchPoints(@BinaryBody byte[] tinyImg, @BinaryBody byte[] srcImg, double threshold);
}
