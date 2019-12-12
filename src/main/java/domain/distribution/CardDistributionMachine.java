package domain.distribution;

import domain.card.Card;
import domain.card.Cards;

public class CardDistributionMachine {
    private final Cards cards;
    private final DistributionSequence distributionSequence;

    public CardDistributionMachine(Cards cards, DistributionSequence distributionSequence ) {
        this.cards = cards;
        this.distributionSequence = distributionSequence;
    }

    public Card distributeCard() {
        return cards.getCardByIndex(distributionSequence.get());
    }

}
