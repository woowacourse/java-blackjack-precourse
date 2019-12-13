package domain.user;

import domain.card.Card;

import java.util.List;

public interface User {
    int BLACKJACK_NUMBER = 21;

    void addCard(Card card);

    String getName();

    List<Card> getCards();

    int getScoreOfCards();

    boolean isBust();

    boolean isBlackjack();
}