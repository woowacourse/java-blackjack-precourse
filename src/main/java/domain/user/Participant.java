package domain.user;

import domain.card.Card;

public interface Participant {

    int getNumberOfCards();

    void addCard(Card card);

    void showCards();

    int calScore();

    double doBalancing(double result);
}
