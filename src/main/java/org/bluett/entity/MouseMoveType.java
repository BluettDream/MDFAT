package org.bluett.entity;

public enum MouseMoveType {
    PROFESSIONAL("专业"), NORMAL("普通"), FRESHMAN("新手");

    private final String name;

    MouseMoveType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
