package fr.kizafox.theguywhoescape.game.entities.utils;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class EntityConstants {
    public static class UI{
        public static class Buttons{
            public static final int
                    B_WIDTH_DEFAULT = 140,
                    B_HEIGHT_DEFAULT = 56,
                    B_WIDTH = (int) (B_WIDTH_DEFAULT * SCALE),
                    B_HEIGHT = (int) (B_HEIGHT_DEFAULT * SCALE);
        }

        public static class PauseButtons{
            public static final int
                    SOUND_SIZE_DEFAULT = 42,
                    SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * SCALE);
        }

        public static class URMButtons{
            public static final int
                    URM_SIZE_DEFAULT = 56,
                    URM_SIZE = (int) (URM_SIZE_DEFAULT * SCALE);
        }

        public static class VolumeButtons{
            public static final int
                    VOLUME_DEFAULT_WIDTH = 28,
                    VOLUME_DEFAULT_HEIGHT = 44,
                    SLIDER_DEFAULT_WIDTH = 215,

                    VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * SCALE),
                    VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * SCALE),
                    SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * SCALE);
        }
    }

    public static class PlayerConstants{
        public static final int
                IDLE = 0,
                RUNNING = 1,
                JUMPING = 2,
                FALLING = 3,
                GROUND = 4,
                HIT = 5,
                ATTACK_1 = 6,
                ATTACK_JUMP_1 = 7,
                ATTACK_JUMP_2 = 8;

        public static int getSpriteAmount(final int playerAction){
            return switch (playerAction) {
                case RUNNING -> 6;
                case IDLE -> 5;
                case HIT -> 4;
                case JUMPING, ATTACK_1, ATTACK_JUMP_1, ATTACK_JUMP_2 -> 3;
                case GROUND -> 2;
                case FALLING -> 0;
                default -> 1;
            };
        }
    }
}