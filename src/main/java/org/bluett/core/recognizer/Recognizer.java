package org.bluett.core.recognizer;

import org.bluett.entity.dto.RecognitionResp;

public interface Recognizer<T> {
    RecognitionResp recognize(T req);
}
