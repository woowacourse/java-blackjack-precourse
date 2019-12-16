package domain.user;

import domain.card.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final List<Card> cards = new ArrayList<>();

    public User() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<String> getCardValue() {
        ArrayList<String> cardInfo = new ArrayList<>();
        for (Card card : this.cards) {
            cardInfo.add(card.printSymbolAndNumber());
        }
        return cardInfo;
    }

    public String printCardValue() {
        return String.join(", ", getCardValue());
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    boolean validateOver(int number) {
        return getScore() < number;
    }

}
