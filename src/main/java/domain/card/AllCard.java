package domain.card;

import java.util.Collections;
import java.util.List;

public class AllCard {
    private static final List<Card> cards = CardFactory.create();

    public static List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public static void remove(Card card) {
        cards.remove(card);
    }
}
