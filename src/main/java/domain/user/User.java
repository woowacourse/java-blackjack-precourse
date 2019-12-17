package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final List<Card> cards = new ArrayList<>();

    public User(){};

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getMaxScore() {
        int totalScore = 0;
        int cardScore;

        for(Card card:cards) {
            cardScore = card.getScore();

            if(cardScore == 1) {
                totalScore += 11;
            } else {
                totalScore += cardScore;
            }
        }
        return totalScore;
    }

    public int getNormalScore() {
        int totalScore = 0;

        for(Card card:cards) {
            totalScore += card.getScore();
        }
        return totalScore;
    }
}
