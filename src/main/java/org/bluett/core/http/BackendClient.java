package org.bluett.core.http;

import com.dtflys.forest.annotation.Address;
import com.dtflys.forest.annotation.Get;

@Address(host = "127.0.0.1", port = "5000")
public interface BackendClient {

    @Get("/")
    String helloWorld();
}
