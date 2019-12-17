package domain;

import java.util.List;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

public class Score {
    private int dealerscore;
    private int[] playerscore;
    
    public int getSum(List<Card> card) {
	int sum = (int)card.stream().mapToInt(Card::getScore).sum();
	if (card.stream().anyMatch(x -> x.isAce())) {
	    sum = sum - 1;
	    sum=plusAce(sum);
	}
	return sum;
    }
    
    public void setScore(Player[] player) {
	playerscore=new int[player.length];
	for(int i=0;i<player.length;i++) {
	    this.playerscore[i]=getSum(player[i].getCards());
	}
    }
    
    public void setScore(Dealer dealer) {
	this.dealerscore=getSum(dealer.getCards());
    }
    
    public int plusAce(int sum) {
	if(sum+11>21) {
	    return sum+1;
	}
	return sum+11;
    }
    
    public boolean isBlackJack(int sum) {
	if(sum==21) {
	    return true;
	}
	return false;
    }
    
    public boolean isLessthanSeventeen(List<Card> card) {
	if(getSum(card)<17) {
	    return true;
	}
	return false;
    }
}
