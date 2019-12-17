package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cardList;
    private int cardIndex=0;

    public Deck() {
        CardFactory cardFactory = new CardFactory();
        cardList=new ArrayList<Card>();
        cardList.addAll(cardFactory.create());
        Collections.shuffle(cardList);
    }

    public Card draw() {
        Card selectedCard = cardList.get(cardIndex++);
        return selectedCard;
    }
}
