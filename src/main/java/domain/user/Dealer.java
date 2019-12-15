package domain.user;

import domain.card.Card;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer implements User {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_MINIMUM_SCORE_TO_STAY = 17;
    private static final String DEALER_SCORE_IS_UNDER_MINIMUM_MESSAGE = "딜러의 점수가 17미만이므로 1장의 카드를 더 받습니다.";

    private final List<Card> cards = new ArrayList<>();

    private Dealer() {}

    public static Dealer create() {
        return new Dealer();
    }

    @Override
    public void addCard(Deck deck, int number) {
        for (int i = 0; i < number; i++) {
            cards.add(deck.draw());
        }
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public String getInitialCards() {
        return this.cards.toString();
    }

    @Override
    public String getHoldingCards() {
        return this.cards.subList(1, this.cards.size()).toString();
    }

    @Override
    public int getScoreOfCards() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    @Override
    public boolean isBust() {
        return getScoreOfCards() > BLACKJACK_NUMBER;
    }

    @Override
    public boolean isBlackjack() {
        return getScoreOfCards() == BLACKJACK_NUMBER;
    }

    @Override
    public boolean isGettingAdditionalCard() {
        return getScoreOfCards() < DEALER_MINIMUM_SCORE_TO_STAY;
    }

    @Override
    public String getMessageForAdditionalCard() {
        return DEALER_SCORE_IS_UNDER_MINIMUM_MESSAGE;
    }
}
