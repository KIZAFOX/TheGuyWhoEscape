package fr.kizafox.theguywhoescape.game.entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public abstract void update(final int[][] levelData);

    public abstract void render(final Graphics graphics, final int levelOffset);

    protected void renderHitBox(final Graphics graphics, final int levelOffset){
        graphics.setColor(Color.RED);
        graphics.drawRect((int) this.hitBox.x - levelOffset, (int) this.hitBox.y, (int) this.hitBox.width, (int) this.hitBox.height);
    }

    protected void initHitBox(final float x, final float y, final int width, final int height){
        this.hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
}
