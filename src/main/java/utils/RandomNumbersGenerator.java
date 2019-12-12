package utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumbersGenerator {

    public static List<Integer> create(int minValue, int maxValue) {
        List<Integer> cardOrders = createNumbersByRange(minValue, maxValue);
        Collections.shuffle(cardOrders);
        return Collections.unmodifiableList(cardOrders);
    }

    private static List<Integer> createNumbersByRange(int minValue, int maxValue) {
        return IntStream.range(minValue, maxValue)
                .boxed()
                .collect(Collectors.toList());
    }
}
