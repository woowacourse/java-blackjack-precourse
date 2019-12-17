package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : Kim SeYun
 * @ClassName : CardFactory
 * @ClassExplanation : 트럼프 카드 전체를 의미하는 것
 * @Date : 2019. 12. 17
 * @Copyright (c) 2019 SeYun Kim
 */
public class CardFactory {
    public static List<Card> create() {
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
