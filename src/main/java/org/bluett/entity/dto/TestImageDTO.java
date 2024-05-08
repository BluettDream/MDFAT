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
public class TestImageDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3489855254585548154L;

    private Long id;

    private String link;

    private Double confidence;

    private Integer x;

    private Integer y;

    private Integer width;

    private Integer height;

    private RecognitionResp resp;
}
