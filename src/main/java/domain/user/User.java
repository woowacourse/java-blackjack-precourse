package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.UserCards;

import java.util.List;

abstract public class User {
    private final UserCards userCards;
    private static final int LIMIT = 21;
    private static final int ONE = 1;

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

    List<Card> getCards() {
        return userCards.getCards();
    }

    String printCards() {
        return userCards.printCards();
    }

    boolean checkExcess() {
        return userCards.checkExcess();
    }

    public boolean isBlackJack() {
        return userCards.getAceCount() == ONE && userCards.isHaveTen();
    }

    public int calcurateScore() {
        int scoreExceptAce = userCards.calcurateScoreExceptAce();
        return startCalcurateAceScoreLoop(scoreExceptAce);
    }

    private int startCalcurateAceScoreLoop(int score) {
        for (int i = userCards.getAceCount(); i > 0; i--) {
            score += calcureateAceScore(score);
        }
        return score;
    }

    private int calcureateAceScore(int score) {
        if (score + Symbol.ACE.getBonusScore() > LIMIT) {
            return Symbol.ACE.getScore();
        }
        return Symbol.ACE.getBonusScore();
    }

    boolean isPlayer() {
        return this instanceof Player;
    }

    boolean isDealer() {
        return this instanceof Dealer;
    }
}
