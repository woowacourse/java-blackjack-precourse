/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * CardFactory.java
 * 트럼프 카드 전체 생성을 담당하는 객체
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        Collections.shuffle(cards);
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
}
