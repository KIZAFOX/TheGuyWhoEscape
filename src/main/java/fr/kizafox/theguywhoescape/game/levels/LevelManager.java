package fr.kizafox.theguywhoescape.game.levels;

import fr.kizafox.theguywhoescape.game.client.window.Game;
import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

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

    public void render(final Graphics graphics, final int levelOffset){
        for(int i = 0; i < TILES_IN_HEIGHT; i++){
            for(int j = 0; j < this.levelOne.levelData()[0].length; j++){
                final int index = this.levelOne.getSpriteIndex(j, i);
                graphics.drawImage(this.levelSprite[index], TILES_SIZE * j - levelOffset, TILES_SIZE * i, TILES_SIZE, TILES_SIZE, null);
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
