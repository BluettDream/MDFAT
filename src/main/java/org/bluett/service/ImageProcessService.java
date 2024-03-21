package org.bluett.service;

import com.dtflys.forest.Forest;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bluett.request.ImageProcessClient;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class ImageProcessService {
    public Point getMatchLocation(String matchImagePath, BufferedImage templateImage, double threshold) {
        ImageProcessClient client = Forest.client(ImageProcessClient.class);
        List<Point> points = client.matchPoints(new HashMap<>() {
            @Serial
            private static final long serialVersionUID = 36040004060497121L;
            {
            put("matchImage.png", imageToByteArray(matchImagePath));
            put("templateImage.png", bufferedImageToByteArray(templateImage));
        }}, threshold);
        if (!points.isEmpty()) {
            return points.getFirst();
        }
        return null;
    }

    private byte[] imageToByteArray(String imagePath) {
        BufferedImage img = null;
        ByteArrayOutputStream baos = null;
        try {
            img = ImageIO.read(new File(imagePath));
            baos = new ByteArrayOutputStream();
            ImageIO.write(img, "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (img != null) {
                img.flush();
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    log.error(ExceptionUtils.getRootCause(e));
                }
            }
        }
        return baos.toByteArray();
    }

    private byte[] bufferedImageToByteArray(BufferedImage bufferedImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                baos.close();
            } catch (IOException e) {
                log.error(ExceptionUtils.getRootCause(e));
            }
        }
        return baos.toByteArray();
    }
}
