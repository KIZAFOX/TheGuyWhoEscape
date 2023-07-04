package fr.kizafox.theguywhoescape.game.levels;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class Level {

    private final int[][] levelData;

    public Level(int[][] levelData) {
        this.levelData = levelData;
    }

    public int getSpriteIndex(final int x, final int y) {
        return this.levelData[y][x];
    }

    public int[][] getLevelData(){
        return levelData;
    }
}
