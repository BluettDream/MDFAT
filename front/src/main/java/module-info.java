module com.bluett.front {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.bluett.front to javafx.fxml;
    exports com.bluett.front;
    exports com.bluett.front.controller;
    opens com.bluett.front.controller to javafx.fxml;
}