open module org.bluett {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires java.desktop;
    requires static lombok;
    requires com.github.joonasvali.naturalmouse;
    requires org.mybatis;
    requires org.xerial.sqlitejdbc;
    requires org.apache.commons.lang3;
    requires org.apache.commons.io;
    requires org.apache.commons.collections4;
    requires org.apache.commons.codec;
    requires forest.core;
    requires com.alibaba.fastjson2;

    exports org.bluett;
    exports org.bluett.ui.controller;
    exports org.bluett.entity;
    exports org.bluett.entity.vo;
    exports org.bluett.entity.enums;
    exports org.bluett.helper;
}