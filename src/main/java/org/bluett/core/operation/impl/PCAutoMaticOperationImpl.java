package org.bluett.core.operation.impl;

import org.bluett.core.operation.PCAutomaticOperation;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class PCAutoMaticOperationImpl extends PCAutomaticOperation {
    private static final Robot ROBOT;
    static {
        try {
            ROBOT = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void multipleClick(Point point, int times) throws Exception {
        moveTo(point);
        for (int i = 0; i < times; i++) {
            ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }

    @Override
    public void longClick(Point point, long delay) throws Exception {
        moveTo(point);
        ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        ROBOT.delay((int) delay);
        ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public void slide(Point startPoint, Point endPoint, long delay) throws Exception {
        moveTo(startPoint);
        ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        ROBOT.delay((int) delay);
        moveTo(endPoint);
        ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public BufferedImage screenCapture(Rectangle rectangle) {
        return ROBOT.createScreenCapture(rectangle);
    }
}