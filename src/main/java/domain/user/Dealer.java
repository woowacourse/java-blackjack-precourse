package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public String showOneCard() {
    	Card oneCard = cards.get(0);
    	return oneCard.showSymbol() + " " + oneCard.showType();
    }
    
    public String showCard() {
    	List<String> cardList = new ArrayList<>();
    	for (Card card : cards) {
    		cardList.add(card.showSymbol() + " " + card.showType());
    	}
    	return String.join(", ", cardList);
    }
    
    public int showScore() {
    	int score = 0;
    	for (Card card : cards) {
    		score += Symbol.valueOf(card.showSymbol()).getScore();
    	}
    	return score;
    }
}
