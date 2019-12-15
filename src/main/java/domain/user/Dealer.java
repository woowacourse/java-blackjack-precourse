/*
 * @(#)Dealer.java      0.2 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.2 2019.12.15
 */
public class Dealer {
    /**
     * Dealer가 받은 카드를 저장할 Card 객체 List.
     */
    private final List<Card> cards = new ArrayList<>();

    /**
     * Dealer 클래스의 기본 생성자(이 이외의 생성자는 금지).
     */
    public Dealer() {}

    /**
     * Card List인 cards에 새로 받은 card를 추가하는 메소드.
     *
     * @param card 새로 받은 Card 객체.
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
}
