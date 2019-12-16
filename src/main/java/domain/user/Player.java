package domain.user;

import domain.Game;
import domain.card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User{
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }



    // TODO 추가 기능 구현


    public String toString(){
        return name+" : "+bettingMoney;
    }
    public void printCards(){
        for(Card card : cards){
            System.out.println(card);
        }
    }
    public void drawCard(int numberOfCard) {
        HashSet<Card> cards = new HashSet<>();

        while (cards.size() != numberOfCard) {
            Card card = new Card(Card.makeRandomSymbol(),
                    Card.makeRandomType());
            cards.add(card);
        }
        for (Card card : cards) {
            this.addCard(card);
        }
    }
    public int getSum(){
        int sum = 0;

        for(Card card : cards){
            sum += card.getSymbolValue();
        }
        return sum;
    }
    public int getAceCounter(){
        int aceCounter = 0;

        for(Card card : cards){
            aceCounter = card.isAce();
        }
        return aceCounter;
    }
    public ArrayList<Integer> getPossibleSum(){
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0;i<getAceCounter();i++){
            list.add(getSum() + i* 10);
        }
        return list;
    }
    public void addCard(Card card) {
        cards.add(card);
    }
    public ArrayList<Integer> draw(){
        ArrayList<Integer> possibleSum = getPossibleSum();
        if(possibleSum.get(0)<= Game.DRAW_FLAG){
            drawCard( 1);
            return getPossibleSum();
        }
        return getPossibleSum();
    }
    public boolean isBlackJack(){
        if(draw().contains(Game.BLACK_JACK)){
            return true;
        }
        return false;
    }

}
