package domain.card;

import java.util.ArrayList;
import java.util.List;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 */
class CardFactory {
    static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
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
}
