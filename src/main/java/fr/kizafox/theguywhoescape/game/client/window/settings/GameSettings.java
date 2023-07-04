package fr.kizafox.theguywhoescape.game.client.window.settings;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class GameSettings {

    public static final String
            NAME = "TheGuyWhoEscape",
            VERSION = "PreAlpha-1.0.0",
            DESCRIPTION = "The description of my project.";

    public static final String[] authors = {"KIZAFOX"};

    public static final float SCALE = 1.5F;

    public static final int
            TILES_DEFAULT_SIZE = 32,
            TILES_IN_WIDTH = 26,
            TILES_IN_HEIGHT = 14,
            TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE),
            GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH,
            GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
}
