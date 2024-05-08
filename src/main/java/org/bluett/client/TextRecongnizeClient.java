package org.bluett.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.http.ForestResponse;
import org.bluett.entity.dto.RecognitionResp;
import org.bluett.entity.dto.TextRecognitionReq;

@BaseRequest(baseURL = "${textProcessURL}")
public interface TextRecongnizeClient {

    @Post("/recongnize")
    ForestResponse<RecognitionResp> recongnize(@Body TextRecognitionReq req);
}
