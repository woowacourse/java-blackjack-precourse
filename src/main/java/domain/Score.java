package domain;

import java.util.List;

import domain.card.Card;

/**
 * 얻은 카드를 바탕으로 점수를 계산하는 역할을 하는 클래스
 */
public class Score {
    
    public int getSum(List<Card> card) {
	int sum = (int)card.stream().mapToInt(Card::getScore).sum();
	if (card.stream().anyMatch(x -> x.isAce())) {
	    sum = sum - 1;
	    sum+=plusAce(sum);
	}
	return sum;
    }
    
    public int plusAce(int sum) {
	if(sum+11>21) {
	    return 1;
	}
	return 11;
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
