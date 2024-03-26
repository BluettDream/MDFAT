package org.bluett.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.DataFile;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.http.ForestResponse;
import org.bluett.entity.dto.ImageProcessDTO;

import java.util.Map;

@BaseRequest(baseURL = "${imageProcessURL}")
public interface ImageProcessClient {
    @Post(value = "/matchPoints")
    ForestResponse<ImageProcessDTO> matchPoints(@DataFile(value = "${_key}", fileName = "${_key}") Map<String, byte[]> byteArrayMap, @Body("threshold") double threshold);
}
