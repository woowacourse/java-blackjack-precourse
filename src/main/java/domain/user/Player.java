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

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getCardString() {
        return (name+" 카드: "+listCard(getKoreanName()));
    }

    public String getFinalCardString() {
        return (name+" 카드: " + listCard(getKoreanName()) + " - 결과: "+this.calculateScore()+super.concatBust());
    }

    public String isHit() {
        return (name+"님, 한장의 카드를 더 받으시겠습니까? (힛: Y, 스탠드: N)");
    }

    public String getName() {
        return this.name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }
}
