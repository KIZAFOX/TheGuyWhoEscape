package fr.kizafox.theguywhoescape.game.utils;

import fr.kizafox.theguywhoescape.game.client.settings.GameSettings;

import static fr.kizafox.theguywhoescape.game.client.settings.GameSettings.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class Constants {
    public static class Environnement{
        public static final int
                BIG_CLOUD_WIDTH_DEFAULT = 448,
                BIG_CLOUD_HEIGHT_DEFAULT = 101,

                BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * SCALE),
                BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * SCALE),

                SMALL_CLOUD_WIDTH_DEFAULT = 74,
                SMALL_CLOUD_HEIGHT_DEFAULT = 24,

                SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * SCALE),
                SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * SCALE)

        ;
    }

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

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{
        public static final int
                IDLE = 0,
                RUNNING = 1,
                JUMPING = 2,
                FALLING = 3,
                ATTACK_1 = 4,
                HIT = 5,
                DEAD = 6;

        public static int getSpriteAmount(final int playerAction){
            switch (playerAction) {
                case DEAD:
                    return 8;
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case JUMPING:
                case ATTACK_1:
                    return 3;
                case FALLING:
                default:
                    return 1;
            }
        }
    }

    public static class EnemyConstants{
        public static final int
                CRABBY = 0,

                IDLE = 0,
                RUNNING = 1,
                ATTACK_1 = 2,
                HIT = 3,
                DEAD = 4,

                CRABBY_WIDTH_DEFAULT = 72,
                CRABBY_HEIGHT_DEFAULT = 32,
                CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * SCALE),
                CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * SCALE),

                CRABBY_DRAWOFFSET_X = (int) (26 * SCALE),
                CRABBY_DRAWOFFSET_Y = (int) (9 * SCALE);

        public static int getSpriteAmount(final int enemyType, final int enemyState){
            switch (enemyType) {
                case CRABBY:
                    switch (enemyState) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK_1:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                        default: break;
                    }
                default:break;
            }
            return 0;
        }

        public static int getEnemyMaxHealth(final int enemyType){
            switch (enemyType) {
                case CRABBY:
                    return 10;
                default:
                    return 1;
            }
        }

        public static int getEnemyDamage(final int enemyType){
            switch (enemyType) {
                case CRABBY:
                    return 15;
                default:
                    return 0;
            }
        }
    }
}