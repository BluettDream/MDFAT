package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestImage implements Serializable{
    @Serial
    private static final long serialVersionUID = 545599542964493604L;
    private Integer id;
    // 图像路径
    private String path;
    // 相似度
    private double similarity;
    // 置信度
    private double confidence;
}
