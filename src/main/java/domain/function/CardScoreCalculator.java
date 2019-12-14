package domain.function;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class CardScoreCalculator {
    private static final int ACE_MIN_SCORE = 1;
    private static final int ACE_MAX_SCORE = 11;
    private final List<Card> cards = new ArrayList<>();

    public int calculateTotalCardScore() {
        int totalCardScore = 0;
        for (Card card : cards) {
            totalCardScore += card.calculateCardScore();
        }
        if (haveAce()) {
            addOneOrEleven(totalCardScore);
        }
        return totalCardScore;
    }

    private boolean haveAce() {
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(card.getSymbolScoreName());
        }
        return cardNames.contains("A");
    }

    private void addOneOrEleven(int totalCardScore) {
        totalCardScore -= ACE_MIN_SCORE;
        if (totalCardScore < ACE_MAX_SCORE) {
            totalCardScore += ACE_MAX_SCORE;
        } else {
            totalCardScore += ACE_MIN_SCORE;
        }
    }
}
