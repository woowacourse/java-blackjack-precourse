package domain.user;

import domain.card.Deck;

public interface User {
    int BLACKJACK_NUMBER = 21;

    void addCard(Deck deck, int number);

    String getName();

    String getInitialCards();

    String getHoldingCards();

    int getScoreOfCards();

    boolean isBust();

    boolean isBlackjack();

    boolean isGettingAdditionalCard();

    String getMessageForAdditionalCard();

    boolean isScore(int score);
}