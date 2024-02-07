module org.bluett {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.apache.logging.log4j;

    requires java.desktop;
    requires static lombok;
    requires com.github.joonasvali.naturalmouse;

    exports org.bluett;
    opens org.bluett to javafx.fxml;
    exports org.bluett.controller;
    opens org.bluett.controller to javafx.fxml;
}