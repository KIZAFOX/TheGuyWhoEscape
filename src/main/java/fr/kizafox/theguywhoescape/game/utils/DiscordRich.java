package fr.kizafox.theguywhoescape.game.utils;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import fr.kizafox.theguywhoescape.game.status.GameStatus;
import fr.kizafox.theguywhoescape.game.status.sub.Playing;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

/**
 * Created at 21/11/2023 at 02:59
 * Made by @KIZAFOX (twitter)
 **/

public class DiscordRich {

    public DiscordRich(final Playing playing, final String applicationId) {
        final DiscordRPC lib = DiscordRPC.INSTANCE;
        final DiscordEventHandlers handlers = new DiscordEventHandlers();

        lib.Discord_Initialize(applicationId, handlers, true, null);

        final DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = "1024";
        presence.largeImageText = NAME;
        presence.state = VERSION;
        lib.Discord_UpdatePresence(presence);

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    if(GameStatus.STATUS == GameStatus.MENU){
                        presence.state = "Dans le menu";
                        lib.Discord_UpdatePresence(presence);
                    }else if(GameStatus.STATUS == GameStatus.PLAYING){
                        presence.details = "Mode Aventure (Solo)";
                        presence.state = "En Jeu";
                        lib.Discord_UpdatePresence(presence);
                    }
                    Thread.sleep(5000L);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();

        System.out.println(Colors.CYAN_BOLD + "DiscordRPC ready to use!\n");
    }
}
