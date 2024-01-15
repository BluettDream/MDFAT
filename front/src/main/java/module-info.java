module com.bluett.front {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    exports com.bluett.front;
    opens com.bluett.front to javafx.fxml;
    exports com.bluett.front.controller;
    opens com.bluett.front.controller to javafx.fxml;
    exports com.bluett.front.component;
    opens com.bluett.front.component to javafx.fxml;
}