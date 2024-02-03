package org.bluett.front.entity;

import lombok.Getter;

@Getter
public enum View {
    INDEX("index"), SETTING("setting"), ABOUT("about");

    private final String viewName;

    View(String viewName) {
        this.viewName = viewName;
    }

}
