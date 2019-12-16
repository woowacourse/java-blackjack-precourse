package domain.card;

import java.util.Random;

public enum Type {
    SPADE,
    DIAMOND,
    HEART,
    CLUB;
    
    private static Random random = new Random();

    public static Type getRandom() {
        return values()[random.nextInt(values().length)];
    }
}