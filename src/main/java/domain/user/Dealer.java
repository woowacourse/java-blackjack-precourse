/*
 * @(#)Dealer.java      0.3 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.user;

import domain.card.Card;
import domain.ui.Output;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.3 2019.12.15
 */
public class Dealer {
    /**
     * Dealer와 관련된 정보를 출력할 때 사용할 상수.
     */
    private static final String DEALER_NAME = "딜러";

    /**
     * 첫번째 카드만 출력해야할 경우 사용할 상수.
     */
    private static final int FIRST_CARD = 0;

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

    /**
     * 처음 2개의 카드를 받았을 때 첫번째 카드만 출력하는 메소드.
     */
    public void printDealerCurrentCardStatus() {
        Output out = new Output();
        out.printUserCurrentCardStatus(DEALER_NAME, cards.get(FIRST_CARD).toString());
    }
}
