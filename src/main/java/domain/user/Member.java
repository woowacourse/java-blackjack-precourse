package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Member {
    private final List<Card> cards = new ArrayList<>();

    public int getOptimizedSum() {
        int sum = getSum();
        if (hasAce()) {
            if (sum < 12) {
                sum += 10;
            }
        }
        return sum;
    }

    public int getSum() {
        int sum = 0;
        for (Card card: cards) {
            sum += card.getScore();
        }

        return sum;
    }

    public boolean hasAce() {
        for (Card card: cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String toStringCards() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card: cards) {
            stringBuilder.append(card.toString());
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    public abstract String cardInfo();
}
