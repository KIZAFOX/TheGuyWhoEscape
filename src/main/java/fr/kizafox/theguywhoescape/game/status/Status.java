package fr.kizafox.theguywhoescape.game.status;

import fr.kizafox.theguywhoescape.game.client.window.Game;
import fr.kizafox.theguywhoescape.game.client.window.ui.button.MenuButton;

import java.awt.event.MouseEvent;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class Status {

    protected final Game game;

    public Status(final Game game) {
        this.game = game;
    }

    public boolean isIn(final MouseEvent mouseEvent, MenuButton menuButton){
        return menuButton.getBounds().contains(mouseEvent.getX(), mouseEvent.getY());
    }

    public Game getGame() {
        return game;
    }
}
