package fr.kizafox.theguywhoescape.game.client.window;

import fr.kizafox.theguywhoescape.game.status.GameStatus;
import fr.kizafox.theguywhoescape.game.status.sub.Menu;
import fr.kizafox.theguywhoescape.game.status.sub.Playing;
import fr.kizafox.theguywhoescape.game.utils.Colors;
import fr.kizafox.theguywhoescape.game.utils.FPSChecker;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class Game{

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;

    private final Menu menu;
    private final Playing playing;

    private final FPSChecker fpsChecker;

    public Game() {
        final Instant startTime = Instant.now();

        this.menu = new Menu(this);
        this.playing = new Playing(this);

        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(this.gamePanel);
        this.gamePanel.requestFocus();

        this.fpsChecker = new FPSChecker(this);

        new Thread(this.fpsChecker).start();

        System.out.printf(Colors.GREEN_UNDERLINED + "Game launched successfully in %ss !\n\n", (Duration.between(startTime, Instant.now()).toMillis()));
    }

    public void update(){
        switch (GameStatus.STATUS){
            case MENU:
                this.menu.update();
                break;
            case PLAYING:
                this.playing.update();
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void render(final Graphics graphics) {
        switch (GameStatus.STATUS){
            case MENU -> this.menu.render(graphics);
            case PLAYING -> this.playing.render(graphics);
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public FPSChecker getFPSChecker() {
        return fpsChecker;
    }

}
