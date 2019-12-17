package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain
.card.Card;
/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
	this.name = name;
	this.bettingMoney = bettingMoney;
    }


    public double getBettingMoney() {
	return bettingMoney;
    }

    public String getName() {
	return name;
    }

}
