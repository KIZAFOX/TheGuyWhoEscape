package fr.kizafox.theguywhoescape.game.client.window;

import fr.kizafox.theguywhoescape.game.status.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static fr.kizafox.theguywhoescape.game.client.window.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class GameWindow extends JFrame {

    /**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public GameWindow(final GamePanel gamePanel) throws HeadlessException {
        this.setTitle(NAME + " - " + VERSION);
        this.add(gamePanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                if(GameStatus.STATUS == GameStatus.PLAYING) gamePanel.getGame().getPlaying().getPlayer().resetDirections();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });
    }
}
