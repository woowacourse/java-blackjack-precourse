import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.user.*;
/**
 * CardGame
 */
public class CardGame {
    
    CardDeck deck = new CardDeck();
    List<Card> cards = deck.getDeck();
    List<Player> players = new ArrayList<Player>();
    Dealer dealer = new Dealer();

    public CardGame(){
    }

    public void start(){
        createUsers();
        
        
        
        
        
    }

    public void createUsers(){
        String[] name = {"pobi","jason"};
        int[] bet = {10000,20000};
        for (int i=0; i<name.length; i++){
            players.add(new Player(name[i], bet[i]));
        }
    }
    public void giveInitialCards(){
        for(Player each : players){
            each.addCard(deck.pick());
            each.addCard(deck.pick());
        }
        dealer.addCard(deck.pick());
    }

}