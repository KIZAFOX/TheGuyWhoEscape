package fr.kizafox.theguywhoescape.game.entities.utils;

import java.awt.geom.Rectangle2D;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class EntityHelper {
    public static float getEntityXPosition(final Rectangle2D.Float hitBox, final float xSpeed){
        final int currentTile = (int) (hitBox.x / TILES_SIZE);

        if(xSpeed > 0){
            final int
                    tileXPos = currentTile * TILES_SIZE,
                    xOffset = (int) (TILES_SIZE - hitBox.width);
            return tileXPos + xOffset -1;
        }else{
            return currentTile * TILES_SIZE;
        }
    }

    public static float getEntityYPosition(final Rectangle2D.Float hitBox, final float airSpeed){
        final int currentTile = (int) (hitBox.y / TILES_SIZE);

        if(airSpeed > 0){
            final int
                    tileYPos = currentTile * TILES_SIZE,
                    yOffset = (int) (TILES_SIZE - hitBox.height);
            return tileYPos + yOffset -1;
        }else{
            return currentTile * TILES_SIZE;
        }
    }

    public static boolean isEntityOnFloor(final Rectangle2D.Float hitBox, final int[][] levelData){
        if(isSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData)){
            return isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData);
        }
        return false;
    }

    public static boolean canMoveHere(final float x, final float y, final float width, final float height, final int[][] levelData) {
        if (isSolid(x, y, levelData)){
            if (isSolid(x + width, y + height, levelData)){
                if (isSolid(x + width, y, levelData)){
                    return isSolid(x, y + height, levelData);
                }
            }
        }
        return false;
    }

    private static boolean isSolid(final float x, final float y, final int[][] levelData) {
        if (x < 0 || x >= GAME_WIDTH){
            return false;
        }
        if (y < 0 || y >= GAME_HEIGHT){
            return false;
        }

        float xIndex = x / TILES_SIZE;
        float yIndex = y / TILES_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];

        return value < 48 && value >= 0 && value == 11;
    }
}

