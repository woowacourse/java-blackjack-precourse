package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public int calculate_score(){
        int score = 0;
        for(Card card : cards){
            score += card.getSymbol().getScore();
        }
        return score;
    }

    public void print_player_cards(){
        String buffer = new String("");
        for(Card card : cards){
            buffer = buffer.concat(card.toString() + ",");
        }

        if(buffer.length() != 0){
            System.out.println(buffer.substring(0,buffer.length()-1));
        }

    }
}
