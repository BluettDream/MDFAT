package org.bluett.service;

import com.dtflys.forest.Forest;
import lombok.extern.log4j.Log4j2;
import org.bluett.request.ImageProcessClient;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Log4j2
public class ImageProcessService {
    public Point getMatchLocation(String tinyImgPath, String srcImgPath, double threshold) {
        ImageProcessClient client = Forest.client(ImageProcessClient.class);
        client.matchPoints(null, null, 0.0);
        return null;
    }

    public Point getMatchLocation(String tinyImgPath, BufferedImage srcImg, double threshold) throws IOException {
        ImageProcessClient client = Forest.client(ImageProcessClient.class);
        List<Point> points = client.matchPoints(imageToByteArray(tinyImgPath), bufferedImageToByteArray(srcImg), threshold);
        if (!points.isEmpty()) {
            return points.getFirst();
        }
        return null;
    }

    private byte[] imageToByteArray(String imagePath) throws IOException {
        BufferedImage img = ImageIO.read(new File(imagePath));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        return baos.toByteArray();
    }

    private byte[] bufferedImageToByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }
}
