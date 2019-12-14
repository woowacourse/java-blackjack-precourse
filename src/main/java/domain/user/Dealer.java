package domain.user;

import domain.card.Card;
import domain.dispenser.CardDispenser;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Gamer {
    private static final int DEALER_BOUND = 16;
    private static final String DEALER_NAME = "딜러";

    private final CardDispenser cardDispenser;

    public Dealer(CardDispenser dispenser) {
        cardDispenser = dispenser;
    }

    public Card pickCard() {
        return cardDispenser.pick();
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public boolean canReceive() {
        return getScore() <= DEALER_BOUND;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }
}
