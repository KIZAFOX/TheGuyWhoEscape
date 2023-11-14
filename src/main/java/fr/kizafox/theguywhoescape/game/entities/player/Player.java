package fr.kizafox.theguywhoescape.game.entities.player;

import fr.kizafox.theguywhoescape.game.entities.player.handlers.EntityPlayer;
import fr.kizafox.theguywhoescape.game.utils.FPSChecker;
import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.entities.utils.EntityConstants.PlayerConstants.*;
import static fr.kizafox.theguywhoescape.game.entities.utils.EntityHelper.*;
import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class Player extends EntityPlayer {

    public Player() {
        this.initHitBox(x, y, (int) (20 * SCALE), (int) (27 * SCALE));

        final BufferedImage playerImage = ImageRenderer.loadSprite(ImageRenderer.PLAYER_ATLAS);

        this.animations = new BufferedImage[9][6];

        for(int i = 0; i < this.animations.length; i++){
            for(int j = 0; j < this.animations[i].length; j++){
                this.animations[i][j] = playerImage.getSubimage(j * 64, i * 40, 64, 40);
            }
        }
    }

    @Override
    public void update() {
        this.updatePosition();
        this.updateAnimationTick();
        this.setAnimation();
    }

    @Override
    public void render(final Graphics graphics) {
        graphics.drawImage(animations[playerAction][this.animationIndex], (int) (this.hitBox.x - xDrawOffset), (int) (this.hitBox.y - yDrawOffset), width, height, null);
        this.renderHitBox(graphics);
    }

    public void resetDirections() {
        this.left = false;
        this.right = false;
    }

    private void updateXPosition(float xSpeed) {
        if(canMoveHere(this.hitBox.x + xSpeed, this.hitBox.y, this.hitBox.width, this.hitBox.height, this.levelData)) {
            this.hitBox.x += xSpeed;
        }else{
            this.hitBox.x = getEntityXPosition(this.hitBox, xSpeed);
        }
    }

    private void jump() {
        if(this.inAir) return;

        this.inAir = true;
        this.airSpeed = this.jumpSpeed;
    }

    private void resetInAir() {
        this.inAir = false;
        this.airSpeed = 0;
    }

    private void updatePosition() {
        this.moving = false;

        if(this.jump){
            this.jump();
        }

        if(!left && !right && !inAir) return;

        float xSpeed = 0;

        if (this.left) {
            xSpeed -= this.getSpeed();
        }

        if (this.right) {
            xSpeed += this.getSpeed();
        }

        if(!this.inAir){
            if(isEntityOnFloor(this.hitBox, this.levelData)){
                this.inAir = true;
            }
        }

        if(inAir){
            if(canMoveHere(this.hitBox.x, this.hitBox.y + this.airSpeed, this.hitBox.width, this.hitBox.height, this.levelData)) {
                this.hitBox.y += this.airSpeed;
                this.airSpeed += this.gravity;
                this.updateXPosition(xSpeed);
            }else{
                this.hitBox.y = getEntityYPosition(this.hitBox, this.airSpeed);

                if(this.airSpeed > 0){
                    resetInAir();
                }else{
                    this.airSpeed = this.fallSpeedAfterCollision;
                }
                this.updateXPosition(xSpeed);
            }
        }else{
            this.updateXPosition(xSpeed);
        }
        this.moving = true;
    }

    private void updateAnimationTick(){
        this.animationTick++;

        if(this.animationTick >= FPSChecker.MAX_FPS / 8){
            this.animationTick = 0;
            this.animationIndex++;

            if(this.animationIndex >= getSpriteAmount(playerAction)){
                this.animationIndex = 0;
                this.attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAnimation = this.playerAction;

        if(this.moving){
            this.playerAction = RUNNING;
        }else{
            this.playerAction = IDLE;
        }

        if(this.inAir){
            if(this.airSpeed < 0){
                this.playerAction = JUMPING;
            }else{
                this.playerAction = FALLING;
            }
        }

        if(this.attacking){
            this.playerAction = ATTACK_1;
        }

        if(startAnimation != this.playerAction){
            this.animationTick = 0;
            this.animationIndex = 0;
        }
    }

    public void setLevelData(int[][] levelData) {
        this.levelData = levelData;

        if(isEntityOnFloor(this.hitBox, this.levelData)){
            this.inAir = true;
        }
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
}