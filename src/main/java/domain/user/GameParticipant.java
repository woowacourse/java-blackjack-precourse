package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;

public class GameParticipant {
    private final List<Card> cards = new ArrayList<>();
    private final String name;

    public GameParticipant(String name){
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards(){ return  cards;}

    public String getName(){return name;}

    private int getSumOfCardScore(){
        int sum=0;
        for(Card card : cards){
            sum += card.getSymbol().getScore();
        }
        return sum;
    }

    public boolean isBlackjack(){
        if(cards.size() == 2){
            return getSumOfCardScore() == 21;
        }
        return false;
    }

}
