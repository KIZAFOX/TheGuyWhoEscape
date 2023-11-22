package fr.kizafox.theguywhoescape.game.utils;

import fr.kizafox.theguywhoescape.game.client.settings.GameSettings;
import fr.kizafox.theguywhoescape.game.entities.handlers.enemy.enemies.Crabby;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static fr.kizafox.theguywhoescape.game.utils.Constants.EnemyConstants.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class ImageRenderer {

    public static final String FONT = "font.png";

    public static final String PLAYING_BACKGROUND_IMAGE = "playing_bg_img.png";
    public static final String BIG_CLOUDS = "big_clouds.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String CRABBY_ATLAS = "crabby_sprite.png";

    public static final String LEVEL_ATLAS = "outside_sprites.png";
    //public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String LEVEL_ONE_DATA = "level_one_data_long.png";

    public static final String BACKGROUND_MENU = "background_menu.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String MENU_BUTTONS = "button_atlas.png";

    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";

    public static final String STATUS_BAR = "health_power_bar.png";

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
                throw new RuntimeException(e);
            }
        }
        return image;
    }

    public static List<Crabby> getCrabs(){
        final BufferedImage image = loadSprite(LEVEL_ONE_DATA);
        final List<Crabby> list = new ArrayList<>();

        for(int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                final Color color = new Color(image.getRGB(j, i));
                int value = color.getGreen();

                if(value == CRABBY){
                    list.add(new Crabby(j * GameSettings.TILES_SIZE, i * GameSettings.TILES_SIZE));
                }
            }
        }
        return list;
    }

    public static int[][] getLevelData() {
        final BufferedImage image = loadSprite(LEVEL_ONE_DATA);
        final int[][] levelData = new int[image.getHeight()][image.getWidth()];

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
