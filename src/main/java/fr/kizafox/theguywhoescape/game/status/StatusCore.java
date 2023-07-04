package fr.kizafox.theguywhoescape.game.status;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public interface StatusCore {

    void update();

    void render(final Graphics graphics);

    void mouseClicked(final MouseEvent event);

    void mousePressed(final MouseEvent event);

    void mouseReleased(final MouseEvent event);

    void mouseMoved(final MouseEvent event);

    void keyPressed(final KeyEvent event);

    void keyReleased(final KeyEvent event);
}
