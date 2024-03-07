package org.bluett.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.nio.file.Path;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestImage implements Serializable, Comparable<TestImage>{
    @Serial
    private static final long serialVersionUID = 545599542964493604L;
    private Integer id;
    // 图像路径
    private Path imagePath;
    // 相似度
    private double similarity;
    // 置信度
    private double confidence;

    @Override
    public int compareTo(TestImage o) {
        return Double.compare(this.similarity, o.similarity);
    }
}
