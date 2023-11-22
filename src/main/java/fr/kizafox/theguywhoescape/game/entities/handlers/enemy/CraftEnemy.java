package fr.kizafox.theguywhoescape.game.entities.handlers.enemy;

import fr.kizafox.theguywhoescape.game.entities.Entity;

/**
 * Created at 16/11/2023 at 17:03
 * Made by @KIZAFOX (twitter)
 **/

public abstract class CraftEnemy extends Entity {

    protected int animationTick, animationSpeed, animationIndex, enemyState, enemyType, walkDirection, tileY, maxHealth, currentHealth;
    protected boolean firstUpdate, inAir, active, attackChecked;
    protected float fallSpeed, gravity, walkSpeed, attackDistance;

    public CraftEnemy(float x, float y, int width, int height, int animationTick, int animationSpeed, int animationIndex, int enemyState, int enemyType, int walkDirection, int tileY, int maxHealth, int currentHealth, boolean firstUpdate, boolean inAir, boolean active, boolean attackChecked, float fallSpeed, float gravity, float walkSpeed, float attackDistance) {
        super(x, y, width, height);
        this.animationTick = animationTick;
        this.animationSpeed = animationSpeed;
        this.animationIndex = animationIndex;
        this.enemyState = enemyState;
        this.enemyType = enemyType;
        this.walkDirection = walkDirection;
        this.tileY = tileY;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.firstUpdate = firstUpdate;
        this.inAir = inAir;
        this.active = active;
        this.attackChecked = attackChecked;
        this.fallSpeed = fallSpeed;
        this.gravity = gravity;
        this.walkSpeed = walkSpeed;
        this.attackDistance = attackDistance;
    }

    public int getAnimationTick() {
        return animationTick;
    }

    public void setAnimationTick(int animationTick) {
        this.animationTick = animationTick;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public void setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }

    public void setEnemyState(int enemyState) {
        this.enemyState = enemyState;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }

    public int getWalkDirection() {
        return walkDirection;
    }

    public void setWalkDirection(int walkDirection) {
        this.walkDirection = walkDirection;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
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

    public boolean isFirstUpdate() {
        return firstUpdate;
    }

    public void setFirstUpdate(boolean firstUpdate) {
        this.firstUpdate = firstUpdate;
    }

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAttackChecked() {
        return attackChecked;
    }

    public void setAttackChecked(boolean attackChecked) {
        this.attackChecked = attackChecked;
    }

    public float getFallSpeed() {
        return fallSpeed;
    }

    public void setFallSpeed(float fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public float getAttackDistance() {
        return attackDistance;
    }

    public void setAttackDistance(float attackDistance) {
        this.attackDistance = attackDistance;
    }
}
