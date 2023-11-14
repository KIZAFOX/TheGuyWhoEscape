package fr.kizafox.theguywhoescape.game.client.window;

import fr.kizafox.theguywhoescape.game.client.inputs.KeyBoardInputs;
import fr.kizafox.theguywhoescape.game.client.inputs.MouseInputs;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class GamePanel extends JPanel {

    protected final Game game;

    public GamePanel(final Game game){
        this.game = game;

        final Dimension dimension = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);

        this.addKeyListener(new KeyBoardInputs(this));

        final MouseInputs mouseInputs =new MouseInputs(this);
        this.addMouseListener(mouseInputs);
        this.addMouseMotionListener(mouseInputs);
    }

    /**
     * Calls the UI delegate's paint method, if the UI delegate
     * is non-<code>null</code>.  We pass the delegate a copy of the
     * <code>Graphics</code> object to protect the rest of the
     * paint code from irrevocable changes
     * (for example, <code>Graphics.translate</code>).
     * <p>
     * If you override this in a subclass you should not make permanent
     * changes to the passed in <code>Graphics</code>. For example, you
     * should not alter the clip <code>Rectangle</code> or modify the
     * transform. If you need to do these operations you may find it
     * easier to create a new <code>Graphics</code> from the passed in
     * <code>Graphics</code> and manipulate it. Further, if you do not
     * invoke super's implementation you must honor the opaque property, that is
     * if this component is opaque, you must completely fill in the background
     * in an opaque color. If you do not honor the opaque property you
     * will likely see visual artifacts.
     * <p>
     * The passed in <code>Graphics</code> object might
     * have a transform other than the identify transform
     * installed on it.  In this case, you might get
     * unexpected results if you cumulatively apply
     * another transform.
     *
     * @param graphics the <code>Graphics</code> object to protect
     * @see #paint
     * @see ComponentUI
     */
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.game.render(graphics);
    }

    public Game getGame() {
        return game;
    }
}
