package fr.kizafox.theguywhoescape.game.status.sub;

import fr.kizafox.theguywhoescape.game.client.window.Game;
import fr.kizafox.theguywhoescape.game.client.settings.GameSettings;
import fr.kizafox.theguywhoescape.game.client.window.ui.button.MenuButton;
import fr.kizafox.theguywhoescape.game.status.GameStatus;
import fr.kizafox.theguywhoescape.game.status.Status;
import fr.kizafox.theguywhoescape.game.status.StatusCore;
import fr.kizafox.theguywhoescape.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class Menu extends Status implements StatusCore {

    private final MenuButton[] buttons = new MenuButton[3];

    private BufferedImage backgroundImage;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(Game game) {
        super(game);
        this.loadButtons();
        this.loadBackground();
    }

    private void loadButtons() {
        this.buttons[0] = new MenuButton(GameSettings.GAME_WIDTH / 2, (int) (150 * GameSettings.SCALE), 0, GameStatus.PLAYING);
        this.buttons[1] = new MenuButton(GameSettings.GAME_WIDTH / 2, (int) (220 * GameSettings.SCALE), 1, GameStatus.OPTIONS);
        this.buttons[2] = new MenuButton(GameSettings.GAME_WIDTH / 2, (int) (290 * GameSettings.SCALE), 2, GameStatus.QUIT);
    }

    private void loadBackground() {
        this.backgroundImage = ImageRenderer.loadSprite(ImageRenderer.MENU_BACKGROUND);

        this.menuWidth = (int) (this.backgroundImage.getWidth() * GameSettings.SCALE);
        this.menuHeight = (int) (this.backgroundImage.getHeight() * GameSettings.SCALE);
        this.menuX = ((GameSettings.GAME_WIDTH / 2) - (this.menuWidth / 2));
        this.menuY = (int) (45 * GameSettings.SCALE);
    }

    @Override
    public void update() {
        Arrays.stream(buttons).forEach(MenuButton::update);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(this.backgroundImage, menuX, menuY, menuWidth, menuHeight, null);
        Arrays.stream(buttons).forEach(button -> button.render(graphics));
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }

    @Override
    public void mousePressed(MouseEvent event) {
        Arrays.stream(buttons).forEach(button -> {
            if(this.isIn(event, button)) {
                button.setMousePressed(true);
            }
        });
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        Arrays.stream(buttons).forEach(button -> {
            if(this.isIn(event, button)) {
                if(button.isMousePressed()){
                    button.applyGameStatus();
                }
            }
        });
        Arrays.asList(buttons).forEach(MenuButton::resetBooleans);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        Arrays.asList(buttons).forEach(button -> button.setMouseOver(false));
        Arrays.asList(buttons).forEach(button -> {
            if(this.isIn(event, button)){
                button.setMouseOver(true);
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER) GameStatus.STATUS = GameStatus.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
