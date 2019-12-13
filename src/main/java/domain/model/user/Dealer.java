/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.user;

import domain.model.card.Card;
import view.PrintController;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();
    private static final String COMMA = ", ";

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public Card getCard() {
        return cards.get(0);
    }

    public String getCardsInformation() { // 카드리스트를 이용해서 출력될 카드 정보를 String으로 만들어서 보내는 메서드
        ArrayList<String> cardInfoArrayList = new ArrayList<>();
        for (Card card : cards) {
            cardInfoArrayList.add(card.toString());
        }
        return String.join(COMMA, cardInfoArrayList);
    }

}
