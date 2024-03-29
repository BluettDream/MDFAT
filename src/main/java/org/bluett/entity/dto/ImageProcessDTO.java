package org.bluett.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageProcessDTO {
    private Float similarity;
    private List<Integer> location;
}
