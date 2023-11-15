package fr.kizafox.theguywhoescape.game.client.window.ui.button;

import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.entities.utils.EntityConstants.UI.PauseButtons.*;

/**
 * Created at 14/11/2023 at 15:50
 * Made by @KIZAFOX (twitter)
 **/

public class SoundButton extends PauseButton{

    private BufferedImage[][] soundImages;

    private boolean
            mouseOver, mousePressed,
            muted;
    private int rowIndex, colIndex;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.loadSoundImages();
    }

    private void loadSoundImages() {
        final BufferedImage temp = ImageRenderer.loadSprite(ImageRenderer.SOUND_BUTTONS);
        this.soundImages = new BufferedImage[2][3];

        for(int i = 0; i < this.soundImages.length; i++){
            for(int j = 0; j < this.soundImages[i].length; j++){
                this.soundImages[i][j] = temp.getSubimage(j * SOUND_SIZE_DEFAULT, i * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
            }
        }
    }

    public void update(){
        if(this.muted){
            this.rowIndex = 1;
        }else{
            this.rowIndex = 0;
        }

        this.colIndex = 0;

        if(this.mouseOver) {
            this.colIndex = 1;
        }

        if(mousePressed){
            this.colIndex = 2;
        }
    }

    public void render(final Graphics graphics){
        graphics.drawImage(this.soundImages[this.rowIndex][this.colIndex], x, y, width, height, null);
    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public BufferedImage[][] getSoundImages() {
        return soundImages;
    }

    public void setSoundImages(BufferedImage[][] soundImages) {
        this.soundImages = soundImages;
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

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
