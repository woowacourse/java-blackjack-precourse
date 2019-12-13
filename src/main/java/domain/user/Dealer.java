package domain.user;

import domain.card.Card;
import domain.card.Deck;
import view.Output;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Player {
    private static final int DEALER_MIN_STAND_NUMBER = 17;

    public Dealer() {
        super("딜러", 0);
    }

    public boolean isDealerUnderSixteen() {
        return getScoreWithAceCheck() < DEALER_MIN_STAND_NUMBER;
    }

    public void dealerMoreCard(Deck deck) {
        if (isDealerUnderSixteen()) {
            Output.showDealerMoreCard();
            addCard(deck.drawCard());
        }
    }
}
