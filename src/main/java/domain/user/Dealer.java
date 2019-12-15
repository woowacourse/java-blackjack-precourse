package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static domain.game.BlackJackGame.*;

/**
 * Dealer
 *
 * @author hyochan
 * @version 0.0.1
 * @since 2019-12-14
 */

public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBlackJack() {
        if (getCardScore() == MAX_Score && getCards().size() == 2) {
            return true;
        }
        return false;
    }

    public boolean isUnderMaxScore() {
        return MAX_Score >= getCardScore();
    }

    public boolean isOverMaxScore() {
        return MAX_Score < getCardScore();
    }

    public boolean isUnderDealerMinScore() {
        return DEALER_MIN_SCORE > getCardScore();
    }
}
