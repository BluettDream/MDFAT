package org.bluett.engine;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 自动化引擎, 用于模拟点击、滑动、截图等操作
 */
public interface AutomaticOperation {

    /**
     * 指定位置点击多次
     * @param point 点击位置
     * @param times 点击次数
     * @throws Exception 异常
     */
    void multipleClick(Point point, int times) throws Exception;

    /**
     * 长按指定位置delay毫秒后松开
     * @param point 点击位置
     * @param delay 延迟时间(ms)
     * @throws Exception 异常
     */
    void longClick(Point point, long delay) throws Exception;

    /**
     * 从起始点滑动经过delay毫秒滑动到终点
     * @param startPoint 起始点
     * @param endPoint 终点
     * @param delay 延迟时间(ms)
     * @throws Exception 异常
     */
    void slide(Point startPoint, Point endPoint, long delay) throws Exception;

    /**
     * 指定矩形区域截图
     * @param rectangle 矩形区域
     * @throws Exception 异常
     */
    BufferedImage screenCapture(Rectangle rectangle) throws Exception;
}
