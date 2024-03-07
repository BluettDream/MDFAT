package org.bluett.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.vo.TestCaseViewModel;
import org.bluett.service.IConverter;
import org.bluett.service.impl.TestCaseServiceImpl;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCase implements Serializable, Comparable<TestCase>, IConverter<TestCaseViewModel> {
    @Serial
    private static final long serialVersionUID = -5101342570102128295L;
    private Integer id;
    private String name;
    private String description;
    private int priority;
    private TestImage expectedImage;
    private TestResult status;

    public TestCase(String name, String description, int priority, TestImage expectedImage, TestResult status) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.expectedImage = expectedImage;
        this.status = status;
    }

    @Override
    public int compareTo(TestCase o) {
        if(expectedImage != null && o.expectedImage != null)
            return expectedImage.compareTo(o.expectedImage);
        return Integer.compare(this.priority, o.priority);
    }

    @Override
    public TestCaseViewModel convertTo() {
        return new TestCaseViewModel(new TestCaseServiceImpl());
    }
}
