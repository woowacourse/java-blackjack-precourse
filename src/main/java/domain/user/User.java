package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static final int INITIAL_SCORE = 0;
    private static final int BLACKJACK_SCORE = 11;
    private static final int BLACKJACK_CORRECTION = 10;
    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final String ACE_SYMBOL = "ACE";
    private static final String CARD_VALUE_DELIMITER = ", ";
    private final List<Card> cards = new ArrayList<>();

    public User() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<String> getCardValue() {
        ArrayList<String> cardInfo = new ArrayList<>();
        for (Card card : this.cards) {
            cardInfo.add(card.printSymbolAndNumber());
        }
        return cardInfo;
    }

    String printCardValue() {
        return String.join(CARD_VALUE_DELIMITER, getCardValue());
    }

    private int getScore() {
        int score = INITIAL_SCORE;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    int getRealScore() {
        int score = INITIAL_SCORE;
        for (Card card : cards) {
            score += card.getScore();
        }
        if (score <= BLACKJACK_SCORE && hasAce()) {
            score += BLACKJACK_CORRECTION;
        }
        return score;
    }

    boolean validateUnder(int number) {
        return getRealScore() < number;
    }

    boolean isBlackjack() {
        return hasAce() && getScore() == BLACKJACK_SCORE && cards.size() == BLACKJACK_CARD_SIZE;
    }

    private boolean hasAce() {
        ArrayList<Boolean> hasAce = new ArrayList<>();
        for (Card card : cards) {
            hasAce.add(checkHasAce(card));
        }

        return hasAce.contains(true);
    }

    private boolean checkHasAce(Card card) {
        return card.getSymbol().equals(ACE_SYMBOL);
    }

}
