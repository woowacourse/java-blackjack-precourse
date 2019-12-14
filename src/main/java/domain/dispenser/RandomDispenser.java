package domain.dispenser;

import domain.card.Card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class RandomDispenser extends CardDispenser {

    private final Queue<Card> shuffledCards;

    public RandomDispenser() {
        List<Card> cards = new LinkedList<>(super.cards);
        Collections.shuffle(cards);
        this.shuffledCards = new LinkedList<>(cards);
    }

    @Override
    public Card pick() {
        checkEmpty();
        return shuffledCards.poll();
    }

    private void checkEmpty() {
        if (shuffledCards.isEmpty()) {
            throw new NoSuchElementException();
        }
    }
}
