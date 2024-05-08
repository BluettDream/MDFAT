package org.bluett.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecognitionResp {
    // 是否识别成功
    private Boolean success;
    // 坐标
    private List<Integer> coordinate;
}
