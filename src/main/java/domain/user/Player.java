package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player implements User {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    private double resultMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public String getName(){
        return name;
    }

    public String getCards(){
        int size = cards.size();
        StringBuilder msg = new StringBuilder();

        for(int idx = 0; idx < size; idx++){
            Card card = cards.get(idx);
            msg.append(card.getSymbol() + " " + card.getType() + ",");
        }
        return msg.toString();
    }

    public List<Card> getCardsList(){
        return cards;
    }

    public int getSumOfCards(){
        int sum = 0;
        int size = cards.size();

        for(Card card : cards){
            sum += card.getSymbol().getScore();
        }
        return sum;
    }

    public void setResultMoney(double money){
        this.resultMoney = money;
    }

    public double getResultMoney(){
        return resultMoney;
    }

    public double getBettingMoney(){
        return bettingMoney;
    }
}
