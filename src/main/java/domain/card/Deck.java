/*
 * @(#)Deck.java        0.7 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

import java.util.ArrayList;
import java.util.List;

/**
 * card 덱을 생성하여 관리하는 클래스.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.7 2019.12.17
 */
public class Deck {
    /**
     * 처음 deck이 생성되었을 때 iterator가 deck의 첫 card를 가리키도록 설정할 상수.
     */
    private static final int FIRST_CARD_IN_DECk = 0;

    /**
     * deck의 모든 card가 사용되었는지 확인하기 위한 상수.
     */
    private static final int USED_ALL_CARD_IN_DECK = 52;

    /**
     * 생성한 카드들을 저장할 Card 객체 List.
     */
    private List<Card> cards;

    /**
     * 몇번째 card를 뽑아야 하는지 알려주는 iterator.
     */
    private int cardIterator;

    /**
     * deck이 생성될 때 card를 만드는 Deck 기본 생성자.
     */
    public Deck() {
        generateCardsIntoDeck();
    }

    /**
     * card를 생성하여 deck에 저장하는 메소드.
     */
    private void generateCardsIntoDeck() {
        cards = new ArrayList<>(CardFactory.create());

        cardIterator = FIRST_CARD_IN_DECk;
    }

    /**
     * deck에서 card 한 장을 뽑는 기능 구현.
     *
     * @return 뽑은 card 반환.
     */
    public Card drawCard() {
        checkAllCardsUsedInDeck();
        return cards.get(cardIterator++);
    }

    /**
     * deck의 card가 모드 사용되었을 시 새로 deck을 생성하는 메소드.
     */
    private void checkAllCardsUsedInDeck() {
        if (cardIterator == USED_ALL_CARD_IN_DECK) {
            generateCardsIntoDeck();
        }
    }
}
