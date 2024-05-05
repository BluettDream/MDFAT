package org.bluett.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.http.ForestResponse;
import org.bluett.entity.dto.ImageProcessDTO;

import java.util.List;

@BaseRequest(baseURL = "${imageProcessURL}")
public interface ImageProcessClient {

    @Post(value = "/matchPoints")
    ForestResponse<ImageProcessDTO> matchPoints(@Body List<String> imageLinkList);
}
