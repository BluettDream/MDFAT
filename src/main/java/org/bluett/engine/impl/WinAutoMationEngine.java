package org.bluett.engine.impl;

import com.github.joonasvali.naturalmouse.util.FactoryTemplates;
import org.bluett.engine.AutomationEngine;
import org.bluett.entity.MouseMoveType;

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

    public static MouseMoveType MOUSE_MOVE_TYPE = MouseMoveType.PROFESSIONAL;

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
        switch (MOUSE_MOVE_TYPE) {
            case PROFESSIONAL -> FactoryTemplates.createFastGamerMotionFactory().move((int) point.getX(), (int) point.getY());
            case NORMAL -> FactoryTemplates.createAverageComputerUserMotionFactory().move((int) point.getX(), (int) point.getY());
            case FRESHMAN -> FactoryTemplates.createGrannyMotionFactory().move((int) point.getX(), (int) point.getY());
        }
    }
}