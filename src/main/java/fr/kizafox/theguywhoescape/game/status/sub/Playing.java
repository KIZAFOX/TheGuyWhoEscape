package fr.kizafox.theguywhoescape.game.status.sub;

import fr.kizafox.theguywhoescape.game.client.window.Game;
import fr.kizafox.theguywhoescape.game.client.window.ui.overlay.PauseOverlay;
import fr.kizafox.theguywhoescape.game.entities.player.Player;
import fr.kizafox.theguywhoescape.game.levels.LevelManager;
import fr.kizafox.theguywhoescape.game.status.GameStatus;
import fr.kizafox.theguywhoescape.game.status.Status;
import fr.kizafox.theguywhoescape.game.status.StatusCore;

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
public class Playing extends Status implements StatusCore {

    protected final LevelManager levelManager;
    protected final Player player;

    private final PauseOverlay pauseOverlay;

    private boolean paused = false;

    public Playing(final Game game) {
        super(game);

        this.levelManager = new LevelManager(this.getGame());
        this.player = new Player();
        this.player.setLevelData(this.levelManager.getCurrentLevel().levelData());

        this.pauseOverlay = new PauseOverlay(this);
    }

    @Override
    public void update() {
        if(!paused){
            this.levelManager.update();
            this.player.update();
        }else{
            this.pauseOverlay.update();
        }
    }

    @Override
    public void render(final Graphics graphics) {
        this.levelManager.render(graphics);
        this.player.render(graphics);

        if(paused) this.pauseOverlay.render(graphics);

        this.game.getFPSChecker().getFPS(graphics);
    }

    public void mouseDragged(final MouseEvent event){
        if(paused) this.pauseOverlay.mouseDragged(event);
    }

    @Override
    public void mouseClicked(final MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1) this.player.setAttacking(true);
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        if(paused) this.pauseOverlay.mousePressed(event);
    }

    @Override
    public void mouseReleased(final MouseEvent event) {
        if(paused) this.pauseOverlay.mouseReleased(event);
    }

    @Override
    public void mouseMoved(final MouseEvent event) {
        if(paused) this.pauseOverlay.mouseMoved(event);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> this.player.setLeft(true);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> this.player.setRight(true);
            case KeyEvent.VK_SPACE -> this.player.setJump(true);
            case KeyEvent.VK_BACK_SPACE -> GameStatus.STATUS = GameStatus.MENU;
            case KeyEvent.VK_ESCAPE, KeyEvent.VK_P -> paused = !paused;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> this.player.setLeft(false);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> this.player.setRight(false);
            case KeyEvent.VK_SPACE -> this.player.setJump(false);
        }
    }

    public void unpauseGame(){
        paused = false;
    }

    public Player getPlayer() {
        return player;
    }
}
