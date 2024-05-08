package org.bluett.core.recognizer;

import com.dtflys.forest.Forest;
import lombok.extern.log4j.Log4j2;
import org.bluett.client.TextRecongnizeClient;
import org.bluett.entity.dto.RecognitionResp;
import org.bluett.entity.dto.TextRecognitionReq;

@Log4j2
public class TextRecognizer {
    TextRecongnizeClient client = Forest.client(TextRecongnizeClient.class);

    public RecognitionResp recongnize(TextRecognitionReq req) {
        return client.recongnize(req).getResult();
    }
}
