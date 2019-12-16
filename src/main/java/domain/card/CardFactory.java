package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 */
public class CardFactory {

    List<Card> cards = create();

    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return cards;
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }

    public List<Card> popRandomCard(int size) {
        return IntStream.range(0, size)
                .boxed()
                .map(i -> popRandomCard())
                .collect(Collectors.toList());
    }

    public Card popRandomCard() {
        return cards.remove(new Random().nextInt(cards.size()));
    }

}
