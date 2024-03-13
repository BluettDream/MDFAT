package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.vo.TestImageVO;

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
    /**
     * 图像路径
     */
    private String path;
    /**
     * 相似度
     */
    private double similarity;
    /**
     * 置信度
     */
    private double confidence;
    /**
     * 用例id
     */
    private Integer caseId;

    public static TestImage convertToTestImage(TestImageVO imageVO) {
        return TestImage.builder()
                .id(imageVO.getId())
                .path(imageVO.getPath())
                .similarity(imageVO.getSimilarity())
                .confidence(imageVO.getConfidence())
                .caseId(imageVO.getTestCaseId())
                .build();
    }
}
