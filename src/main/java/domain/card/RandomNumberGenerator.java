package domain.card;

import java.util.Random;

public class RandomNumberGenerator {
    private static Random random;
    private int boundary;

    public RandomNumberGenerator(int symbolLength, int typeLength) {
        random = new Random();
        random.setSeed(System.currentTimeMillis());
        boundary = symbolLength * typeLength;
    }

    public int generate() {
        return random.nextInt(boundary);
    }

}
