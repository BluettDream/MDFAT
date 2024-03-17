package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.vo.TestTextVO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName test_text
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestText implements Serializable {
    @Serial
    private static final long serialVersionUID = -2896054250646119072L;
    private Integer id;
    private Integer caseId;
    private String text;
    private Float confidence;
    private Integer pointX;
    private Integer pointY;
    private Date updateTime;
    private Date createTime;

    public static TestText convertToTestText(TestTextVO testTextVO) {
        return TestText.builder()
                .id(testTextVO.getId())
                .caseId(testTextVO.getCaseId())
                .text(testTextVO.getText())
                .confidence(testTextVO.getConfidence())
                .pointX(testTextVO.getPointX())
                .pointY(testTextVO.getPointY())
                .build();
    }
}