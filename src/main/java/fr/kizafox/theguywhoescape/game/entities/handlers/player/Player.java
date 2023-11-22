package fr.kizafox.theguywhoescape.game.entities.handlers.player;

import fr.kizafox.theguywhoescape.game.client.settings.GameSettings;
import fr.kizafox.theguywhoescape.game.status.sub.Playing;
import fr.kizafox.theguywhoescape.game.utils.FPSChecker;
import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.SCALE;
import static fr.kizafox.theguywhoescape.game.utils.Constants.PlayerConstants.*;
import static fr.kizafox.theguywhoescape.game.entities.helper.EntityHelper.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class Player extends EntityPlayer {

    public Player(final Playing playing) {
        this.playing = playing;
        this.initHitBox(x, y, (int) (20 * SCALE), (int) (27 * SCALE));

        final BufferedImage playerImage = ImageRenderer.loadSprite(ImageRenderer.PLAYER_ATLAS);

        this.animations = new BufferedImage[7][8];

        for (int i = 0; i < this.animations.length; i++) {
            for (int j = 0; j < this.animations[i].length; j++) {
                this.animations[i][j] = playerImage.getSubimage(j * 64, i * 40, 64, 40);
            }
        }

        this.statusBarImage = ImageRenderer.loadSprite(ImageRenderer.STATUS_BAR);

        this.attackBox = new Rectangle2D.Float(x, y, (int) (20 * SCALE), (int) (20 * SCALE));
    }

    @Override
    public void update() {
        this.updatePosition();
        this.updateAnimationTick();
        this.setAnimation();
        this.updateHealthBar();
        this.updateAttackBox();

        if(this.attacking) this.checkAttack();

        if(this.currentHealth <= 0){
            this.playing.setGameOver(true);
        }
    }

    @Override
    public void update(int[][] levelData) {
    }

    @Override
    public void render(final Graphics graphics, final int levelOffset) {
        graphics.drawImage(this.animations[this.playerAction][this.animationIndex],
                (int) (this.hitBox.x - this.xDrawOffset) - levelOffset + this.flipX,
                (int) (this.hitBox.y - yDrawOffset),
                this.width * this.flipW, this.height, null);
        this.renderHitBox(graphics, levelOffset);

        graphics.drawImage(statusBarImage, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        graphics.setColor(Color.RED);
        graphics.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);

        graphics.setColor(Color.RED);
        graphics.drawRect((int) attackBox.x - levelOffset, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

    private void updateXPosition(float xSpeed) {
        if (canMoveHere(this.hitBox.x + xSpeed, this.hitBox.y, this.hitBox.width, this.hitBox.height, this.levelData)) {
            this.hitBox.x += xSpeed;
        } else {
            this.hitBox.x = getEntityXPosition(this.hitBox, xSpeed);
        }
    }

    private void jump() {
        if (this.inAir) return;

        this.inAir = true;
        this.airSpeed = this.jumpSpeed;
    }

    private void resetInAir() {
        this.inAir = false;
        this.airSpeed = 0;
    }

    private void updatePosition() {
        moving = false;

        if (jump) jump();

        if (!inAir){
            if ((!left && !right) || (right && left)) return;
        }

        float xSpeed = 0;

        if (left){
            xSpeed -= speed;
            this.flipX = this.width;
            this.flipW = -1;
        }

        if (right){
            xSpeed += speed;
            this.flipX = 0;
            this.flipW = 1;
        }

        if (!inAir){
            if (!isEntityOnFloor(hitBox, levelData)) inAir = true;
        }

        if (inAir) {
            if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
                hitBox.y += airSpeed;
                airSpeed += gravity;
                this.updateXPosition(xSpeed);
            } else {
                hitBox.y = getEntityYPosition(hitBox, airSpeed);
                if(airSpeed > 0){
                    resetInAir();
                }else{
                    airSpeed = fallSpeedAfterCollision;
                }
                this.updateXPosition(xSpeed);
            }
        } else{
            this.updateXPosition(xSpeed);
        }
        moving = true;
    }

    private void checkAttack() {
        if(this.attackChecked || this.animationIndex != 1) return;

        this.attackChecked = true;
        this.playing.checkEnemyHit(this.attackBox);
    }

    private void updateAnimationTick() {
        this.animationTick++;

        if (this.animationTick >= FPSChecker.MAX_FPS / 8) {
            this.animationTick = 0;
            this.animationIndex++;

            if (this.animationIndex >= getSpriteAmount(playerAction)) {
                this.animationIndex = 0;
                this.attacking = false;
                this.attackChecked = false;
            }
        }
    }

    private void setAnimation() {
        int startAni = this.playerAction;

        if(this.moving){
            this.playerAction = RUNNING;
        }else{
            this.playerAction = IDLE;
        }

        if(this.inAir) {
            if(this.airSpeed < 0){
                this.playerAction = JUMPING;
            }else{
                this.playerAction = FALLING;
            }

        }

        if(this.attacking){
            this.playerAction = ATTACK_1;

            if(startAni != ATTACK_1){
                this.animationIndex = 1;
                this.animationTick = 0;
                return;
            }
        }

        if(startAni != this.playerAction) {
            this.animationTick = 0;
            this.animationIndex = 0;
        }
    }

    private void updateHealthBar() {
        this.healthWidth = (int) ((this.currentHealth / (float) this.maxHealth) * this.healthBarWidth);
    }

    private void updateAttackBox() {
        if (right){
            attackBox.x = hitBox.x + hitBox.width + (int) (SCALE * 10);
        } else if (left){
            attackBox.x = hitBox.x - hitBox.width - (int) (SCALE * 10);
        }
        attackBox.y = hitBox.y + (SCALE * 10);
    }

    public void changeHealth(final int value){
        this.currentHealth += value;

        if(this.currentHealth <= 0){
            this.currentHealth = 0;
            //lose
        }else if(this.currentHealth >= this.maxHealth){
            this.currentHealth = this.maxHealth;
        }
    }

    public void setLevelData(int[][] levelData) {
        this.levelData = levelData;

        if(!isEntityOnFloor(this.hitBox, levelData)) {
            this.inAir = true;
        }
    }

    public void resetDirections() {
        this.left = false;
        this.right = false;
    }

    public void resetAll() {
        this.resetDirections();
        this.inAir = false;
        this.attacking = false;
        this.moving = false;
        this.playerAction = IDLE;
        this.currentHealth = this.maxHealth;
        this.hitBox.x = this.x;
        this.hitBox.y = this.y;
        this.attackBox.x = hitBox.x + hitBox.width + (int) (SCALE * 10);
        this.attackBox.y = hitBox.y + (SCALE * 10);

        if(!isEntityOnFloor(this.hitBox, this.levelData)) this.inAir = true;
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
