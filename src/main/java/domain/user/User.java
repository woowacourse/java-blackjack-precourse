package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getSumOfAllCardNumbers() {
        return 0;
    }

    public String[] getCardsStrings() {
        return null;
    }
}
