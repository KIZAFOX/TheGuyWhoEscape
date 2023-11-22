package fr.kizafox.theguywhoescape.game.entities.handlers.enemy;

import fr.kizafox.theguywhoescape.game.entities.handlers.enemy.enemies.Crabby;
import fr.kizafox.theguywhoescape.game.entities.handlers.player.Player;
import fr.kizafox.theguywhoescape.game.status.sub.Playing;
import fr.kizafox.theguywhoescape.game.utils.Colors;
import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static fr.kizafox.theguywhoescape.game.utils.Constants.EnemyConstants.*;

/**
 * Created at 16/11/2023 at 17:39
 * Made by @KIZAFOX (twitter)
 **/

public class EnemyManager {

    protected final Playing playing;

    private BufferedImage[][] crabbyImage;

    private List<Crabby> crabbies = new ArrayList<>();

    public EnemyManager(final Playing playing) {
        this.playing = playing;
        this.loadEnemyImages();
        this.addEnemies();
    }

    public void update(final int[][] levelData, final Player player){
        this.crabbies.forEach(crabby -> {
            if(crabby.isActive()){
                crabby.update(levelData, player);
            }
        });
    }

    public void render(final Graphics graphics, final int xLevelOffset){
        this.crabbies.forEach(crabby -> {
            if(crabby.isActive()){
                graphics.drawImage(
                        this.crabbyImage[crabby.getEnemyState()][crabby.getAnimationIndex()],
                        (int) crabby.getHitBox().x - xLevelOffset - CRABBY_DRAWOFFSET_X + crabby.flipX(),
                        (int) crabby.getHitBox().y - CRABBY_DRAWOFFSET_Y,
                        CRABBY_WIDTH * crabby.flipWidth(),
                        CRABBY_HEIGHT,
                        null);

                crabby.render(graphics, xLevelOffset);
            }
        });
    }

    public void checkEnemyHit(final Rectangle2D.Float attackBox){
        this.crabbies.forEach(crabby -> {
            if(crabby.isActive()){
                if(attackBox.intersects(crabby.getHitBox())){
                    crabby.hurt(10);
                }
            }
        });
    }

    public void resetAll(){
        this.crabbies.forEach(Crabby::resetEnemy);
    }

    private void loadEnemyImages() {
        this.crabbyImage = new BufferedImage[5][9];

        final BufferedImage temp = ImageRenderer.loadSprite(ImageRenderer.CRABBY_ATLAS);

        for(int j = 0; j < this.crabbyImage.length; j++){
            for(int i = 0; i < this.crabbyImage[j].length; i++){
                this.crabbyImage[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
            }
        }
    }

    private void addEnemies() {
        this.crabbies = ImageRenderer.getCrabs();
        System.out.println(Colors.CYAN + "Crabs Informations (count: " + crabbies.size() + "):\n");
        for(int i = 0; i < this.crabbies.size(); i++){
            final Crabby crabby = this.crabbies.get(i);
            System.out.println(Colors.YELLOW_BRIGHT + "ID: " + i);
            System.out.println(Colors.YELLOW_BRIGHT + " Health: " + crabby.getCurrentHealth() + "/" + crabby.getMaxHealth());
            System.out.println(Colors.YELLOW_BRIGHT + " X: " + Math.round(crabby.getHitBox().x));
            System.out.println(Colors.YELLOW_BRIGHT + " Y: " + Math.round(crabby.getHitBox().y));
        }
    }
}
