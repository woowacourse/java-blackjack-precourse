package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public abstract class User {
    private static final int NONE_ACE_COUNT = 0;
    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int BUST_MIN_VALUE = 22;
    private static final int BLACKJACK_VALUE = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCardByIndex(int index) {
        return cards.get(index);
    }

    public int getCardsSize() {
        return cards.size();
    }

    public boolean isBust() {
        return getTotalScore() >= BUST_MIN_VALUE;
    }

    public boolean isBlackjack() {
        return (getTotalScore() == BLACKJACK_VALUE) && (cards.size() == BLACKJACK_CARD_SIZE);
    }

    public int getTotalScore() {
        int sumScore = cards.stream()
                .mapToInt(Card::getSymbolScore)
                .sum();
        if (!isHaveAce()) {
            return sumScore;
        }
        return calculateScoreContainsAce(sumScore, countAce());
    }

    private boolean isHaveAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private long countAce() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateScoreContainsAce(int sumScore, long aceCount) {
        if (aceCount == NONE_ACE_COUNT) {
            return sumScore;
        }
        if (sumScore + ACE_ADDITIONAL_VALUE < BUST_MIN_VALUE) {
            return calculateScoreContainsAce(sumScore + ACE_ADDITIONAL_VALUE, --aceCount);
        }
        return sumScore;
    }

    public abstract String getName();
}
