package org.bluett.core.http;

import com.dtflys.forest.annotation.GetRequest;

public interface BackendClient {

    @GetRequest(url = "http://127.0.0.1:5000/")
    String helloWorld();
}
