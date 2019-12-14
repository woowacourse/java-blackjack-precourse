package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class User {
    final List<Card> cards = new ArrayList<>();
    private int score = 0;
    private boolean isAce = false;
    private boolean isBigAce = false;

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void subtractScore(int score) {
        this.score -= score;
    }

    public boolean getIsAce() {
        return isAce;
    }

    public void setIsAce(boolean isAce) {
        this.isAce = isAce;
    }

    public boolean getIsBigAce() {
        return isBigAce;
    }

    public void setIsBigAce(boolean isBigAce) {
        this.isBigAce = isBigAce;
    }

    public List<Card> showCards() {
        return cards;
    }

    public boolean isScoreGreaterThan(int maxScore) {
        return score > maxScore;
    }

    public boolean isBlackJack(int maxScore) {
        return score == maxScore;
    }
}
