package domain.card;

import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public Score score() {
        return reviseAceScore(calculateRawScore());
    }

    private Score reviseAceScore(Score score) {
        if (!hasAce(cards)) {
            return score;
        }
        return score.plusTenIfNotBust();
    }

    private Score calculateRawScore() {
        Score score = Score.ZERO;
        for (Card card : cards) {
            score = card.calculate(score);
        }
        return score;
    }

    private static boolean hasAce(List<Card> original) {
        return original.stream().anyMatch(Card::isAce);
    }

}
