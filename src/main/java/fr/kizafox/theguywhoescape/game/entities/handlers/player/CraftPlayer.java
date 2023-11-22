package fr.kizafox.theguywhoescape.game.entities.handlers.player;

import fr.kizafox.theguywhoescape.game.entities.Entity;
import fr.kizafox.theguywhoescape.game.status.sub.Playing;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public abstract class CraftPlayer extends Entity {

    protected BufferedImage[][] animations;
    protected int animationTick, animationIndex, playerAction;
    protected boolean moving, attacking, left, right, jump, inAir;
    protected int[][] levelData;
    protected float xDrawOffset, yDrawOffset, airSpeed, gravity, jumpSpeed, fallSpeedAfterCollision, speed;

    protected BufferedImage statusBarImage;

    protected int statusBarWidth, statusBarHeight, statusBarX, statusBarY;
    protected int healthBarWidth, healthBarHeight, healthBarXStart, healthBarYStart;
    protected int maxHealth, currentHealth, healthWidth;

    protected Rectangle2D.Float attackBox;

    protected int flipX, flipW;

    protected boolean attackChecked;
    protected Playing playing;

    public CraftPlayer(float x, float y, int width, int height, int animationTick, int animationIndex, int playerAction, boolean moving, boolean attacking, boolean left, boolean right, boolean jump, boolean inAir, int[][] levelData, float xDrawOffset, float yDrawOffset, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, float speed, int statusBarWidth, int statusBarHeight, int statusBarX, int statusBarY, int healthBarWidth, int healthBarHeight, int healthBarXStart, int healthBarYStart, int maxHealth, int flipX, int flipW, boolean attackChecked) {
        super(x, y, width, height);
        this.animationTick = animationTick;
        this.animationIndex = animationIndex;
        this.playerAction = playerAction;
        this.moving = moving;
        this.attacking = attacking;
        this.left = left;
        this.right = right;
        this.jump = jump;
        this.inAir = inAir;
        this.levelData = levelData;
        this.xDrawOffset = xDrawOffset;
        this.yDrawOffset = yDrawOffset;
        this.airSpeed = airSpeed;
        this.gravity = gravity;
        this.jumpSpeed = jumpSpeed;
        this.fallSpeedAfterCollision = fallSpeedAfterCollision;
        this.speed = speed;
        this.statusBarWidth = statusBarWidth;
        this.statusBarHeight = statusBarHeight;
        this.statusBarX = statusBarX;
        this.statusBarY = statusBarY;
        this.healthBarWidth = healthBarWidth;
        this.healthBarHeight = healthBarHeight;
        this.healthBarXStart = healthBarXStart;
        this.healthBarYStart = healthBarYStart;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.healthWidth = healthBarWidth;
        this.flipX = flipX;
        this.flipW = flipW;
        this.attackChecked = attackChecked;
    }

    public CraftPlayer(int animationTick, int animationIndex, int playerAction, boolean moving, boolean attacking, boolean left, boolean right, boolean jump, boolean inAir, int[][] levelData, float xDrawOffset, float yDrawOffset, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, float speed, int statusBarWidth, int statusBarHeight, int statusBarX, int statusBarY, int healthBarWidth, int healthBarHeight, int healthBarXStart, int healthBarYStart, int maxHealth, int flipX, int flipW, boolean attackChecked) {
        super(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
        this.animationTick = animationTick;
        this.animationIndex = animationIndex;
        this.playerAction = playerAction;
        this.moving = moving;
        this.attacking = attacking;
        this.left = left;
        this.right = right;
        this.jump = jump;
        this.inAir = inAir;
        this.levelData = levelData;
        this.xDrawOffset = xDrawOffset;
        this.yDrawOffset = yDrawOffset;
        this.airSpeed = airSpeed;
        this.gravity = gravity;
        this.jumpSpeed = jumpSpeed;
        this.fallSpeedAfterCollision = fallSpeedAfterCollision;
        this.speed = speed;
        this.statusBarWidth = statusBarWidth;
        this.statusBarHeight = statusBarHeight;
        this.statusBarX = statusBarX;
        this.statusBarY = statusBarY;
        this.healthBarWidth = healthBarWidth;
        this.healthBarHeight = healthBarHeight;
        this.healthBarXStart = healthBarXStart;
        this.healthBarYStart = healthBarYStart;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.healthWidth = healthBarWidth;
        this.flipX = flipX;
        this.flipW = flipW;
        this.attackChecked = attackChecked;
    }

    public BufferedImage[][] getAnimations() {
        return animations;
    }

    public void setAnimations(BufferedImage[][] animations) {
        this.animations = animations;
    }

    public int getAnimationTick() {
        return animationTick;
    }

    public void setAnimationTick(int animationTick) {
        this.animationTick = animationTick;
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public void setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex;
    }

    public int getPlayerAction() {
        return playerAction;
    }

    public void setPlayerAction(int playerAction) {
        this.playerAction = playerAction;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public int[][] getLevelData() {
        return levelData;
    }

    public void setLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    public float getxDrawOffset() {
        return xDrawOffset;
    }

    public void setxDrawOffset(float xDrawOffset) {
        this.xDrawOffset = xDrawOffset;
    }

    public float getyDrawOffset() {
        return yDrawOffset;
    }

    public void setyDrawOffset(float yDrawOffset) {
        this.yDrawOffset = yDrawOffset;
    }

    public float getAirSpeed() {
        return airSpeed;
    }

    public void setAirSpeed(float airSpeed) {
        this.airSpeed = airSpeed;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getJumpSpeed() {
        return jumpSpeed;
    }

    public void setJumpSpeed(float jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }

    public float getFallSpeedAfterCollision() {
        return fallSpeedAfterCollision;
    }

    public void setFallSpeedAfterCollision(float fallSpeedAfterCollision) {
        this.fallSpeedAfterCollision = fallSpeedAfterCollision;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public BufferedImage getStatusBarImage() {
        return statusBarImage;
    }

    public void setStatusBarImage(BufferedImage statusBarImage) {
        this.statusBarImage = statusBarImage;
    }

    public int getStatusBarWidth() {
        return statusBarWidth;
    }

    public void setStatusBarWidth(int statusBarWidth) {
        this.statusBarWidth = statusBarWidth;
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    public void setStatusBarHeight(int statusBarHeight) {
        this.statusBarHeight = statusBarHeight;
    }

    public int getStatusBarX() {
        return statusBarX;
    }

    public void setStatusBarX(int statusBarX) {
        this.statusBarX = statusBarX;
    }

    public int getStatusBarY() {
        return statusBarY;
    }

    public void setStatusBarY(int statusBarY) {
        this.statusBarY = statusBarY;
    }

    public int getHealthBarWidth() {
        return healthBarWidth;
    }

    public void setHealthBarWidth(int healthBarWidth) {
        this.healthBarWidth = healthBarWidth;
    }

    public int getHealthBarHeight() {
        return healthBarHeight;
    }

    public void setHealthBarHeight(int healthBarHeight) {
        this.healthBarHeight = healthBarHeight;
    }

    public int getHealthBarXStart() {
        return healthBarXStart;
    }

    public void setHealthBarXStart(int healthBarXStart) {
        this.healthBarXStart = healthBarXStart;
    }

    public int getHealthBarYStart() {
        return healthBarYStart;
    }

    public void setHealthBarYStart(int healthBarYStart) {
        this.healthBarYStart = healthBarYStart;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getHealthWidth() {
        return healthWidth;
    }

    public void setHealthWidth(int healthWidth) {
        this.healthWidth = healthWidth;
    }

    public Rectangle2D.Float getAttackBox() {
        return attackBox;
    }

    public void setAttackBox(Rectangle2D.Float attackBox) {
        this.attackBox = attackBox;
    }

    public int getFlipX() {
        return flipX;
    }

    public void setFlipX(int flipX) {
        this.flipX = flipX;
    }

    public int getFlipW() {
        return flipW;
    }

    public void setFlipW(int flipW) {
        this.flipW = flipW;
    }

    public boolean isAttackChecked() {
        return attackChecked;
    }

    public void setAttackChecked(boolean attackChecked) {
        this.attackChecked = attackChecked;
    }

    public Playing getPlaying() {
        return playing;
    }

    public void setPlaying(Playing playing) {
        this.playing = playing;
    }
}