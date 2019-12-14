package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();
    private double cost = 0;

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
    public int sumScore() {
        int score = 0;
        for (int i = 0; i < cards.size(); i++) {
            score += cards.get(i).getScore();
        }
        return score;
    }

    public void addCost(double playerCost) {
        cost += playerCost;
    }

    public double finalCost() {
        return cost;
    }

    public String getCard() {
        String cardStr = String.join(", ",cards.toString());
        return cardStr;
    }

    public String toString() {
        String str = "";
        String joinStr = String.join(",", cards.toString());
        str += joinStr.substring(1, joinStr.length()-1);
        return str;
    }
}
