package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * CardDeck
 */
public class CardDeck{
    List<Card> deck = new ArrayList<Card>();
    
    public CardDeck(){
        List<Card> cards = new CardFactory().create();
        System.out.println(cards.get(0).getSymbol());
        for (Card each : cards){
            
            deck.add(each);
        }
    }
    
    public Card pick(){
        Random rand = new Random(); 
        int rand_num = rand.nextInt(deck.size());
        Card picked = deck.get(rand_num);
        deck.remove(rand_num);
        return picked;
    }

    public List<Card> getDeck(){
        return deck;
    }

    
}