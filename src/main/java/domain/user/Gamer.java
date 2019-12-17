package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int basicScore = calculateBasicScore();
        if (haveAce()) {
            return adjustAce(basicScore);
        }
        return basicScore;
    }

    private int calculateBasicScore() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getScore();
        }
        return sum;
    }

    private boolean haveAce() {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    private int adjustAce(int basicScore) {
        final int victoryScore = 21;
        final int adjustChange = 10;
        if (basicScore + adjustChange <= victoryScore
                && victoryScore - (basicScore + adjustChange) < victoryScore - basicScore) {
            return basicScore + adjustChange;
        }
        return basicScore;
    }
}
