package org.bluett.core.operator;

import com.github.joonasvali.naturalmouse.api.MouseMotionFactory;
import com.github.joonasvali.naturalmouse.util.FactoryTemplates;
import org.bluett.entity.enums.MouseMoveTypeEnum;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PCAutomaticOperator implements AutomaticOperator {

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

    public void moveTo(Point point) throws InterruptedException {
        MouseMotionFactory mouseMotionFactory;
        switch (MOUSE_MOVE_TYPE) {
            case PROFESSIONAL -> mouseMotionFactory = FactoryTemplates.createFastGamerMotionFactory();
            case FRESHMAN -> mouseMotionFactory = FactoryTemplates.createGrannyMotionFactory();
            default -> mouseMotionFactory = FactoryTemplates.createAverageComputerUserMotionFactory();
        }
        mouseMotionFactory.move((int) point.getX(), (int) point.getY());
    }
}
