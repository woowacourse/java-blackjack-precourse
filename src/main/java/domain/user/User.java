/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * User.java
 * 게임에 참가한 유저들의 공통 행동을 담는 객체
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        17 Dec 2019
 *
 */

package domain.user;

import java.util.ArrayList;
import java.util.List;
import domain.card.Card;
import domain.card.Deck;
import util.GameRule;

abstract public class User {
    private List<Card> cards = new ArrayList<>();

    public void addCard(Deck deck){
        cards.add(deck.getCard());
    }
    public abstract void addCardIfWant(Deck deck);

    public String toStringCards() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append("\t"+card.toString());
        }
        return stringBuilder.toString();
    }

    public int getScore() {
        int score = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            if(card.getCardNumber() == GameRule.ACE) {
                hasAce = true;
                continue;
            }
            score += card.getCardNumber();
        }
        if(hasAce) {
            score += determineAceValue(score);
        }
        return score;
    }

    private int determineAceValue(int score) {
        if((score + GameRule.ACE_VALUE_ELEVEN) < GameRule.BUST) {
            return GameRule.ACE_VALUE_ELEVEN;
        }
        return GameRule.ACE_VALUE_ONE;
    }
}
