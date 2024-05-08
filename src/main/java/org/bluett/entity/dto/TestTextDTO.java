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
public class TestTextDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3688865483270186529L;

    private Long id;

    private String text;

    private Double confidence;

    private Integer x;

    private Integer y;

    private RecognitionResp resp;
}
