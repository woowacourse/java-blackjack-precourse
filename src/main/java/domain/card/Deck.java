package domain.card;

import java.util.List;

public class Deck {
    private List<Card> deck = CardFactory.create();

    public Deck() {
    }

}
