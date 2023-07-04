package fr.kizafox.theguywhoescape.game.levels;

import fr.kizafox.theguywhoescape.game.client.window.Game;
import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.client.window.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class LevelManager {

    protected final Game game;
    protected final Level levelOne;

    private BufferedImage[] levelSprite;

    public LevelManager(final Game game) {
        this.game = game;
        this.levelOne = new Level(ImageRenderer.getLevelData());

        this.importOutsideSprites();
    }

    public void update(){

    }

    public void render(final Graphics graphics){
        for(int i = 0; i < TILES_IN_HEIGHT; i++){
            for(int j = 0; j < TILES_IN_WIDTH; j++){
                final int index = levelOne.getSpriteIndex(j, i);
                graphics.drawImage(levelSprite[index], TILES_SIZE * j, TILES_SIZE * i, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    private void importOutsideSprites() {
        final BufferedImage image = ImageRenderer.loadSprite(ImageRenderer.LEVEL_ATLAS);

        this.levelSprite = new BufferedImage[48];

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 12; j++){
                int index = i * 12 + j;

                this.levelSprite[index] = image.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
