package domain.user;

import domain.card.Card;
import domain.card.CardSupplier;
import domain.card.Symbol;
import domain.view.InputView;
import domain.view.OutputView;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
	private static final int INIT_CARD_SIZE = 2;
	private static final int BLACKJACK_SCORE = 21;
	private static final int BURST_SCORE = 22;
	private static final int ACE_BONUS_SCORE = 10;

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

	// TODO 추가 기능 구현

	public String getName() {
		return this.name;
	}

	public double getBettingMoney() {
		return bettingMoney;
	}

	public boolean bust() {
		if (sumCardScore() >= BURST_SCORE) {
			return true;
		}
		return false;
	}

	public int sumCardScore() {
		if (containAce()) {
			int sum_ace_eleven = sumScore() + ACE_BONUS_SCORE;
			if (sum_ace_eleven < BURST_SCORE) {
				return sum_ace_eleven;
			}
		}
		return sumScore();
	}

	private int sumScore() {
		int sum = cards.stream().map(Card::getScore).reduce(Integer::sum).get();
		return sum;
	}

	public boolean isBlackJack() {
		if (cards.size() == INIT_CARD_SIZE && containAce() && sumCardScore() == BLACKJACK_SCORE) {
			return true;
		}
		return false;
	}

	private boolean containAce() {
		return cards.stream().anyMatch(Card::isAce);
	}

	public void checkCardNumber(CardSupplier cardSupplier) {
		if (isBlackJack()) {
			System.out.println("WOW 블랙잭 입니다!!! 배당금의 1.5배를 가져갑니다.");
			return;
		}
		if (bust()) {
			System.out.println("BURST! 21 초과");
		} else {
			OutputView.printPlayerCards(this);
			if (InputView.getMoreCard(this)) {
				addCard(cardSupplier.getDeal());
				checkCardNumber(cardSupplier);
				return;
			}
		}
	}

	public String getPlayerCards() {
		List<String> cardList = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		sb.append(this.getName());
		sb.append(": ");
		for (Card card : cards) {
			cardList.add(card.getCardSymbolAndType());
		}
		sb.append(String.join(",", cardList));
		return sb.toString();
	}

}
