package fr.kizafox.theguywhoescape.game.client.inputs;

import fr.kizafox.theguywhoescape.game.client.window.GamePanel;
import fr.kizafox.theguywhoescape.game.status.GameStatus;

import java.awt.event.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    protected final GamePanel gamePanel;

    public MouseInputs(final GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseClicked(final MouseEvent event) {
        if (GameStatus.STATUS.equals(GameStatus.PLAYING)) {
            this.gamePanel.getGame().getPlaying().mouseClicked(event);
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mousePressed(final MouseEvent event) {
        switch (GameStatus.STATUS){
            case MENU -> this.gamePanel.getGame().getMenu().mousePressed(event);
            case PLAYING -> this.gamePanel.getGame().getPlaying().mousePressed(event);
        }
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseReleased(final MouseEvent event) {
        switch (GameStatus.STATUS){
            case MENU -> this.gamePanel.getGame().getMenu().mouseReleased(event);
            case PLAYING -> this.gamePanel.getGame().getPlaying().mouseReleased(event);
        }
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseEntered(final MouseEvent event) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseExited(final MouseEvent event) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseDragged(final MouseEvent event) {

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param event the event to be processed
     */
    @Override
    public void mouseMoved(final MouseEvent event) {
        switch (GameStatus.STATUS){
            case MENU -> this.gamePanel.getGame().getMenu().mouseMoved(event);
            case PLAYING -> this.gamePanel.getGame().getPlaying().mouseMoved(event);
        }
    }
}
