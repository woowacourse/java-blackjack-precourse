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
import util.GameRule;

public class Player extends User {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    @Override
    public void addCardIfWant(Deck deck) {
        while (canGetCard() && GameManager.inputView.getMoreCard(name)) {
            addCard(deck);
            System.out.println(toStringCards());
        }

        if(isBust()) {
            System.out.println(name + "님께서 Bust 되셨습니다.");
        }
    }

    @Override
    public boolean canGetCard() {
        return getScore() < GameRule.BUST;
    }

    @Override
    public String toStringCards() {
        return name+":"+super.toStringCards();
    }

    public double getProfit(int dealerScore) {
        if (isBlackJack()) {
            return bettingMoney * 1.5;
        }
        if (isBust()) {
            return bettingMoney * -1.0;
        }
        int score = getScore();
        if (score > dealerScore) {
            return bettingMoney;
        }
        if (score == dealerScore) {
            return 0;
        }
        return bettingMoney * -1;
    }

    public String getName() {
        return name;
    }
}
