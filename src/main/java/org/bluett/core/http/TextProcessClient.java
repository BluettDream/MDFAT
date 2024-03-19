package org.bluett.core.http;

import com.dtflys.forest.annotation.Address;
import com.dtflys.forest.annotation.Get;

@Address(basePath = "${textProcessURL}")
public interface TextProcessClient {
    @Get("/")
    String helloWorld();
}
