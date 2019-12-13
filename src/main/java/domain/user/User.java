package domain.user;

import domain.card.Card;

import java.util.List;

public interface User {
    void addCard(Card card);

    String getName();

    List<Card> getCards();

    int getScoreOfCards();
}