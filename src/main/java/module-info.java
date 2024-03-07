open module org.bluett {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires cn.hutool;
    requires java.sql;
    requires java.desktop;
    requires static lombok;
    requires com.github.joonasvali.naturalmouse;
    requires org.mybatis;
    requires org.xerial.sqlitejdbc;

    exports org.bluett;
    exports org.bluett.controller;
    exports org.bluett.entity to cn.hutool, org.mybatis;
    exports org.bluett.entity.enums to cn.hutool, org.mybatis;
    exports org.bluett.util to cn.hutool, org.mybatis;
}