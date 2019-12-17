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

    public int getSumOfCardScore(){
        int sum=0;
        for(Card card : cards){
            sum += card.getSymbol().getScore();
        }

        if( haveACECard() && is10BetterThen1(sum)){
            sum += 9;
        }
        return sum;
    }

    public boolean isBlackjack(){
        if(cards.size() == 2){
            return getSumOfCardScore() == 21;
        }
        return false;
    }


    private boolean is10BetterThen1(int sum){
        return sum+9<21;
    }

    private boolean haveACECard(){
        boolean haveACE = false;
        for(Card card: cards){
            haveACE = (haveACE || (card.getSymbol()==Symbol.ACE));
        }
        return haveACE;
    }

}
