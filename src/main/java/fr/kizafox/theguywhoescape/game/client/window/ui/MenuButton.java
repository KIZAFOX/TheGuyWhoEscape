package fr.kizafox.theguywhoescape.game.client.window.ui;

import fr.kizafox.theguywhoescape.game.status.GameStatus;
import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.entities.utils.EntityConstants.UI.Buttons.*;

/**
 * Created at 14/11/2023 at 02:59
 * Made by @KIZAFOX (twitter)
 **/

public class MenuButton {

    private final int xPos, yPos, rowIndex;
    private final GameStatus status;

    private BufferedImage[] images;
    private int index;
    private boolean mouseOver, mousePressed;;

    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, GameStatus status) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.status = status;
        this.loadImages();
        this.initBounds();
    }

    private void loadImages() {
        this.images = new BufferedImage[3];
        final BufferedImage temp = ImageRenderer.loadSprite(ImageRenderer.MENU_BUTTONS);

        for(int i = 0; i < this.images.length; i++){
            this.images[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    private void initBounds() {
        this.bounds = new Rectangle((this.xPos - (B_WIDTH / 2)), yPos, B_WIDTH, B_HEIGHT);
    }

    public void draw(final Graphics graphics){
        graphics.drawImage(this.images[this.index], (this.xPos - (B_WIDTH / 2)), this.yPos, B_WIDTH, B_HEIGHT, null);
    }

    public void update(){
        this.index = 0;
        if(this.mouseOver){
            this.index = 1;
        }else if(this.mousePressed){
            this.index = 2;
        }
    }

    public void applyGameStatus(){
        GameStatus.STATUS = status;
    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public GameStatus getStatus() {
        return status;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
