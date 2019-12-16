package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String name() {
    	return name;
    }
    
    public double bettingMoney() {
    	return bettingMoney;
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
    	int check = 0;
    	for (Card card : cards) {
    		score += Symbol.valueOf(card.showSymbol()).getScore();
    		check = aceCheck(card);
    	}
    	if (score < 12 && check == 1) {
    		score += 10;
    	}
    	return score;
    }
    
    public int aceCheck(Card card) {
    	int check = 0;
    	if (card.showSymbol() == "ACE") {
    		check = 1; 
    	}
    	return check;
    }

}
