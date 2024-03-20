package org.bluett.core.http;

import com.dtflys.forest.annotation.Address;
import com.dtflys.forest.annotation.Get;

@Address(basePath = "${imageProcessURL}")
public interface ImageProcessClient {
    @Get("/")
    String helloWorld();
}
