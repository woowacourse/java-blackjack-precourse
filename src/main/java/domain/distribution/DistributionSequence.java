package domain.distribution;

import java.util.List;
import java.util.Random;

import utils.RandomNumbersGenerator;

public class DistributionSequence {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 52;
    private static final int DECKS_NUMBER = 6;

    private final List<Integer> sequences;
    private int index = 0;

    public DistributionSequence() {
        sequences = RandomNumbersGenerator.create(MIN_VALUE, calculateMaxValue());
    }

    public int get() {
        return sequences.get(index++ % MAX_VALUE);
    }

    private int calculateMaxValue() {
        /* 6개의 덱 사용 - 카운팅 방지를 위한 커팅 */
        return (MAX_VALUE * DECKS_NUMBER) - new Random().nextInt(MAX_VALUE);
    }
}
