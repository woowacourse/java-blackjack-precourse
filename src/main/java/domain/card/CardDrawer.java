package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDrawer {
    private static final int TOTAL_CARD_NUMBER = 52;
    private static Random random = new Random();
    private static List<Card> cardDeck = CardFactory.create();
    private static List<Integer> drawnCard = new ArrayList<>();

    public static Card draw() {
        int idx = random.nextInt(TOTAL_CARD_NUMBER);

        if (!drawnCard.contains(idx)) {
            drawnCard.add(idx);
            return cardDeck.get(idx);
        }

        return draw();
    }
}
