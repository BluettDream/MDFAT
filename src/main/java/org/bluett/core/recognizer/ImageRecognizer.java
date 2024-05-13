package org.bluett.core.recognizer;

import com.dtflys.forest.Forest;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bluett.client.ImageRecognizeClient;
import org.bluett.entity.dto.ImageRecognitionReq;
import org.bluett.entity.dto.RecognitionResp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Log4j2
public class ImageRecognizer implements Recognizer<ImageRecognitionReq> {
    ImageRecognizeClient client = Forest.client(ImageRecognizeClient.class);

    public RecognitionResp recognize(ImageRecognitionReq req) {
        return client.recognize(req).getResult();
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
        } finally {
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
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                log.error(ExceptionUtils.getRootCause(e));
            }
        }
        return baos.toByteArray();
    }
}
