package domain.user;

import domain.card.Card;

import java.util.List;

public abstract class User {

    static final int BLACK_JACK = 21;
    static final int BLACK_JACK_SIZE = 2;

    public void addCard(List<Card> cards, Card card) {
        cards.add(card);
    }

    public int calculateSum(List<Card> cards) {
        int result = 0;

        for (Card card : cards) {
            int number = card.getNumber();
            if (number == 1) {
                result += 10;
            }
            result += number;
        }

        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    boolean isBlackJack(List<Card> cards) {
        return calculateSum(cards) == BLACK_JACK && cards.size() == BLACK_JACK_SIZE;
    }

    boolean isBursted(List<Card> cards) {
        return calculateSum(cards) > BLACK_JACK;
    }

    boolean isInGame(List<Card> cards) {
        return calculateSum(cards) < BLACK_JACK;
    }

    public abstract String getName();

}
