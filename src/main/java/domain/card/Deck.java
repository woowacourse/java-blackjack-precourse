/*
 * @(#)Deck.java        0.3 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.card;

import domain.business.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * 카드 덱을 생성하고 관리하는 클래스.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.3 2019.12.15
 */
public class Deck {
    /**
     * 처음 카드 덱이 생성되었을 때 시작하는 iterator 값.
     */
    private static final int FIRST_CARD = 0;

    /**
     * 카드 덱의 마지막 카드에 도착했을 때의 iterator 값.
     */
    private static final int LAST_CARD = 51;

    /**
     * 생성한 카드들을 저장할 Card 객체 List.
     */
    private List<Card> cards;

    /**
     * 몇번째 카드를 뽑아야 하는지 알려주는 iterator.
     */
    private int cardIterator;

    /**
     * 처음 덱이 생성될 때 카드를 만들고, 뽑아야 할 카드를 알려주는 iterator를 초기화하는 생성자.
     */
    public Deck() throws Exception {
        generateCardDeck();
        cardIterator = FIRST_CARD;
    }

    /**
     * 카드를 생성하고 유효성을 검사하는 메소드.
     *
     * @throws Exception 카드에 중복이 발생할 경우 발생하는 예외.
     */
    private void generateCardDeck() throws Exception {
        try{
            cards = new ArrayList<>(CardFactory.create());
            Validator.isValidCardDeck(cards);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /**
     * 카드 덱에서 카드 한장을 뽑는 기능 구현.
     *
     * @return 뽑은 한장의 카드 반환.
     */
    public Card drawCard() {
        return cards.get(cardIterator++);
    }
}
