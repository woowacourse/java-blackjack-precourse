package domain.user;

import domain.card.Card;
import domain.game.BlackJack;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer{
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
            score += getAceScore(score, card);
        }
        return score;
    }


    public int getCardListSize() {
        return cards.size();
    }
    public String getScoreString() {
        return " - 결과: " + Integer.toString(getScore());
    }


    public int getAceScore(int score, Card card){
        if(score <= BlackJack.MAX_SCORE - BlackJack.ACE_BONUS_SCORE && card.isAceCard()){
            return BlackJack.ACE_BONUS_SCORE;
        }
        return 0;
    }
    public String getCardStringWithName() {
        return "딜러 " + getCardString();
    }
    public String getCardString() {
        List<String> nameList = new ArrayList<String>();
        String cardString = "카드: ";
        for(Card card : cards) {
            nameList.add(card.getCardName());
        }
        return cardString + String.join(",",nameList);
    }
}
