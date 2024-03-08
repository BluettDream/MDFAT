package org.bluett.entity.enums;

import lombok.Getter;

@Getter
public enum MouseMoveTypeEnum {
    PROFESSIONAL("专业"), NORMAL("普通"), FRESHMAN("新手");

    private final String name;

    MouseMoveTypeEnum(String name) {
        this.name = name;
    }

}
