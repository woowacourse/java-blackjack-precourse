package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final int bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, int bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
    public static void checkValidName(String name) {
    	PlayerConstraints.checkEmptyName(name);
    }
    
    public static void checkValidBettingMoney(int money) {
    	PlayerConstraints.checkMoneyRange(money);
    }
}
