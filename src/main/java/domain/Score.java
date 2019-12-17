package domain;

import java.util.List;

import domain.card.Card;

/**
 * 얻은 카드를 바탕으로 점수를 계산하는 역할을 하는 클래스
 */
public class Score {
    private final static int BLACKJACKNUMBER = 21;
    private final static int ACEONE = 1;
    private final static int ACEELEVEN = 11;
    private final static int SEVENTEEN = 17;

    public int getSum(List<Card> card) {
	int sum = (int) card.stream().mapToInt(Card::getScore).sum();
	if (card.stream().anyMatch(x -> x.isAce())) {
	    sum = sum - ACEONE;
	    sum += plusAce(sum);
	}
	return sum;
    }

    public int plusAce(int sum) {
	if (sum + ACEELEVEN > BLACKJACKNUMBER) {
	    return ACEONE;
	}
	return ACEELEVEN;
    }

    public boolean isBlackJack(int sum) {
	if (sum == BLACKJACKNUMBER) {
	    return true;
	}
	return false;
    }

    public boolean isLessThanSeventeen(List<Card> card) {
	if (getSum(card) < SEVENTEEN) {
	    return true;
	}
	return false;
    }

}
