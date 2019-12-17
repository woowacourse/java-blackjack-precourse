package domain.distribution;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Cards;

/**
 * 카드를 나눠주는 기계 역할을 담당
 */
public class CardDistributionMachine {
    private final Cards cards;
    private final DistributionSequence distributionSequence;

    public CardDistributionMachine() {
        this.cards = new Cards();
        this.distributionSequence = new DistributionSequence();
    }

    public Card getCard() {
        return cards.getCardByIndex(distributionSequence.get());
    }

    public List<Card> getCardsBySize(int size) {
        List<Card> cardStore = new ArrayList<>();
        for (int count = 0; count < size; count++) {
            cardStore.add(cards.getCardByIndex(distributionSequence.get()));
        }
        return cardStore;

    }
}
