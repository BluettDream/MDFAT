package org.bluett.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.bluett.builder.UIBuilder;
import org.bluett.entity.dto.OperationDialogDTO;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestImageVO;
import org.bluett.entity.vo.TestTextVO;
import org.bluett.service.OperationService;
import org.bluett.service.TestImageService;
import org.bluett.service.TestTextService;
import org.bluett.ui.OperationDialog;
import org.bluett.ui.TestImageDialog;
import org.bluett.ui.TestTextDialog;

import java.nio.file.Paths;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
public class TestCaseListCellContentController {
    @FXML
    private Label caseIdL;
    @FXML
    private ImageView caseImageIV;
    @FXML
    private Label caseNameL;

    private final TestImageService imageService = new TestImageService();
    private final TestTextService textService = new TestTextService();
    private final OperationService operationService = new OperationService();

    private final TestCaseVO testCaseVO;
    private TestImageVO currentTestImageVO;
    private TestTextVO currentTestTextVO;

    @FXML
    void initialize() {
        caseIdL.setText(String.valueOf(testCaseVO.getId()));
        caseNameL.setText(testCaseVO.getName());
        TestImageVO testImageVO = imageService.selectByCaseId(testCaseVO.getId());
        if (Objects.nonNull(testImageVO) && StringUtils.isNotBlank(testImageVO.getLink())) {
            caseImageIV.setImage(new Image(Paths.get(testImageVO.getLink()).toUri().toString()));
            currentTestImageVO = testImageVO;
        }
        TestTextVO testTextVO = textService.selectByCaseId(testCaseVO.getId());
        if (Objects.nonNull(testTextVO)) {
            currentTestTextVO = testTextVO;
        }
    }

    @FXML
    void addOrUpdateOperationClick() {
        new OperationDialog("", new OperationDialogDTO(testCaseVO.getId(), operationService.selectBatchByCaseId(testCaseVO.getId())))
                .showAndWait().ifPresent(operationDialogDTO -> {
        });
    }

    @FXML
    void addOrUpdateTestImageClick() {
        new TestImageDialog("", currentTestImageVO).showAndWait().ifPresent(testImageVO -> {
            testImageVO.caseIdProperty().set(testCaseVO.getId());
            testImageVO.idProperty().set(currentTestImageVO.getId());
            if (!imageService.update(testImageVO)) {
                log.error("update testImage error");
                UIBuilder.showErrorAlert("编辑测试图像异常", 0.8);
                return;
            }
            caseImageIV.setImage(new Image(Paths.get(testImageVO.getLink()).toUri().toString()));
            UIBuilder.showInfoAlert("编辑测试图像成功", 0.8);
        });
    }

    @FXML
    void addOrUpdateTestTextClick() {
        new TestTextDialog("", currentTestTextVO).showAndWait().ifPresent(testTextVO -> {
            testTextVO.caseIdProperty().set(testCaseVO.getId());
            testTextVO.idProperty().set(currentTestTextVO.getId());
            if (!textService.update(testTextVO)) {
                log.error("update testText error");
                UIBuilder.showErrorAlert("编辑测试文本异常", 0.8);
                return;
            }
            UIBuilder.showInfoAlert("编辑测试文本成功", 0.8);
        });
    }
}
