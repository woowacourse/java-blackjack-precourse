package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

public class Gamer {
    private static final Integer BLACKJACK = 21;

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack(){
        return sumOfCard() == BLACKJACK;
    }

    public Integer sumOfCard() {
        int sum = 0;
        for(int i=0; i<getCards().size(); i++){
            sum += getCards().get(i).getSymbol().getScore();
        }
        if(hasAce() && sumOfCard() <= 11)
            sum += 10;
        return sum;
    }

    protected boolean hasAce() {
        return getCards().contains(Symbol.ACE);
    }

}
