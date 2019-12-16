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
    }

    // TODO 추가 기능 구현
    public void addCard(Card card) {
        cards.add(card);
    }

    public void showCard() {
        System.out.print("\n" + name + " : ");
        for (Card card : this.cards) {
            System.out.print(card + " ");
        }
    }

    public int getScore() {
        if (cards.toString().contains("A")) {
            return getScoreIncludeA();
        }
        return cards.stream().mapToInt(Card::getScore).sum();
    }

    /**
     * 카드중 A가 포함되어있을 경우 1과 11중 유리한 쪽으로 계산
     */
    public int getScoreIncludeA() {
        if (cards.stream().mapToInt(Card::getScore).sum() < 12) {
            return cards.stream().mapToInt(Card::getScore).sum() + 10;
        }
        return cards.stream().mapToInt(Card::getScore).sum();
    }

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }
}
