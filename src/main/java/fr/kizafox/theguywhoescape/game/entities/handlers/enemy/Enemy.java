package fr.kizafox.theguywhoescape.game.entities.handlers.enemy;

import fr.kizafox.theguywhoescape.game.entities.handlers.player.Player;
import fr.kizafox.theguywhoescape.game.utils.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;
import static fr.kizafox.theguywhoescape.game.utils.Constants.EnemyConstants.*;
import static fr.kizafox.theguywhoescape.game.utils.Constants.Directions.*;
import static fr.kizafox.theguywhoescape.game.entities.helper.EntityHelper.*;

/**
 * Created at 16/11/2023 at 17:11
 * Made by @KIZAFOX (twitter)
 **/

public abstract class Enemy extends EntityEnemy {

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height, 0, 25, 0, 0, enemyType, LEFT, 0, 0, 0, true, false, true, false, 0, 0.04F * SCALE, .5F * SCALE, TILES_SIZE);
        this.initHitBox(x, y, (int) (20 * SCALE), (int) (27 * SCALE));

        this.maxHealth = getEnemyMaxHealth(this.enemyType);
        this.currentHealth = this.maxHealth;
    }

    protected void updateWalkDirection(){
        if(this.walkDirection == LEFT){
            this.walkDirection = RIGHT;
        }else{
            this.walkDirection = LEFT;
        }
    }

    protected void firstUpdateCheck(final int[][] lvlData) {
        if (!isEntityOnFloor(hitBox, lvlData)) inAir = true;
        firstUpdate = false;
    }

    protected void updateInAir(final int[][] lvlData) {
        if (canMoveHere(hitBox.x, hitBox.y + fallSpeed, hitBox.width, hitBox.height, lvlData)) {
            hitBox.y += fallSpeed;
            fallSpeed += gravity;
        } else {
            inAir = false;
            hitBox.y = getEntityYPosition(hitBox, fallSpeed);
            tileY = (int) (hitBox.y / TILES_SIZE);
        }
    }

    protected void move(final int[][] lvlData) {
        final float xSpeed;

        if(walkDirection == LEFT){
            xSpeed = -walkSpeed;
        }else{
            xSpeed = walkSpeed;
        }

        if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)){
            if (isFloor(hitBox, xSpeed, lvlData)) {
                hitBox.x += xSpeed;
                return;
            }
        }

        this.updateWalkDirection();
    }

    protected void turnTowardsPlayer(final Player player) {
        if(player.getHitBox().x > hitBox.x){
            walkDirection = RIGHT;
        }else{
            walkDirection = LEFT;
        }
    }

    protected boolean isPlayerInRange(final Player player) {
        return (int) Math.abs(player.getHitBox().x - hitBox.x) <= attackDistance * 5;
    }

    protected boolean isPlayerCloseForAttack(final Player player) {
        return (int) Math.abs(player.getHitBox().x - hitBox.x) <= attackDistance;
    }

    protected boolean canSeePlayer(final int[][] lvlData, Player player) {
        final int playerTileY = (int) (player.getHitBox().y / TILES_SIZE);
        if (playerTileY == tileY){
            if (isPlayerInRange(player)) {
                return isSightClear(lvlData, hitBox, player.getHitBox(), tileY);
            }
        }
        return false;
    }

    protected void newState(final int enemyState){
        this.enemyState = enemyState;
        this.animationTick = 0;
        this.animationIndex = 0;
    }

    public void hurt(final int amount){
        this.currentHealth -= amount;

        if(this.currentHealth <= 0){
            this.newState(DEAD);
        }else{
            this.newState(HIT);
        }
    }

    protected void updateAnimationTick(){
        this.animationTick++;

        if(this.animationTick >= this.animationSpeed){
            this.animationTick = 0;
            this.animationIndex++;

            if(this.animationIndex >= getSpriteAmount(this.enemyType, this.enemyState)){
                this.animationIndex = 0;

                switch(this.enemyState){
                    case ATTACK_1, HIT -> this.enemyState = IDLE;
                    case DEAD -> active = false;
                }
            }
        }
    }

    protected void checkEnemyHit(final Rectangle2D.Float attackBox, final Player player){
        if(attackBox.intersects(player.getHitBox())){
            player.changeHealth(-getEnemyDamage(this.enemyType));
        }

        this.attackChecked = true;
    }

    @Override
    public void update() {
    }

    @Override
    public void update(final int[][] levelData) {

    }

    @Override
    public void render(Graphics graphics, int levelOffset) {

    }

    public int getAnimationIndex() {
        return super.getAnimationIndex();
    }

    public int getEnemyState() {
        return super.getEnemyState();
    }
}
