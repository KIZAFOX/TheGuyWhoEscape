package fr.kizafox.theguywhoescape.game.entities.utils;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 04/07/2023
 * @project : TheGuyWhoEscape
 */
public class EntityConstants {
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