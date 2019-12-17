package domain.user;

import domain.card.Card;
import domain.project.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 플레이어와 딜러의 부모 클래스. 플레이어와 딜러의 공통 로직과 변수들을 포함한다.
 * 
 * @author giantim
 */
public class Person {
	private final List<Card> cards = new ArrayList<>();
	private double rate; // 플레이어의 상금 비율을.

	public Person() {
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	/**
	 * 소유하고 있는 카드의 숫자와 종류를 출력할때 필요한 문자열을 만든다.
	 * 
	 * @return 카드의 숫자와 종류로 이루어진 문자열
	 */
	public String getCardString() {
		String cardString = "";

		ArrayList<String> cardStringSet = new ArrayList<String>();

		for (Card c : cards) {
			cardStringSet.add(c.makeCardString());
		}
		cardString = String.join(", ", cardStringSet);
		return cardString;
	}

	/**
	 * 카드의 숫자와 종류로 이루어진 문자열에 결과값을 더한 문자열을 만든다.
	 * 
	 * @return 카드의 숫자와 종류로 이루어진 문자열에 결과값을 더한 문자열
	 */
	public String getResultString() {
		return getCardString() + Constant.RESULT + Integer.toString(determineScore());
	}

	/**
	 * ace를 갖고 있는 여부를 판단하여 ace를 11로 더할 것인지 결정하고 ace를 갖고있지 않다면 총 카드의 합만 계산한다.
	 * 
	 * @return 21을 넘지 않으면서 가장 큰 값
	 */
	public int determineScore() {
		if (isContainAce() == true && getCardSumWithAce() == Constant.BLACKJACK) {
			return getCardSumWithAce();
		}
		if (isContainAce() == true && getCardSumWithAce() <= 21 && getCardSumWithAce() >= getCardSum()) {
			return getCardSumWithAce();
		}
		return getCardSum();
	}

	/**
	 * 현재 카드 중에 ace를 갖고 있는지 판단한다
	 * 
	 * @return ace 포함 여부
	 */
	public boolean isContainAce() {
		boolean ace = false;

		for (Card c : cards) {
			ace = checkAce(c, ace);
		}
		return ace;
	}

	/**
	 * 현재 검사하는 카드가 ace 카드인지 확인한다
	 * 
	 * @param card 비교할 카드
	 * @param ace  ace를 소유하고 있는지 판단하는 변수
	 * @return ace 소유 여부
	 */
	private boolean checkAce(Card card, boolean ace) {
		if (ace == true) {
			return true;
		}
		if (card.getSymbolName().equals(Constant.ACE)) {
			return true;
		}
		return false;
	}

	/**
	 * 갖고 있는 카드의 총합을 계산한다.
	 * 
	 * @return 카드의 총 합
	 */
	public int getCardSum() {
		int cardSum = 0;

		for (Card c : cards) {
			cardSum = cardSum + c.getSymbolScore();
		}
		return cardSum;
	}

	/**
	 * ace를 갖고있을 때 10을 더한 값을 계산한다
	 * 
	 * @return 현재 소유한 카드의 총 합 더하기 10
	 */
	public int getCardSumWithAce() {
		return getCardSum() + Constant.PLUS_ACE;
	}

	/**
	 * 갖고 있는 카드들의 개수를 계산한다
	 * 
	 * @return 갖고 있는 카드의 개수
	 */
	public int getCardSize() {
		return this.cards.size();
	}

	/**
	 * 상금 비율을 설정한다
	 * 
	 * @param rate 상금의 비율
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * 상금 비율을 반환한다
	 * 
	 * @return 상금 비율
	 */
	public double getRate() {
		return this.rate;
	}

	/**
	 * 카드의 총합과 21과의 차이를 계산하여 승리자를 판단할때 사용한다.
	 */
	public int calculateMin() {
		if (determineScore() > Constant.BLACKJACK) {
			return Constant.MAX;
		}
		return Constant.BLACKJACK - determineScore();
	}
}
