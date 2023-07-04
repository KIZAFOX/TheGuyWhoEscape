package fr.kizafox.theguywhoescape.game.client.window.inputs;

import fr.kizafox.theguywhoescape.game.client.window.GamePanel;
import fr.kizafox.theguywhoescape.game.status.GameStatus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class KeyBoardInputs implements KeyListener {

    protected final GamePanel gamePanel;

    public KeyBoardInputs(final GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyTyped(final KeyEvent event) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyPressed(final KeyEvent event) {
        switch (GameStatus.STATUS){
            case MENU -> this.gamePanel.getGame().getMenu().keyPressed(event);
            case PLAYING -> this.gamePanel.getGame().getPlaying().keyPressed(event);
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyReleased(final KeyEvent event) {
        switch (GameStatus.STATUS){
            case MENU -> this.gamePanel.getGame().getMenu().keyReleased(event);
            case PLAYING -> this.gamePanel.getGame().getPlaying().keyReleased(event);
        }
    }
}
