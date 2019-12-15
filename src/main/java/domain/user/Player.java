package domain.user;

import UI.Input.InputController;
import domain.card.Card;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player implements User {
    private static final String PLAYER_IS_GETTING_ADDITIONAL_CARD_MESSAGE = "님이 1장의 카드를 더 받습니다.";

    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    private Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static Player fromNameAndBettingMoney(String name, double bettingMoney) {
        return new Player(name, bettingMoney);
    }

    @Override
    public void addCard(Deck deck, int number) {
        for (int i = 0; i < number; i++) {
            cards.add(deck.draw());
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getInitialCards() {
        return getHoldingCards();
    }

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
    public boolean isGettingAdditionalCard() {
        return !isBust() && InputController.askIfGetCard(this.name);
    }

    @Override
    public String getMessageForAdditionalCard() {
        return PLAYER_IS_GETTING_ADDITIONAL_CARD_MESSAGE;
    }
}
