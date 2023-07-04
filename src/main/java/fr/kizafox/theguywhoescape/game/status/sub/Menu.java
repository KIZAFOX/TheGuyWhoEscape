package fr.kizafox.theguywhoescape.game.status.sub;

import fr.kizafox.theguywhoescape.game.client.window.Game;
import fr.kizafox.theguywhoescape.game.status.GameStatus;
import fr.kizafox.theguywhoescape.game.status.Status;
import fr.kizafox.theguywhoescape.game.status.StatusCore;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static fr.kizafox.theguywhoescape.game.client.window.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class Menu extends Status implements StatusCore {

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawString("MENU", GAME_WIDTH / 2, 200);
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER) GameStatus.STATUS = GameStatus.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
