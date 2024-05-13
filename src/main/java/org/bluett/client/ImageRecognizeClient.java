package org.bluett.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.http.ForestResponse;
import org.bluett.entity.dto.ImageRecognitionReq;
import org.bluett.entity.dto.RecognitionResp;

@BaseRequest(baseURL = "${imageProcessURL}")
public interface ImageRecognizeClient {

    @Post(value = "/recognize")
    ForestResponse<RecognitionResp> recognize(@JSONBody ImageRecognitionReq req);
}
