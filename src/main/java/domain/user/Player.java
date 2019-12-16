/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * Player.java
 * 게임 참여자를 의미하는 객체
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package domain.user;

import java.util.ArrayList;
import java.util.List;
import domain.card.Card;
import domain.card.Deck;

public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Deck deck){
        cards.add(deck.getCard());
    }
}
