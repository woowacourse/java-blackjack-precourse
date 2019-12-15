/*
 * @(#)Deck.java        0.1 2019.12.15
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
 * @version 0.1 2019.12.15
 */
public class Deck {
    /**
     * 생성한 카드들을 저장할 Card 객체 List.
     */
    private List<Card> cards;

    /**
     * 카드를 생성하고 유효성을 검사하는 메소드.
     *
     * @throws Exception 카드에 중복이 발생할 경우 발생하는 예외.
     */
    public void generateCardDeck() throws Exception {
        try{
            cards = CardFactory.create();
            Validator.isValidCardDeck(cards);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
