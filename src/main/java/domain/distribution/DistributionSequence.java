package domain.distribution;

import java.util.List;

/**
 * 카드를 섞었다고 가정하고 카드를 나눌 때 랜덤한 분배 순서를 담당하는 객체
 */
public class DistributionSequence {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 52;

    private List<Integer> sequences;
    private int index;

    public DistributionSequence() {
        init();
    }

    public int get() {
        if (index == MAX_VALUE) {
            init();
        }
        return sequences.get(index++);
    }

    private void init() {
        sequences = RandomNumbersGenerator.create(MIN_VALUE, MAX_VALUE);
        index = MIN_VALUE;
    }
}
