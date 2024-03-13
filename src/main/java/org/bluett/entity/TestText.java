package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.vo.TestTextVO;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName test_text
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestText implements Serializable {
    @Serial
    private static final long serialVersionUID = -5928940947665467032L;
    private Integer id;
    /**
     * 测试文本
     */
    private String text;
    /**
     * 置信度
     */
    private Double confidence;
    /**
     * 相似度
     */
    private Double similarity;
    /**
     * 用例id
     */
    private Integer caseId;

    public static TestText convertToTestText(TestTextVO textVO) {
        return TestText.builder()
                .id(textVO.getId())
                .text(textVO.getText())
                .confidence(textVO.getConfidence())
                .similarity(textVO.getSimilarity())
                .caseId(textVO.getCaseId())
                .build();
    }
}