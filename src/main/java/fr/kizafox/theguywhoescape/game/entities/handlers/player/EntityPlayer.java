package fr.kizafox.theguywhoescape.game.entities.handlers.player;

import fr.kizafox.theguywhoescape.game.status.sub.Playing;
import fr.kizafox.theguywhoescape.game.utils.Constants;

import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public abstract class EntityPlayer extends CraftPlayer {
    public EntityPlayer() {
        super(
                0,
                0,
                Constants.PlayerConstants.IDLE,
                false,
                false,
                false,
                false,
                false, false,
                new int[0][0],
                21 * SCALE,
                4 * SCALE, 0F,
                .04F * SCALE,
                -2.25F * SCALE,
                .5F * SCALE,
                1.35F * SCALE,
                (int) (192 * SCALE),
                (int) (58 * SCALE),
                (int) (10 * SCALE),
                (int) (10 * SCALE),
                (int) (150 * SCALE),
                (int) (4 * SCALE),
                (int) (34 * SCALE),
                (int) (14 * SCALE),
                100,
                0,
                1,
                true
        );
    }
}
