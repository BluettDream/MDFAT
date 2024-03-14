package org.bluett.entity.enums;

import lombok.Getter;
import org.bluett.helper.UIHelper;

@Getter
public enum OperationEnum {
    CLICK(UIHelper.getI18nStr("operate.click")),
    CLICK_RANDOM(UIHelper.getI18nStr("operate.click.random")),
    CLICK_AND_HOLD(UIHelper.getI18nStr("operate.click.hold")),
    SLIDE(UIHelper.getI18nStr("operate.slide"));

    private final String msg;

    OperationEnum(String msg) {
        this.msg = msg;
    }
}
