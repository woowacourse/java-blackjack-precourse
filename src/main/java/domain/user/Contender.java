package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.game.Deck;

public abstract class Contender {
    private static final int PAIR_NUMBER = 2;
    private static final int BUSTED_NUMBER = 22;
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
        return getSum() >= BUSTED_NUMBER;
    }

    private int getSum() {
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


}
