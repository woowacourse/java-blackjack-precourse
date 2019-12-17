package application.domain.card;

import application.util.IndexGenerator;

import java.io.IOException;
import java.util.List;

public class CardSupplier {
    private boolean[] isCrossedOut;
    private final List<Card> allCards;
    private IndexGenerator indexGenerator;

    public CardSupplier(IndexGenerator indexGenerator) {
        this.isCrossedOut = new boolean[CardFactory.create().size()];
        this.allCards = CardFactory.create();
        this.indexGenerator = indexGenerator;
    }

    public Card supply() {
        int cardIndex = indexGenerator.getIndex();
        try {
            checkAndCrossOutIndex(cardIndex);
        }
        catch (IOException e) {
            supply();
        }
        return allCards.get(cardIndex);
    }

    private void checkAndCrossOutIndex(int cardIndex) throws IOException {
        if (isCrossedOut[cardIndex]) throw new IOException();
        isCrossedOut[cardIndex] = true;
    }
}
