package kcart.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

public class ImageUtil {

    // Convert Icon (from JLabel) to byte[] for DB storage.
    public static byte[] iconToBytes(Icon icon) {
        if (icon != null && icon instanceof ImageIcon) {
            try {
                ImageIcon imgIcon = (ImageIcon) icon;
                Image img = imgIcon.getImage();
                BufferedImage bImg = new BufferedImage(
                        img.getWidth(null),
                        img.getHeight(null),
                        BufferedImage.TYPE_INT_RGB
                );
                Graphics2D g2 = bImg.createGraphics();
                g2.drawImage(img, 0, 0, null);
                g2.dispose();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bImg, "jpg", baos);
                return baos.toByteArray();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}