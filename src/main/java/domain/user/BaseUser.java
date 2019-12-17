package domain.user;

import domain.card.Card;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseUser implements User {
    protected final List<Card> cards = new ArrayList<>();

    @Override
    public void addCard(Deck deck, int number) {
        for (int i = 0; i < number; i++) {
            cards.add(deck.draw());
        }
    }

    @Override
    abstract public String getName();

    @Override
    abstract public String getInitialCards();

    @Override
    public String getHoldingCards() {
        return this.cards.toString();
    }

    @Override
    public int getScoreOfCards() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    @Override
    public boolean isBust() {
        if (isOverBlackjackNumber()) {
            return hasChangedScore();
        }
        return false;
    }

    private boolean hasChangedScore() {
        if (findAceAndChangeScore()) {
            return isBust();
        }
        return true;
    }

    private boolean isOverBlackjackNumber() {
        return getScoreOfCards() > BLACKJACK_NUMBER;
    }

    private boolean findAceAndChangeScore() {
        for (Card card : cards) {
            return ifCardIsAceChangeScore(card);
        }
        return false;
    }

    private boolean ifCardIsAceChangeScore(Card card) {
        if (card.isAce()) {
            return card.changeScoreOfAce();
        }
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return getScoreOfCards() == BLACKJACK_NUMBER;
    }

    @Override
    abstract public boolean isGettingAdditionalCard();

    @Override
    abstract public String getMessageForAdditionalCard();

    @Override
    public boolean isScore(int score) {
        return getScoreOfCards() == score;
    }
}
