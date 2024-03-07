package org.bluett.entity.enums;

import lombok.Getter;

@Getter
public enum MouseMoveType {
    PROFESSIONAL("专业"), NORMAL("普通"), FRESHMAN("新手");

    private final String name;

    MouseMoveType(String name) {
        this.name = name;
    }

}
