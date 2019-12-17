package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_SIZE = 2;
    private static final int ACE_NUMBER = 1;
    private static final int ACE_ELEVEN_NUMBER = 11;

    protected List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateSum() {
        int result = 0;
        List<Card> sortedCards = new ArrayList<>(cards);
        sortedCards.sort((firstCard, secondCard) -> secondCard.getNumber() - firstCard.getNumber());

        for (Card card : sortedCards) {
            int cardNumber = checkAce(card, result);
            result += cardNumber;
        }

        return result;
    }

    private int checkAce(Card card, int score) {
        if (card.getNumber() == ACE_NUMBER && score < ACE_ELEVEN_NUMBER) {
            return ACE_ELEVEN_NUMBER;
        }
        return card.getNumber();
    }

    public boolean isBlackJack() {
        return calculateSum() == BLACK_JACK && cards.size() == BLACK_JACK_SIZE;
    }

    public boolean isBursted() {
        return calculateSum() > BLACK_JACK;
    }

    public abstract String getName();

}
