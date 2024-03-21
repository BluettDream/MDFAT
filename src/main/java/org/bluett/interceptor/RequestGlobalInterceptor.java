package org.bluett.interceptor;

import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.Interceptor;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RequestGlobalInterceptor implements Interceptor<String> {
    @Override
    public boolean beforeExecute(ForestRequest request) {
        log.debug("request: url[{}]", request.getUrl());
        return Interceptor.super.beforeExecute(request);
    }

    @Override
    public void onSuccess(String data, ForestRequest request, ForestResponse response) {
        Interceptor.super.onSuccess(data, request, response);
        log.debug("response: status[{}], result[{}]", response.getStatusCode(), response.getContent());
    }

    @Override
    public void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response) {
        Interceptor.super.onError(ex, request, response);
        log.debug("error: status[{}], result[{}]", response.getStatusCode(), response.getContent());
    }
}
