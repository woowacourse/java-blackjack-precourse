/*
 * @(#)Deck.java        0.5 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

import java.util.ArrayList;
import java.util.List;

/**
 * 카드 덱을 생성하고 관리하는 클래스.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.5 2019.12.17
 */
public class Deck {
    /**
     * 처음 deck이 생성되었을 때 iterator가 첫 card를 가리키도록 설정할 상수.
     */
    private static final int FIRST_CARD = 0;

    /**
     * deck의 모든 card가 사용되었는지 확인하기 위한 상수.
     */
    private static final int USED_ALL_CARD_IN_DECK = 52;

    /**
     * 생성한 카드들을 저장할 Card 객체 List.
     */
    private List<Card> cards;

    /**
     * 몇 번째 card를 뽑아야 하는지 알려주는 iterator.
     */
    private int cardIterator;

    /**
     * 처음 deck이 생성될 때 card를 만드는 Deck 기본 생성자.
     */
    public Deck() {
        generateCardDeck();
    }

    /**
     * card를 생성하는 메소드.
     */
    private void generateCardDeck() {
        cards = new ArrayList<>(CardFactory.create());
        cardIterator = FIRST_CARD;
    }

    /**
     * deck에서 card 한 장을 뽑는 기능 구현.
     *
     * @return 뽑은 card 반환.
     */
    public Card drawCard() {
        isAllCardUsedInDeck();
        return cards.get(cardIterator++);
    }

    /**
     * deck의 모든 card가 사용되었을 시 새로 deck을 생성하는 메소드.
     */
    private void isAllCardUsedInDeck() {
        if (cardIterator == USED_ALL_CARD_IN_DECK) {
            generateCardDeck();
            cardIterator = FIRST_CARD;
        }
    }
}
