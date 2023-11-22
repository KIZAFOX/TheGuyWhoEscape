package fr.kizafox.theguywhoescape.game.client.window.ui.button;

import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.utils.Constants.UI.URMButtons.*;

/**
 * Created at 14/11/2023 at 21:12
 * Made by @KIZAFOX (twitter)
 **/

public class UrmButton extends PauseButton {

    private BufferedImage[] images;
    private int rowIndex, index;

    private boolean mouseOver, mousePressed;

    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        this.loadImages();
    }

    private void loadImages() {
        final BufferedImage temp = ImageRenderer.loadSprite(ImageRenderer.URM_BUTTONS);
        this.images = new BufferedImage[3];

        for(int i = 0; i < this.images.length; i++){
            this.images[i] = temp.getSubimage(i * URM_SIZE_DEFAULT, rowIndex * URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT);
        }
    }

    public void update(){
        index = 0;

        if(mouseOver) index = 1;

        if(mousePressed) index = 2;
    }

    public void render(final Graphics graphics){
        graphics.drawImage(this.images[index], x, y, URM_SIZE, URM_SIZE, null);
    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
