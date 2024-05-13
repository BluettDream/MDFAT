package org.bluett.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextRecognitionReq implements Serializable {
    @Serial
    private static final long serialVersionUID = -5971587849189287257L;

    private String text;

    private String captureLink;

    private Double confidence;

    private Integer x;

    private Integer y;
}
