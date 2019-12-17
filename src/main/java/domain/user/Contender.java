package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;
import domain.game.Deck;

public abstract class Contender implements Comparable<Contender> {
    public static final int BLACK_JACK_NUMBER = 21;
    private static final int ADDABLE_NUMBER_BEFORE_COUNTING_ACE = 12;
    private static final int NUMBER_TO_ADD_WHEN_CARDS_INCLUDES_ACE = 10;
    private static final int PAIR_NUMBER = 2;
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
        return getSum() == BLACK_JACK_NUMBER && cards.size() == PAIR_NUMBER;
    }

    public int getSum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getScore();
        }
        return sum;
    }

    public int getFinalSum() {
        int sum = getSum();
        if (includesAce() && sum < ADDABLE_NUMBER_BEFORE_COUNTING_ACE) {
            sum += NUMBER_TO_ADD_WHEN_CARDS_INCLUDES_ACE;
            System.out.println(getName() + "의 ACE는 11점으로 계산되었습니다.");
        }
        return sum;
    }

    private boolean includesAce() {
        return cards.stream().filter(this::isCardAce).count() > 0;
    }

    private boolean isCardAce(Card card) {
        return card.getScore() == Symbol.ACE.getScore();
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
        return compared.getFinalSum() - getFinalSum();
    }
}
