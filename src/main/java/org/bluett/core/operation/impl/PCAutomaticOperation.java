package org.bluett.core.operation.impl;

import com.github.joonasvali.naturalmouse.util.FactoryTemplates;
import org.bluett.core.operation.AutomaticOperation;
import org.bluett.entity.enums.MouseMoveTypeEnum;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PCAutomaticOperation implements AutomaticOperation {

    public static MouseMoveTypeEnum MOUSE_MOVE_TYPE = MouseMoveTypeEnum.PROFESSIONAL;

    public void click(Point point) throws Exception {
        multipleClick(point, 1);
    }

    public void doubleClick(Point point) throws Exception {
        multipleClick(point, 2);
    }

    @Override
    public abstract void multipleClick(Point point, int times) throws Exception;

    @Override
    public abstract void longClick(Point point, long delay) throws Exception;

    @Override
    public abstract void slide(Point startPoint, Point endPoint, long delay) throws Exception;

    @Override
    public abstract BufferedImage screenCapture(Rectangle rectangle) throws Exception;

    protected void moveTo(Point point) throws InterruptedException {
        switch (MOUSE_MOVE_TYPE) {
            case PROFESSIONAL -> FactoryTemplates.createFastGamerMotionFactory().move((int) point.getX(), (int) point.getY());
            case NORMAL -> FactoryTemplates.createAverageComputerUserMotionFactory().move((int) point.getX(), (int) point.getY());
            case FRESHMAN -> FactoryTemplates.createGrannyMotionFactory().move((int) point.getX(), (int) point.getY());
        }
    }
}
