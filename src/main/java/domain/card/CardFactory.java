package domain.card;

import java.util.*;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 */
public class CardFactory {
    public static Stack<Card> provideShuffledCards() {
        Stack<Card> cards = new Stack<>();
        long seed = System.nanoTime();

        cards.addAll(CardFactory.create());
        Collections.shuffle(cards, new Random(seed));

        return cards;
    }

    private static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
}
