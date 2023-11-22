package fr.kizafox.theguywhoescape.game.status.sub;

import fr.kizafox.theguywhoescape.game.client.settings.GameSettings;
import fr.kizafox.theguywhoescape.game.client.window.Game;
import fr.kizafox.theguywhoescape.game.client.window.ui.overlay.GameOverOverlay;
import fr.kizafox.theguywhoescape.game.client.window.ui.overlay.PauseOverlay;
import fr.kizafox.theguywhoescape.game.entities.handlers.enemy.EnemyManager;
import fr.kizafox.theguywhoescape.game.entities.handlers.player.Player;
import fr.kizafox.theguywhoescape.game.levels.LevelManager;
import fr.kizafox.theguywhoescape.game.status.GameStatus;
import fr.kizafox.theguywhoescape.game.status.Status;
import fr.kizafox.theguywhoescape.game.status.StatusCore;
import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;
import fr.kizafox.theguywhoescape.game.utils.InfoPrinter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import static fr.kizafox.theguywhoescape.game.utils.Constants.Environnement.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class Playing extends Status implements StatusCore {

    private final BufferedImage backgroundImage, bigCloudsImage, smallCloudsImage;
    private final int[] smallCloudsPosition;

    protected final LevelManager levelManager;
    protected final PauseOverlay pauseOverlay;
    protected final EnemyManager enemyManager;
    protected final GameOverOverlay gameOverOverlay;

    protected final Player player;

    private boolean
            paused = false,
            gameOver = false;

    private int xLvlOffset;
    private final int
            leftBorder = (int) (0.2 * GameSettings.GAME_WIDTH),
            rightBorder = (int) (0.8 * GameSettings.GAME_WIDTH),
            lvlTilesWide = ImageRenderer.getLevelData()[0].length,
            maxTilesOffset = lvlTilesWide - GameSettings.TILES_IN_WIDTH,
            maxLvlOffsetX = maxTilesOffset * GameSettings.TILES_SIZE;

    public Playing(final Game game) {
        super(game);

        this.backgroundImage = ImageRenderer.loadSprite(ImageRenderer.PLAYING_BACKGROUND_IMAGE);
        this.bigCloudsImage = ImageRenderer.loadSprite(ImageRenderer.BIG_CLOUDS);
        this.smallCloudsImage = ImageRenderer.loadSprite(ImageRenderer.SMALL_CLOUDS);
        this.smallCloudsPosition = new int[8];

        for(int i = 0; i < this.smallCloudsPosition.length; i++){
            this.smallCloudsPosition[i] = (int) (90 * GameSettings.SCALE) + new Random().nextInt((int) (150 * GameSettings.SCALE));
        }

        this.levelManager = new LevelManager(this.getGame());
        this.pauseOverlay = new PauseOverlay(this);
        this.enemyManager = new EnemyManager(this);
        this.gameOverOverlay = new GameOverOverlay(this);

        this.player = new Player(this);
        this.player.setLevelData(this.levelManager.getCurrentLevel().levelData());
    }

    @Override
    public void update() {
        if(!this.paused && !this.gameOver){
            this.levelManager.update();
            this.enemyManager.update(this.levelManager.getCurrentLevel().levelData(), this.player);
            this.player.update();

            this.checkCloseToBorder();
        }else{
            this.pauseOverlay.update();
        }
    }

    private void checkCloseToBorder() {
        final int playerX = (int) player.getHitBox().x;
        final int diff = playerX - this.xLvlOffset;

        if(diff > this.rightBorder){
            this.xLvlOffset += diff - this.rightBorder;
        }else if (diff < leftBorder){
            this.xLvlOffset += diff - this.leftBorder;
        }

        if (this.xLvlOffset > this.maxLvlOffsetX){
            this.xLvlOffset = this.maxLvlOffsetX;
        }else if (this.xLvlOffset < 0){
            this.xLvlOffset = 0;
        }
    }

    private void drawClouds(final Graphics graphics) {
        for(int i = 0; i < 3; i++){
            graphics.drawImage(this.bigCloudsImage, i * BIG_CLOUD_WIDTH - (int) (this.xLvlOffset * .3), (int) (204 * GameSettings.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);
        }

        for(int i = 0; i < this.smallCloudsPosition.length; i++){
            graphics.drawImage(this.smallCloudsImage, SMALL_CLOUD_WIDTH * 4 * i - (int) (this.xLvlOffset * .7), this.smallCloudsPosition[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);

        }
    }

    @Override
    public void render(final Graphics graphics) {
        graphics.drawImage(this.backgroundImage, 0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT, null);

        this.drawClouds(graphics);

        this.levelManager.render(graphics, this.xLvlOffset);
        this.enemyManager.render(graphics, this.xLvlOffset);
        this.player.render(graphics, this.xLvlOffset);

        if(this.paused) {
            graphics.setColor(new Color(0, 0, 0, 150));
            graphics.fillRect(0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT);
            this.pauseOverlay.render(graphics);
        }else if(this.gameOver){
            this.gameOverOverlay.render(graphics);
        }

        new InfoPrinter(this, graphics, this.player);
    }

    public void mouseDragged(final MouseEvent event){
        if(!gameOver) if(paused) this.pauseOverlay.mouseDragged(event);
    }

    @Override
    public void mouseClicked(final MouseEvent event) {
        if(!gameOver) if(event.getButton() == MouseEvent.BUTTON1) this.player.setAttacking(true);
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        if(!gameOver) if(paused) this.pauseOverlay.mousePressed(event);
    }

    @Override
    public void mouseReleased(final MouseEvent event) {
        if(!gameOver) if(paused) this.pauseOverlay.mouseReleased(event);
    }

    @Override
    public void mouseMoved(final MouseEvent event) {
        if(!gameOver) if(paused) this.pauseOverlay.mouseMoved(event);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(this.gameOver){
            this.gameOverOverlay.keyPressed(event);
        }else{
            switch (event.getKeyCode()) {
                case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> this.player.setLeft(true);
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> this.player.setRight(true);
                case KeyEvent.VK_SPACE -> this.player.setJump(true);
                case KeyEvent.VK_BACK_SPACE -> GameStatus.STATUS = GameStatus.MENU;
                case KeyEvent.VK_ESCAPE, KeyEvent.VK_P -> paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if(!this.gameOver){
            switch (event.getKeyCode()) {
                case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> this.player.setLeft(false);
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> this.player.setRight(false);
                case KeyEvent.VK_SPACE -> this.player.setJump(false);
            }
        }
    }

    public void checkEnemyHit(final Rectangle2D.Float attackBox) {
        this.enemyManager.checkEnemyHit(attackBox);
    }

    public void resetAll() {
        this.gameOver = false;
        this.paused = false;
        this.player.resetAll();
        this.enemyManager.resetAll();
    }

    public void unpauseGame(){
        paused = false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

}
