package fr.kizafox.theguywhoescape.game.client.window.ui.overlay;

import fr.kizafox.theguywhoescape.game.client.settings.GameSettings;
import fr.kizafox.theguywhoescape.game.status.GameStatus;
import fr.kizafox.theguywhoescape.game.status.sub.Playing;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created at 19/11/2023 at 16:19
 * Made by @KIZAFOX (twitter)
 **/

public class GameOverOverlay {

    protected final Playing playing;

    public GameOverOverlay(Playing playing) {
        this.playing = playing;
    }

    public void render(final Graphics graphics){
        graphics.setColor(new Color(0, 0, 0, 100));
        graphics.fillRect(0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT);

        graphics.setColor(Color.WHITE);
        graphics.drawString("Game Over", GameSettings.GAME_WIDTH / 2, 150);
        graphics.drawString("Press esc to enter Main  Menu!", GameSettings.GAME_WIDTH / 2, 300);
    }

    public void keyPressed(final KeyEvent event){
        if(event.getKeyCode() == KeyEvent.VK_ESCAPE){
            playing.resetAll();
            GameStatus.STATUS = GameStatus.MENU;
        }
    }
}
