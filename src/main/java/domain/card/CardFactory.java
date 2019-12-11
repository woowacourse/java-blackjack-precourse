package domain.card;

import java.util.Set;
import java.util.HashSet;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 */
public class CardFactory {
    public static Set<Card> create() {
        Set<Card> cards = new HashSet<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return cards;
    }

    private static void createByType(Set<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
}
