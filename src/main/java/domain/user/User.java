package domain.user;

import domain.card.Card;

import java.util.List;

public abstract class User {

    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_SIZE = 2;
    private static final int ACE_NUMBER = 1;
    private static final int ACE_ELEVEN_NUMBER = 11;

    void addCard(List<Card> cards, Card card) {
        cards.add(card);
    }

    int calculateSum(List<Card> cards) {
        int result = 0;

        for (Card card : cards) {
            result += checkAce(card, result);
        }

        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    private int checkAce(Card card, int score) {
        if (card.getNumber() == ACE_NUMBER && score < ACE_ELEVEN_NUMBER) {
            return ACE_ELEVEN_NUMBER;
        }
        return card.getNumber();
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
