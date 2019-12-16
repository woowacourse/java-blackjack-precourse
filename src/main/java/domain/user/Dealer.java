package domain.user;

import domain.Game;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

import java.util.*;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    // TODO 추가 기능 구현


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


    public ArrayList<Integer> Draw(){
        ArrayList<Integer> possibleSum = getPossibleSum();
        if(possibleSum.get(0)<=Game.DRAW_FLAG){
            drawCard(1);
            return getPossibleSum();
        }
        return getPossibleSum();
    }
    public boolean isBlackJack(){
        if(Draw().contains(Game.BLACK_JACK)){
            return true;
        }
        return false;
    }

}
