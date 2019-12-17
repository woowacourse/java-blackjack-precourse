package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static system.ResultSystem.BUST_CONDITION;

public class User {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public String toString() {
        List<String> stringCardList = cards.stream()
                .map(c -> c.toString())
                .collect(Collectors.toList());
        return String.join(", ", stringCardList);
    }

    public String hideFirstCard() {
        List<String> stringCardList = cards.stream()
                .map(c -> c.toString())
                .skip(1)
                .collect(Collectors.toList());
        return String.join(", ", stringCardList);
    }

    public int getSumScore() {
        int sum = 0;
        for (Card c : cards) {
            sum += c.getScore();
        }

        if (hasAce() && sum + 10 <= BUST_CONDITION) {
            return sum + 10;
        }
        return sum;
    }

    public boolean isSumUnderCondition(int condition) {
        return getSumScore() <= condition;
    }

    private boolean hasAce() {
        for (Card c : cards) {
            if (c.getScore() == 1) return true;
        }
        return false;
    }

    public boolean isBust() {
        return getSumScore() > BUST_CONDITION;
    }

    public boolean isBlackjack() {
        return getSumScore() == BUST_CONDITION;
    }
}
