package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.vo.TestImageVO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName test_image
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestImage implements Serializable {
    @Serial
    private static final long serialVersionUID = -5118586449662427554L;
    private Integer id;
    private Integer caseId;
    private String path;
    private Float confidence;
    private Integer pointX;
    private Integer pointY;
    private Integer width;
    private Integer height;
    private Date updateTime;
    private Date createTime;

    public static TestImage convertToTestImage(TestImageVO testImageVO) {
        return TestImage.builder()
                .id(testImageVO.getId())
                .caseId(testImageVO.getCaseId())
                .path(testImageVO.getPath())
                .confidence(testImageVO.getConfidence())
                .pointX(testImageVO.getPointX())
                .pointY(testImageVO.getPointY())
                .width(testImageVO.getWidth())
                .height(testImageVO.getHeight())
                .build();
    }
}