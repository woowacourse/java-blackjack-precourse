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

public class User {
    private List<Card> cards = new ArrayList<>();

    public void addCard(Deck deck){
        cards.add(deck.getCard());
    }
}
