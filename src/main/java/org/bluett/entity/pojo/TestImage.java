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
    private Path imagePath;
    private double similarity;
    private double confidence;

    @Override
    public int compareTo(TestImage o) {
        return Double.compare(this.similarity, o.similarity);
    }
}
