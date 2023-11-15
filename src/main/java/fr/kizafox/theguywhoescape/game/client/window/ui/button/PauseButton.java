package fr.kizafox.theguywhoescape.game.client.window.ui.button;

import java.awt.*;

/**
 * Created at 14/11/2023 at 15:47
 * Made by @KIZAFOX (twitter)
 **/

public class PauseButton {

    protected int x;
    protected final int y;
    protected int width;
    protected final int height;

    protected Rectangle bounds;

    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.initBounds();
    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
