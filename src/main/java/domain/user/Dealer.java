package domain.user;

import domain.card.Card;
import domain.card.CardSupplier;
import domain.card.Symbol;
import domain.view.OutputView;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
	private static final String NAME = "딜러";
	private static final int INIT_CARD_SIZE = 2;
	private static final int BLACKJACK_SCORE = 21;
	private static final int BURST_SCORE = 22;
	private static final int ACE_BONUS_SCORE = 10;
	private static final int DEALER_STAND_SCORE = 16;
	private final List<Card> cards = new ArrayList<>();

	public Dealer() {
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	// TODO 추가 기능 구현

	public static String getNAME() {
		return NAME;
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
		if (bust()) {
			System.out.println("BURST! 21 초과");
		} else {
			OutputView.printDealerCards(this);
			if (isLessThanSeventeen()) {
				System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
				addCard(cardSupplier.getDeal());
				checkCardNumber(cardSupplier);
				return;
			}
			System.out.println("딜러는 17이상이라 카드를 받지 않습니다.");
		}
	}

	public boolean isLessThanSeventeen() {
		return sumCardScore() <= DEALER_STAND_SCORE;
	}

	public String getDealerCards() {
		List<String> cardList = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		sb.append("딜러 카드");
		sb.append(": ");
		for (Card card : cards) {
			cardList.add(card.getCardSymbolAndType());
		}
		sb.append(String.join(",", cardList));
		return sb.toString();
	}

}
