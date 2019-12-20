package domain.user;

import domain.card.Card;
import domain.card.Score;
import domain.card.UserCards;

abstract public class User {
    private final UserCards userCards;

    User() {
        this.userCards = new UserCards();
    }

    abstract public void printUserState();

    abstract public void printFinalOutput();

    void addRandomCard() {
        userCards.addRandomCard();
    }

    void addInitCard() {
        userCards.addRandomCard();
        userCards.addRandomCard();
    }

    void addFixCard(Card card) {
        userCards.addFixCard(card);
    }

    public UserCards getCards() {
        return userCards;
    }

    String printCards() {
        return userCards.printCards();
    }

    public Score calcurateScore() {
        return userCards.calcurateScore();
    }

    boolean isPlayer() {
        return this instanceof Player;
    }

    boolean isDealer() {
        return this instanceof Dealer;
    }
}
