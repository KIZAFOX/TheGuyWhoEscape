package fr.kizafox.theguywhoescape.game.entities.player.handlers;

import fr.kizafox.theguywhoescape.game.entities.Entity;

import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.SCALE;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public abstract class CraftPlayer extends Entity {

    public BufferedImage[][] animations;
    public int animationTick, animationIndex, playerAction;
    public boolean moving, attacking, left, right, jump, inAir;
    public int[][] levelData;
    public float xDrawOffset, yDrawOffset, airSpeed, gravity, jumpSpeed, fallSpeedAfterCollision, speed;

    public CraftPlayer(BufferedImage[][] animations, int animationTick, int animationIndex, int playerAction, boolean moving, boolean attacking, boolean left, boolean right, boolean jump, boolean inAir, int[][] levelData, float xDrawOffset, float yDrawOffset, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, float speed) {
        super(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
        this.animations = animations;
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

    public float getXDrawOffset() {
        return xDrawOffset;
    }

    public void setXDrawOffset(float xDrawOffset) {
        this.xDrawOffset = xDrawOffset;
    }

    public float getYDrawOffset() {
        return yDrawOffset;
    }

    public void setYDrawOffset(float yDrawOffset) {
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
}