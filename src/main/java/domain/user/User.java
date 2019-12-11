package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

/**
 * User
 */
public class User{
    private final List<Card> cards = new ArrayList<>();
    
    
    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
    public List<Card> getCardList(){
        return cards;
   
    }
}