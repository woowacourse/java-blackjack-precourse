package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final List<Card> cards = new ArrayList<>();
    public Double profit;

    public User(){
        profit = 0.0;
    };

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

    public String getCardString(){
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;

        for(Card card:cards){
            if(first == true){
                first = false;
            } else{
                stringBuilder.append(", ");
            }
            stringBuilder.append(card.toString());
        }
        return stringBuilder.toString();
    }
}
