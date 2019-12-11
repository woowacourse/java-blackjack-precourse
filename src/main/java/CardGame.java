import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.*;
/**
 * CardGame
 */
public class CardGame {
    List<Card> cards = new CardFactory().create();
    List<Card> usedCards = new ArrayList<>();

    public void start(){
        final Player player1 = new Player("jason", 20000);
        final Dealer dealer = new Dealer();
        usedCards.add(cards.get(0));
        usedCards.add(cards.get(1));
        System.out.println(usedCards.contains(cards.get(1)));

        System.out.println(cards.get(5).getSymbol().getScore());
        
    }
}