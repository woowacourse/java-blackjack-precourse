package domain.card;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = CardFactory.create();
    }

    public Card giveUnusedRandomCard(Set<Integer> usedCards) {
        Random random = new Random();
        int indexOfCard;
        int sizeOfBeforeAdd = usedCards.size();
        do {
            indexOfCard = random.nextInt(cards.size());
            usedCards.add(indexOfCard);
        } while (usedCards.size() == sizeOfBeforeAdd);
        return cards.get(indexOfCard);
    }
}
