package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;

public class GameParticipant {
    private final static int BALCKJACK_SCORE = 21;
    private final static int DIFFERENCE_OF_TEN_AND_ONE = 9;
    private final List<Card> cards = new ArrayList<>();
    private final String name;

    public GameParticipant(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public double getSumOfCardScore() {
        double sum = 0;

        for (Card card : cards) {
            sum += card.getSymbol().getScore();
        }
        if (haveACECard() && is10BetterThen1(sum)) {
            sum += DIFFERENCE_OF_TEN_AND_ONE;
        }

        return sum;
    }

    public boolean isBlackjack() {
        if (cards.size() == 2) {
            return getSumOfCardScore() == BALCKJACK_SCORE;
        }
        return false;
    }

    public boolean isBust() {
        return getSumOfCardScore() > BALCKJACK_SCORE;
    }

    private boolean is10BetterThen1(double sum) {
        return sum + DIFFERENCE_OF_TEN_AND_ONE < BALCKJACK_SCORE;
    }

    private boolean haveACECard() {
        boolean haveACE = false;
        for (Card card : cards) {
            haveACE = (haveACE || (card.getSymbol() == Symbol.ACE));
        }
        return haveACE;
    }

    public void addMoreCard(Card card) {
        addCard(card);
    }

}
