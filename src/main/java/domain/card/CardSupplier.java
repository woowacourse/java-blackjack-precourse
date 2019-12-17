package domain.card;

import java.io.IOException;
import java.util.List;

public class CardSupplier {
    private boolean[] isCrossedOut;
    private List<Card> allCards;
    private IndexGenerator indexGenerator;

    public CardSupplier(IndexGenerator indexGenerator) {
        this.isCrossedOut = new boolean[CardFactory.create().size()];
        this.indexGenerator = indexGenerator;

    }

    public Card supply() {
        int cardIndex = indexGenerator.getIndex();
        try {
            checkGeneratedIndex(cardIndex);
            isCrossedOut[cardIndex] = true;
        }
        catch (IOException e) {
            supply();
        }
        return allCards.get(cardIndex);
    }

    private void checkGeneratedIndex(int cardIndex) throws IOException {
        if (isCrossedOut[cardIndex]) throw new IOException();
    }
}
