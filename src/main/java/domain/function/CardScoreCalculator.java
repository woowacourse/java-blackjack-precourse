package domain.function;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class CardScoreCalculator {
    private static final int ACE_MIN_SCORE = 1;
    private static final int ACE_MAX_SCORE = 11;

    public int calculateTotalScore(List<Card> cards) {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.calculateScore();
        }
        if (haveAce(cards)) {
            totalScore = addOneOrEleven(totalScore);
        }
        return totalScore;
    }

    private boolean haveAce(List<Card> cards) {
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(card.getSymbolScoreName());
        }
        return cardNames.contains("A");
    }

    private int addOneOrEleven(int totalScore) {
        int totalScoreExcludingAce = totalScore - ACE_MIN_SCORE;
        if (totalScoreExcludingAce < ACE_MAX_SCORE) {
            totalScore = totalScoreExcludingAce + ACE_MAX_SCORE;
        }
        return totalScore;
    }
}
