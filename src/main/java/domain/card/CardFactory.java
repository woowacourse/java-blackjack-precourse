/*
 * @(#)CardFactory.java     0.2 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.2 2019.12.15
 */
public class CardFactory {
    /**
     * 카드 덱을 생성하는 메소드.
     * 생성한 카드를 반환할 때 섞어서 반환되도록 중간에 shuffle 기능 추가.
     *
     * @return 섞여서 반환되는 한 덱의 카드.
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
     * symbol(숫자 번호)마다 4가지 타입(SPADE, DIAMOND, HEART, CLUB)을 생성하는 메소드.
     *
     * @param cards
     * @param symbol
     */
    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
}
