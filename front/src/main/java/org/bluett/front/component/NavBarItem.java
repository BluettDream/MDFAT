package org.bluett.front.component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.net.MalformedURLException;
import java.nio.file.Path;

public class NavBarItem extends HBox {
    private final ImageView iv;
    private final Label label;

    public NavBarItem(Path imgPath, String text) throws MalformedURLException {
        iv = new ImageView(String.valueOf(imgPath.toUri().toURL()));
        label = new Label(text);
        setImageViewStyle();
        setLabelStyle();
        setNavBarItemStyle();
        this.getChildren().addAll(iv, label);
    }

    private void setLabelStyle() {
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(120.0);
        label.setMinWidth(50.0);
        label.setPrefWidth(120.0);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setFont(Font.font(20.0));
        HBox.setHgrow(label, Priority.ALWAYS);
    }

    private void setImageViewStyle() {
        iv.setFitHeight(32.0);
        iv.setFitWidth(32.0);
        iv.setPickOnBounds(true);
        iv.setPreserveRatio(true);
        HBox.setHgrow(iv, Priority.ALWAYS);
    }

    private void setNavBarItemStyle() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setMaxWidth(155.0);
        this.setMinWidth(50.0);
        this.setPrefWidth(155.0);
    }
}
