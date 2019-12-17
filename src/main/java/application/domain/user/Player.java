package application.domain.user;

import application.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String getFirstShuffleCardInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append(cards.get(0));
        sb.append(',');
        sb.append(cards.get(1));
        return sb.toString();
    }

    // TODO 추가 기능 구현


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", bettingMoney=" + bettingMoney +
                ", cards=" + cards +
                '}';
    }
}
