package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = 0;
        for(Card card : cards) {
            score += card.getScore();
        }
        for(Card card : cards) {
            score += card.getAceScore(score);
        }
        return score;
    }

    public String getCardString() {
        List<String> nameList = new ArrayList<String>();
        String cardString = "딜러 : \n";
        for(Card card : cards) {
            nameList.add(card.getCardName());
        }
        return cardString + String.join(",",nameList);
    }

    public String getScoreString() {
        return " - 결과: " + Integer.toString(getScore());
    }
}
