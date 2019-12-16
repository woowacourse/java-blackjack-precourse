package domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deck {
    private static final int DECK_COUNT = 51;

    private static Deck deck;
    private List<Card> cards = CardFactory.create();
    private Set<Integer> setNotForDuplicationOfIndex = new HashSet<>();

    public static Deck getInstance() {
        if (deck == null) {
            deck = new Deck();
        }
        return deck;
    }

    public Card getRandomCard() {
        return cards.get(makeRandomIndex());
    }

    private int makeRandomIndex() {
        int before = setNotForDuplicationOfIndex.size();
        int randomNumber = (int) (Math.random() * DECK_COUNT);
        setNotForDuplicationOfIndex.add(randomNumber);
        int after = setNotForDuplicationOfIndex.size();
        if (before == after)
            return makeRandomIndex();
        return randomNumber;
    }

}
