package org.bluett.front.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCase implements Serializable, Comparable<TestCase> {
    @Serial
    private static final long serialVersionUID = -5101342570102128295L;
    private String name;
    private String description;
    private int priority;
    private TestImage expectedImage;

    @Override
    public int compareTo(TestCase o) {
        if(expectedImage != null && o.expectedImage != null)
            return expectedImage.compareTo(o.expectedImage);
        return Integer.compare(this.priority, o.priority);
    }
}
