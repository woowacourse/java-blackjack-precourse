package domain.user;

import domain.card.Card;

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

    String printCardValue() {
        return String.join(", ", getCardValue());
    }

    private int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    int getRealScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        if (score <= 11 && hasAce()) {
            score += 10;
        }
        return score;
    }

    boolean validateUnder(int number) {
        return getRealScore() < number;
    }

    boolean isBlackjack() {
        //System.out.println("블랙잭입니다!");
        return hasAce() && getScore() == 11 && cards.size() == 2;
    }

    private boolean hasAce() {

        ArrayList<Boolean> hasAce = new ArrayList<>();
        for (Card card : cards) {
            hasAce.add(checkHasAce(card));
        }

        return hasAce.contains(true);
    }

    private boolean checkHasAce(Card card) {
        return card.getSymbol().equals("ACE");
    }

}
