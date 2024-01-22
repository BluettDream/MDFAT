module org.bluett.front {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    requires org.apache.logging.log4j;

    requires java.desktop;
    requires com.github.joonasvali.naturalmouse;

    exports org.bluett.front;
    opens org.bluett.front to javafx.fxml;
    exports org.bluett.front.controller;
    opens org.bluett.front.controller to javafx.fxml;
    exports org.bluett.front.component;
    opens org.bluett.front.component to javafx.fxml;
}