package fr.kizafox.theguywhoescape.game.entities.player.handlers;

import fr.kizafox.theguywhoescape.game.entities.utils.EntityConstants;

import java.awt.image.BufferedImage;

import static fr.kizafox.theguywhoescape.game.client.window.settings.GameSettings.SCALE;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public abstract class EntityPlayer extends CraftPlayer {
    public EntityPlayer() {
        super(new BufferedImage[9][6], 0, 0, EntityConstants.PlayerConstants.IDLE, false, false, false, false, false, false, new int[0][0], 21 * SCALE, 4 * SCALE, 0F, .04F * SCALE, -2.25F * SCALE, .5F * SCALE, 2F);
    }
}
