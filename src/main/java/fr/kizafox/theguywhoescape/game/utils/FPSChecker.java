package fr.kizafox.theguywhoescape.game.utils;

import fr.kizafox.theguywhoescape.game.client.window.Game;

import java.awt.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class FPSChecker implements Runnable{

    protected final Game game;

    public static final int
            MAX_FPS = 144,
            UPDATES_SET = 200;

    public int
            frames = 0,
            currentFPS = 0,
            updates = 0;

    public FPSChecker(final Game game) {
        this.game = game;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / MAX_FPS, timePerUpdate = 1000000000.0 / UPDATES_SET;
        long lastCheck = System.currentTimeMillis(), previousTime = System.nanoTime();

        double deltaU = 0, deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                game.update();
                deltaU--;
            }

            if(deltaF >= 1){
                game.getGamePanel().repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                currentFPS = frames;
                System.out.println(Colors.RESET + Colors.PURPLE_BOLD + "FPS: " + frames + " | UPDATES: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void getFPS(final Graphics graphics) {
        graphics.setColor(Color.BLACK);

        final Font originalFont = graphics.getFont();
        final Font largerFont = originalFont.deriveFont(originalFont.getSize() * 1.5F);

        graphics.setFont(largerFont);
        graphics.drawString("FPS: " + currentFPS, 10, 20);
        graphics.setFont(originalFont);
    }
}
