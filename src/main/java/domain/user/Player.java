package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
        //System.out.println("새 플레이어: "+name+" "+bettingMoney);
    }

    public void addCard(Card card) {
        cards.add(card);
        System.out.println(name+": "+card.toString());
    }

    // TODO 추가 기능 구현

}
