package domain.card;

import java.util.Collections;
import java.util.List;

public class Stack {
    private List<Card> stack;

    public Stack(List<Card> stack) {
        this.stack = stack;
    }

    public void shuffle() {
        Collections.shuffle(stack);
    }

    public Card popCard() {
        Card poppedCard = stack.get(0);
        stack.remove(0);
        return poppedCard;
    }
}
