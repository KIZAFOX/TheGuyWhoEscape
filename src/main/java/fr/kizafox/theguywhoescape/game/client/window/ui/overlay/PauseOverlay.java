package fr.kizafox.theguywhoescape.game.client.window.ui.overlay;

import fr.kizafox.theguywhoescape.game.client.window.ui.button.PauseButton;
import fr.kizafox.theguywhoescape.game.client.window.ui.button.SoundButton;
import fr.kizafox.theguywhoescape.game.client.window.ui.button.UrmButton;
import fr.kizafox.theguywhoescape.game.client.window.ui.button.VolumeButton;
import fr.kizafox.theguywhoescape.game.status.GameStatus;
import fr.kizafox.theguywhoescape.game.status.sub.Playing;
import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.entities.utils.EntityConstants.UI.PauseButtons.*;
import static fr.kizafox.theguywhoescape.game.entities.utils.EntityConstants.UI.URMButtons.*;
import static fr.kizafox.theguywhoescape.game.entities.utils.EntityConstants.UI.VolumeButtons.*;
import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

/**
 * Created at 14/11/2023 at 15:33
 * Made by @KIZAFOX (twitter)
 **/

public class PauseOverlay {

    protected final Playing playing;

    private BufferedImage backgroundImage;
    private int width, height, x, y;

    private SoundButton musicButton, sfxButton;
    private UrmButton menuButton, replayButton, unpauseButton;
    private VolumeButton volumeButton;

    public PauseOverlay(final Playing playing) {
        this.playing = playing;

        this.loadBackground();
        this.createSoundButtons();
        this.createURMButtons();
        this.createVolumeButtons();
    }

    private void loadBackground() {
        this.backgroundImage = ImageRenderer.loadSprite(ImageRenderer.PAUSE_BACKGROUND);

        this.width = (int) (this.backgroundImage.getWidth() * SCALE);
        this.height = (int) (this.backgroundImage.getHeight() * SCALE);
        this.x = ((GAME_WIDTH / 2) - (this.width / 2));
        this.y = (int) (25 * SCALE);
    }

    private void createSoundButtons() {
        final int
                soundX = (int) (450 * SCALE),
                musicY = (int) (140 * SCALE),
                sfxY = (int) (186 * SCALE);

        this.musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        this.sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void createURMButtons() {
        final int
                menuX = (int) (313 * SCALE),
                replayX = (int) (387 * SCALE),
                unpauseX = (int) (462 * SCALE),
                bY = (int) (325 * SCALE);

        this.menuButton = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        this.replayButton = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        this.unpauseButton = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    private void createVolumeButtons() {
        int
                volumeX = (int) (309 * SCALE),
                volumeY = (int) (278 * SCALE);

        this.volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    public void update(){
        this.musicButton.update();
        this.sfxButton.update();

        this.menuButton.update();
        this.replayButton.update();
        this.unpauseButton.update();

        this.volumeButton.update();
    }

    public void render(final Graphics graphics){
        graphics.drawImage(this.backgroundImage, this.x, this.y, this.width, this.height, null);

        this.musicButton.render(graphics);
        this.sfxButton.render(graphics);

        this.replayButton.render(graphics);
        this.menuButton.render(graphics);
        this.unpauseButton.render(graphics);

        this.volumeButton.render(graphics);
    }

    public void mouseDragged(final MouseEvent event){
        if(this.volumeButton.isMousePressed()){
            this.volumeButton.changeX(event.getX());
        }
    }

    public void mousePressed(final MouseEvent event) {
        if(this.isIn(event, this.musicButton)){
            this.musicButton.setMousePressed(true);
        }else if(this.isIn(event, this.sfxButton)){
            this.sfxButton.setMousePressed(true);
        }else if(this.isIn(event, this.menuButton)){
            this.menuButton.setMousePressed(true);
        }else if(this.isIn(event, this.replayButton)){
            this.replayButton.setMousePressed(true);
        }else if(this.isIn(event, this.unpauseButton)){
            this.unpauseButton.setMousePressed(true);
        }else if(this.isIn(event, this.volumeButton)){
            this.volumeButton.setMousePressed(true);
        }
    }

    public void mouseReleased(final MouseEvent event) {
        if(this.isIn(event, this.musicButton)){
            if(this.musicButton.isMousePressed()){
                this.musicButton.setMuted(!this.musicButton.isMuted());
            }
        }else if(this.isIn(event, this.sfxButton)){
            if(this.sfxButton.isMousePressed()){
                this.sfxButton.setMuted(!this.sfxButton.isMuted());
            }
        }else if(this.isIn(event, this.menuButton)){
            if(this.menuButton.isMousePressed()){
                GameStatus.STATUS = GameStatus.MENU;
                this.playing.unpauseGame();
            }
        }else if(this.isIn(event, this.replayButton)){
            if(this.replayButton.isMousePressed()){
                System.out.println("Replay level stage!");
            }
        }else if(this.isIn(event, this.unpauseButton)){
            if(this.unpauseButton.isMousePressed()){
                this.playing.unpauseGame();
            }
        }

        this.musicButton.resetBooleans();
        this.sfxButton.resetBooleans();
        this.menuButton.resetBooleans();
        this.replayButton.resetBooleans();
        this.unpauseButton.resetBooleans();
        this.volumeButton.resetBooleans();
    }

    public void mouseMoved(final MouseEvent event) {
        this.musicButton.setMouseOver(false);
        this.sfxButton.setMouseOver(false);
        this.menuButton.setMouseOver(false);
        this.replayButton.setMouseOver(false);
        this.unpauseButton.setMouseOver(false);
        this.volumeButton.setMouseOver(false);

        if(this.isIn(event, this.musicButton)){
            this.musicButton.setMouseOver(true);
        }else if(this.isIn(event, this.sfxButton)){
            this.sfxButton.setMouseOver(true);
        }else if(this.isIn(event, this.menuButton)){
            this.menuButton.setMouseOver(true);
        }else if(this.isIn(event, this.replayButton)){
            this.replayButton.setMouseOver(true);
        }else if(this.isIn(event, this.unpauseButton)){
            this.unpauseButton.setMouseOver(true);
        }else if(this.isIn(event, this.volumeButton)){
            this.volumeButton.setMouseOver(true);
        }
    }

    private boolean isIn(final MouseEvent mouseEvent, final PauseButton pauseButton) {
        return pauseButton.getBounds().contains(mouseEvent.getX(), mouseEvent.getY());
    }
}
