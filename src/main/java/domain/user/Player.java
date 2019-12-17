package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public double getBettingMoney(){
        return bettingMoney;
    }
    public String getName(){
        return name;
    }

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

        System.out.println(buffer.substring(0,buffer.length()-1));

    }


    // TODO 추가 기능 구현

}
