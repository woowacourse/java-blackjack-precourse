package domain.card;

import java.util.List;

public class CardTest {

    private static List<Card> cards = CardFactory.create();

    public static Card getAceCardFixture() {
        return cards.get(0);
    }

    public static Card getKingCardFixture() {
        return cards.get(51);
    }
}
