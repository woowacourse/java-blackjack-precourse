package domain;

import java.util.List;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

public class Score {
    
    public int getSum(List<Card> card) {
	int sum = (int)card.stream().mapToInt(Card::getScore).sum();
	if (card.stream().anyMatch(x -> x.isAce())) {
	    sum = sum - 1;
	    sum=plusAce(sum);
	}
	return sum;
    }
    
    public int plusAce(int sum) {
	if(sum+11>21) {
	    return sum+1;
	}
	return sum+11;
    }
    
    public boolean isBlackJack(List<Card> card) {
	if(getSum(card)==21) {
	    return true;
	}
	return false;
    }
    
}
