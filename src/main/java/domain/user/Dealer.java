package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private static final int BLACKJACK = 21;
    private static final int ACEDIFFNUM = 10;
    private final List<Card> cards = new ArrayList<>();
    private double cost = 0;
    public boolean ifBurst = false;

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
    public int sumScore() {
        int score = 0;
        boolean hasAce = false;
        for (int i = 0; i < cards.size(); i++) {
            score += cards.get(i).getScore();
            hasAce = cards.get(i).ifCardIsAce();
        }
        if (hasAce) {
            score = checkIfBurst(score);
        }
        return score;
    }

    public int checkIfBurst(int score) {
        if (score + ACEDIFFNUM <= BLACKJACK) {
            score += ACEDIFFNUM;
        }
        return score;
    }

    public boolean ifBlackJack() {
        if ((cards.get(0).getScore() + cards.get(1).getScore()) == BLACKJACK) {
            return true;
        }
        return false;
    }

    public void addCost(double playerCost) {
        cost += playerCost;
    }

    public void minusCost(double playerCost) {
        cost -= playerCost;
    }

    public int finalCost() {
        return (int) cost;
    }

    public String toStringFirstTurn() {
        String str = cards.get(0).toString();
        return str;
    }

    public String toString() {
        String str = "";
        String joinStr = String.join(",", cards.toString());
        str += joinStr.substring(1, joinStr.length() - 1);
        return str;
    }
}
