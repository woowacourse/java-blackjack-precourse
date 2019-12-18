package domain.user;

import domain.Game;
import domain.card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = super.cards;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }


    // TODO 추가 기능 구현

    public double getBettingMoney(){
        return bettingMoney;
    }

    public String toString() {
        return name;
    }
}
