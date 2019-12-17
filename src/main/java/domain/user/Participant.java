package domain.user;

import domain.card.Card;

public interface Participant {

    boolean withInitCards();

    void addCard(Card card);

    void showCards();

    int calScore();

    void showOutcome();

    double doBalancing(double amount);
}
