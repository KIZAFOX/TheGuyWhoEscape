package fr.kizafox.theguywhoescape.game.entities.handlers.enemy;

/**
 * Created at 16/11/2023 at 17:10
 * Made by @KIZAFOX (twitter)
 **/

public abstract class EntityEnemy extends CraftEnemy{
    public EntityEnemy(float x, float y, int width, int height, int animationTick, int animationSpeed, int animationIndex, int enemyState, int enemyType, int walkDirection, int tileY, int maxHealth, int currentHealth, boolean firstUpdate, boolean inAir, boolean active, boolean attackChecked, float fallSpeed, float gravity, float walkSpeed, float attackDistance) {
        super(x, y, width, height, animationTick, animationSpeed, animationIndex, enemyState, enemyType, walkDirection, tileY, maxHealth, currentHealth, firstUpdate, inAir, active, attackChecked, fallSpeed, gravity, walkSpeed, attackDistance);
    }
}
