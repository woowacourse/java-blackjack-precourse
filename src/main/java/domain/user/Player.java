package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Person{
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName(){
        return this.name;
    }


    public String toString(){
        return "name : " + this.name + ", bettingMoney : " + this.bettingMoney;
    }

    // TODO 추가 기능 구현

}
