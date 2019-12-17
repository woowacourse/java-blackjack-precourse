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

import domain.card.Deck;
import game.GameManager;

public class Player extends User {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    @Override
    public void addCardIfWant(Deck deck) {
        while (GameManager.inputView.getMoreCard(name)) {
            addCard(deck);
            System.out.println(toStringCards());
        }
    }

    @Override
    public String toStringCards() {
        return name+":"+super.toStringCards();
    }
}
