/*
 * @(#)CardFactory.java     0.3 2019.12.16
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 트럼프 card 전체 생성을 담당하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.3 2019.12.16
 */
public class CardFactory {
    /**
     * card의 deck을 생성하는 메소드.
     * 생성한 card를 반환할 때 섞어서 반환하도록 중간에 shuffle 기능 추가.
     *
     * @return 섞여서 반환되는 한개의 deck.
     */
    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();

        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        Collections.shuffle(cards);
        return Collections.unmodifiableList(cards);
    }

    /**
     * 1개의 symbol마다 4가지 type(SPADE, DIAMOND, HEART, CLUB)으로 card를 생성하는 메소드.
     *
     * @param cards  생성한 card를 저장할 deck.
     * @param symbol 생성할 card의 symbol.
     */
    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();

        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
}
