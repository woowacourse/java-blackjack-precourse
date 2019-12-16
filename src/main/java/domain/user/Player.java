package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Dealer {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현

    @Override
    public String print() {
        return name + "카드: " + printCardList();
    }

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

}