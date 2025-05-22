/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author emdominguez
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;



public class ImageUtil {

    //Hace el resize de la foto, cuando llamamos desde editar el perfil
    public static void resize(File input, File output, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(input);
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, width, height, null);
            g.dispose();

            ImageIO.write(resizedImage, "jpg", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
