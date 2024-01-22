package org.bluett.front.engine.impl;

import com.github.joonasvali.naturalmouse.util.FactoryTemplates;
import javafx.beans.property.SimpleObjectProperty;
import org.bluett.front.engine.AutomationEngine;
import org.bluett.front.entity.MouseMoveType;
import org.bluett.front.entity.Point;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class WinAutoMationEngine implements AutomationEngine {
    private static final Robot ROBOT;
    static {
        try {
            ROBOT = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static SimpleObjectProperty<MouseMoveType> MOUSE_MOVE_TYPE = new SimpleObjectProperty<>(MouseMoveType.PROFESSIONAL);

    @Override
    public void click(Point point) throws Exception {
        mouseMoveTo(point);
        ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public void doubleClick(Point point) throws Exception {
        click(point);
        click(point);
    }

    @Override
    public void longClick(Point point, long delay) throws Exception {
        mouseMoveTo(point);
        ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        ROBOT.delay((int) delay);
        ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public void slide(Point startPoint, Point endPoint, long delay) throws Exception {
        mouseMoveTo(startPoint);
        ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        ROBOT.delay((int) delay);
        mouseMoveTo(endPoint);
        ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public BufferedImage screenCapture(Rectangle rectangle) throws Exception {
        return ROBOT.createScreenCapture(rectangle);
    }

    private void mouseMoveTo(Point point) throws InterruptedException {
        switch (MOUSE_MOVE_TYPE.get()) {
            case PROFESSIONAL -> FactoryTemplates.createFastGamerMotionFactory().move(point.getX(), point.getY());
            case NORMAL -> FactoryTemplates.createAverageComputerUserMotionFactory().move(point.getX(), point.getY());
            case FRESHMAN -> FactoryTemplates.createGrannyMotionFactory().move(point.getX(), point.getY());
        }
    }
}