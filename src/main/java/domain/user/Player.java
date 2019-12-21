package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return this.name;
    }

    public void setBettingMoney(Double money) {
        this.bettingMoney = money;
    }

    public void setBettingMoneyMultiple() {
        this.bettingMoney *= 1.5;
    }

    public Double getBettingMoney() {
        return this.bettingMoney;
    }

    /*
    score를 반환하며 에이스도 처리해준다.
     */
    public int getScoreHaveAce() {
        for (int i = 0; i < cards.size(); i ++) {
            if (cards.get(i).getScore() == 1
                    && getScore() <= 11) {
                return getScore() + 10;
            }
        }
        return getScore();
    }

    private int getScore() {
        int resultScore = 0;
        for (int i = 0; i < cards.size(); i ++) {
            resultScore += this.cards.get(i).getScore();
        }
        return resultScore;
    }

    public String toLog() {
        return this.name +
                " 카드:" + cards;
    }

    @Override
    public String toString() {
        return this.name +
                " 카드:" + cards +
                " - 결과: " + getScoreHaveAce();
    }
}
