package fr.kizafox.theguywhoescape.main;

import fr.kizafox.theguywhoescape.game.client.settings.GameSettings;
import fr.kizafox.theguywhoescape.game.client.window.Game;
import fr.kizafox.theguywhoescape.game.utils.DiscordRich;

import static fr.kizafox.theguywhoescape.game.utils.Colors.*;

import javax.swing.*;

public class TheGuyWhoEscape {
    public static void main(String[] args) {
        System.out.println();
        System.out.println(RED_BRIGHT +
                "  _____ _           ____           __        ___           _____                          \n" +
                " |_   _| |__   ___ / ___|_   _ _   \\ \\      / / |__   ___ | ____|___  ___ __ _ _ __   ___ \n" +
                "   | | | '_ \\ / _ \\ |  _| | | | | | \\ \\ \\ / /| '_ \\ / _ \\|  _| / __|/ __/ _` | '_ \\ / _ \\ \n" +
                "   | | | | | |  __/ |_| | |_| | |_| |\\ V  V / | | | | (_) | |___\\__ \\ (_| (_| | |_) |  __/ \n" +
                "   |_| |_| |_|\\___|\\____|\\__,_|\\__, | \\_/\\_/  |_| |_|\\___/|_____|___/\\___\\__,_| .__/ \\___| \n" +
                "                               |___/                                          |_|         \n");
        System.out.println(BLUE + "@Author: " + GREEN_BOLD + GameSettings.AUTHORS[0]);
        System.out.println(BLUE + "@Description: " + GREEN_BOLD + GameSettings.DESCRIPTION);
        System.out.println();
        System.out.println(BLUE + "TheGuyWhoEscape (" + GREEN_BOLD + "v" + GameSettings.VERSION + BLUE + ")     Repository: " + RED_BOLD + GameSettings.URL);
        System.out.println();

        SwingUtilities.invokeLater(Game::new);
    }
}