package org.bluett.core.operator.impl;

import org.bluett.core.operator.AutomaticOperator;
import org.bluett.entity.BaseConstants;
import org.bluett.entity.SettingDO;
import org.bluett.entity.enums.SettingKeyEnum;
import org.bluett.service.CommandService;
import org.bluett.service.SettingService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdbAutoMaticOperator implements AutomaticOperator {
    public static final String SCREEN_CAPTURE_PNG = "screenCapture.png";
    public static final String SDCARD_SCREEN_CAPTURE_PNG = "/sdcard/" + SCREEN_CAPTURE_PNG;
    private final Map<SettingKeyEnum, SettingDO> settingsMap = new SettingService().getSettingMap();
    private final CommandService commandService = new CommandService();
    private final List<String> adbCommand = new ArrayList<>(List.of("adb", "-s"));

    public AdbAutoMaticOperator() {
        adbCommand.add(settingsMap.get(SettingKeyEnum.ADB_DEVICE).getValue());
    }

    @Override
    public void click(Point point) throws Exception {
        adbCommand.add("shell");
        adbCommand.add("input");
        adbCommand.add("tap");
        adbCommand.add(String.valueOf(point.x));
        adbCommand.add(String.valueOf(point.y));
        commandService.executeCommand(convert(adbCommand));
    }

    @Override
    public void doubleClick(Point point) throws Exception {
        click(point);
        click(point);
    }

    @Override
    public void multipleClick(Point point, int times) throws Exception {
        for (int i = 0; i < times; i++) {
            click(point);
        }
    }

    @Override
    public void longClick(Point point, long delay) throws Exception {
        slide(point, point, delay);
    }

    @Override
    public void slide(Point startPoint, Point endPoint, long delay) throws Exception {
        adbCommand.add("shell");
        adbCommand.add("input");
        adbCommand.add("swipe");
        adbCommand.add(String.valueOf(startPoint.x));
        adbCommand.add(String.valueOf(startPoint.y));
        adbCommand.add(String.valueOf(endPoint.x));
        adbCommand.add(String.valueOf(endPoint.y));
        adbCommand.add(String.valueOf(delay));
        commandService.executeCommand(convert(adbCommand));
    }

    @Override
    public BufferedImage screenCapture(Rectangle rectangle) throws Exception {
        adbCommand.add("shell");
        adbCommand.add("screencap");
        adbCommand.add(SDCARD_SCREEN_CAPTURE_PNG);
        commandService.executeCommand(convert(adbCommand));
        commandService.executeCommand(pullScreenCapture());
        return ImageIO.read(BaseConstants.IMG_PATH.resolve(SCREEN_CAPTURE_PNG).toFile());
    }

    @Override
    public void moveTo(Point point) throws InterruptedException {
    }

    @Override
    public void input(String text) throws Exception {
        adbCommand.add("shell");
        adbCommand.add("input");
        adbCommand.add("text");
        adbCommand.add(text);
        commandService.executeCommand(convert(adbCommand));
    }

    private String[] convert(List<String> list) {
        return list.stream().map(String::valueOf).toArray(String[]::new);
    }

    private String[] pullScreenCapture() {
        return new String[]{"adb", "pull", SDCARD_SCREEN_CAPTURE_PNG, BaseConstants.IMG_PATH.resolve("screenCapture.png").toString()};
    }
}
