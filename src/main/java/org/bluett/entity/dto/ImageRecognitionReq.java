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
public class ImageRecognitionReq implements Serializable {
    @Serial
    private static final long serialVersionUID = -4962045369211375612L;

    private String imageLink;

    private String captureLink;

    private Double confidence;

    private Integer x;

    private Integer y;

    private Integer width;

    private Integer height;
}
