package fr.kizafox.theguywhoescape.game.utils;

import fr.kizafox.theguywhoescape.game.client.settings.GameSettings;
import fr.kizafox.theguywhoescape.game.entities.handlers.player.Player;
import fr.kizafox.theguywhoescape.game.status.sub.Playing;

import java.awt.*;

/**
 * Created at 20/11/2023 at 21:40
 * Made by @KIZAFOX (twitter)
 **/

public class InfoPrinter {


    public InfoPrinter(final Playing playing, final Graphics graphics, final Player player) {
        final Font font = graphics.getFont();

        graphics.setColor(Color.RED);
        graphics.setFont(font.deriveFont(font.getSize() * 1.5F));

        graphics.drawString("FPS: " + playing.getGame().getFPSChecker().currentFPS, GameSettings.GAME_WIDTH - 80, 20);

        graphics.drawString("Health: " + player.getCurrentHealth() + "/" + player.getMaxHealth(), GameSettings.GAME_WIDTH - 80 * 3, 20);

        graphics.drawString("X: " + Math.round(player.getHitBox().x), GameSettings.GAME_WIDTH - 80 * 4, 20);
        graphics.drawString("Y: " + Math.round(player.getHitBox().y), GameSettings.GAME_WIDTH - 80 * 4, 20 * 2);
    }
}
