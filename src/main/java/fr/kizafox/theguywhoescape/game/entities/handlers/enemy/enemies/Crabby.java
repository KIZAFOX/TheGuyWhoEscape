package fr.kizafox.theguywhoescape.game.entities.handlers.enemy.enemies;

import fr.kizafox.theguywhoescape.game.client.settings.GameSettings;
import fr.kizafox.theguywhoescape.game.entities.handlers.enemy.Enemy;
import fr.kizafox.theguywhoescape.game.entities.handlers.player.Player;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static fr.kizafox.theguywhoescape.game.utils.Constants.EnemyConstants.*;
import static fr.kizafox.theguywhoescape.game.utils.Constants.Directions.*;

/**
 * Created at 16/11/2023 at 17:35
 * Made by @KIZAFOX (twitter)
 **/

public class Crabby extends Enemy {

    protected Rectangle2D.Float attackBox;
    private final int attackBoxOffsetX;

    public Crabby(int x, int y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        this.initHitBox(x, y, (int) (22 * GameSettings.SCALE), (int) (19 * GameSettings.SCALE));

        this.attackBox = new Rectangle2D.Float(x ,y, (int) (82 * GameSettings.SCALE), (int) (19 * GameSettings.SCALE));
        this.attackBoxOffsetX = (int) (GameSettings.SCALE * 30);
    }

    public void update(final int[][] levelData, final Player player) {
        this.updateAnimationTick();

        if(this.firstUpdate) this.firstUpdateCheck(levelData);

        if(this.inAir){
            this.updateInAir(levelData);
        }else{
            switch (this.enemyState){
                case IDLE:
                    this.newState(RUNNING);
                    break;
                case RUNNING:
                    if(this.canSeePlayer(levelData, player)) {
                        this.turnTowardsPlayer(player);
                        if(this.isPlayerCloseForAttack(player)){
                            this.newState(ATTACK_1);
                        }
                    }

                    this.move(levelData);
                    break;
                case ATTACK_1:
                    if(this.animationIndex == 0) this.attackChecked = false;
                    if(this.animationIndex == 3 && !this.attackChecked) this.checkEnemyHit(this.attackBox, player);
                    break;
                case HIT:
                    break;
                default:break;
            }
        }

        this.attackBox.x = this.hitBox.x - this.attackBoxOffsetX;
        this.attackBox.y = this.hitBox.y;
    }

    public void render(final Graphics graphics, final int xLevelOffset){
        graphics.setColor(Color.RED);
        graphics.drawRect((int) (this.attackBox.x - xLevelOffset), (int) this.attackBox.y, (int) this.attackBox.width, (int) this.attackBox.height);
    }

    public int flipX(){
        if(this.walkDirection == RIGHT){
            return this.width;
        }else{
            return 0;
        }
    }

    public int flipWidth(){
        if(this.walkDirection == RIGHT){
            return -1;
        }else{
            return 1;
        }
    }

    public void resetEnemy() {
        this.hitBox.x = this.x;
        this.hitBox.y = this.y;
        this.firstUpdate = true;
        this.currentHealth = maxHealth;
        this.newState(IDLE);
        this.active = true;
        this.fallSpeed = 0;
    }
}
