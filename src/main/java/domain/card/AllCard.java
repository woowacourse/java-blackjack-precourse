package domain.card;

import java.util.Queue;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class AllCard {
    private static final Queue<Card> shuffledCards
            = AllCard.initShuffledCards();

    private static Queue<Card> initShuffledCards() {
        List<Card> cards = CardFactory.create();
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }

    public static Card pick() {
        checkEmpty();
        return shuffledCards.poll();
    }

    private static void checkEmpty() {
        if (shuffledCards.isEmpty()) {
            throw new NoSuchElementException();
        }
    }
}
