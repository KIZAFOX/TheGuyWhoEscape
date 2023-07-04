package fr.kizafox.theguywhoescape.game.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static fr.kizafox.theguywhoescape.game.client.window.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class ImageRenderer {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String LEVEL_TWO_DATA = "level_two_data.png";

    public static BufferedImage loadSprite(final String fileName){
        final InputStream inputStream = ImageRenderer.class.getResourceAsStream("/" + fileName);
        final BufferedImage image;

        if(inputStream == null){
            throw new IllegalStateException("File " + fileName + " not found or doesn't exist in 'resources' folder!");
        }

        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                inputStream.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    public static int[][] getLevelData() {
        final int[][] levelData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        final BufferedImage image = loadSprite(LEVEL_TWO_DATA);

        for(int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                final Color color = new Color(image.getRGB(j, i));
                int value = color.getRed();

                if(value >= 48){
                    value = 0;
                }

                levelData[i][j] = value;
            }
        }
        return levelData;
    }
}
