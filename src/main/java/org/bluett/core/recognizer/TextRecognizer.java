package org.bluett.core.recognizer;

import com.dtflys.forest.Forest;
import lombok.extern.log4j.Log4j2;
import org.bluett.client.TextRecognizeClient;
import org.bluett.entity.dto.RecognitionResp;
import org.bluett.entity.dto.TextRecognitionReq;

@Log4j2
public class TextRecognizer implements Recognizer<TextRecognitionReq> {
    TextRecognizeClient client = Forest.client(TextRecognizeClient.class);

    public RecognitionResp recognize(TextRecognitionReq req) {
        return client.recognize(req).getResult();
    }
}
