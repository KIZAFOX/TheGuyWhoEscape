package fr.kizafox.theguywhoescape.game.entities.helper;

import fr.kizafox.theguywhoescape.game.entities.handlers.player.Player;

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
    private static boolean isSolid(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * TILES_SIZE;
        if (x < 0 || x >= maxWidth)
            return true;
        if (y < 0 || y >= GAME_HEIGHT)
            return true;
        float xIndex = x / TILES_SIZE;
        float yIndex = y / TILES_SIZE;

        return isTileSolid((int) xIndex, (int) yIndex, lvlData);
    }

    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!isSolid(x, y, lvlData))
            if (!isSolid(x + width, y + height, lvlData))
                if (!isSolid(x + width, y, lvlData))
                    return !isSolid(x, y + height, lvlData);
        return false;
    }

    public static boolean isTileSolid(int xTile, int yTile, int[][] lvlData) {
        int value = lvlData[yTile][xTile];

        return value >= 48 || value < 0 || value != 11;
    }

    public static float getEntityXPosition(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / TILES_SIZE);
        if (xSpeed > 0) {
            int tileXPos = currentTile * TILES_SIZE;
            int xOffset = (int) (TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else
            return currentTile * TILES_SIZE;
    }

    public static float getEntityYPosition(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / TILES_SIZE);
        if (airSpeed > 0) {
            int tileYPos = currentTile * TILES_SIZE;
            int yOffset = (int) (TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else
            return currentTile * TILES_SIZE;

    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            return isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);
        return true;
    }

    public static boolean isFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        if(xSpeed > 0){
            return isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);
        }
        return isSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
    }

    public static boolean isAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
        for (int i = 0; i < xEnd - xStart; i++) {
            if (isTileSolid(xStart + i, y, lvlData))
                return false;
            if (!isTileSolid(xStart + i, y + 1, lvlData))
                return false;
        }
        return true;
    }

    public static boolean isSightClear(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {
        int firstXTile = (int) (firstHitbox.x / TILES_SIZE);
        int secondXTile = (int) (secondHitbox.x / TILES_SIZE);

        if (firstXTile > secondXTile)
            return isAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
        else
            return isAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);
    }
}