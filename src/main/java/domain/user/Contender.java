package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.game.Deck;

public abstract class Contender implements Comparable<Contender> {
    private static final int PAIR_NUMBER = 2;
    public static final int BLACK_JACK_NUMBER = 21;
    private final List<Card> cards = new ArrayList<>();

    public abstract String getName();
    public abstract double getBettingMoney();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void drawPairFrom(Deck deck) {
        for (int i = 0; i < PAIR_NUMBER; i++) {
            addCard(deck.draw());
        }
    }

    public boolean isBusted() {
        return getSum() > BLACK_JACK_NUMBER;
    }

    public boolean notBusted() {
        return !isBusted();
    }

    public boolean isBlackJack() {
        return getSum() == BLACK_JACK_NUMBER;
    }

    public int getSum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getScore();
        }
        return sum;
    }

    @Override
    public String toString() {
        return getName() + "의 카드: " + cardsToString();
    }

    private String cardsToString() {
        List<String> cardList = new ArrayList<>();
        for (Card card : cards) {
            cardList.add(card.toString());
        }
        return String.join(", ", cardList);
    }

    @Override
    public int compareTo(Contender compared) {
        return compared.getSum() - getSum();
    }
}
