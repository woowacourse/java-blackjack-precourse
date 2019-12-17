package util;

import java.util.Random;

public class RandomNumberGenerator {
    private static final String MSG_RANGE_ERROR = "max는 min보다 큰 값이어야 합니다.";
    private Random random;
    private int min;
    private int max;

    public RandomNumberGenerator(int min, int max) {
        this.random = new Random();
        setRange(min, max);
    }

    public void setRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException(MSG_RANGE_ERROR);
        }

        this.random = new Random();
        this.min = min;
        this.max = max;
    }

    public int getNumber() {
        return random.nextInt((max - min) + 1) + min;
    }
}