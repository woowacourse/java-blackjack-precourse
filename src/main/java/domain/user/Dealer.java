package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void showCard() {
        System.out.println("딜러: " + cards.get(0));
    }

    public int calScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }
}
