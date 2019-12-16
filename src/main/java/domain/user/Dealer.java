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
	private final List<Card> cards = new ArrayList<>();

	public Dealer() {
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	// TODO 추가 기능 구현

	public List<Card> getCards() {
		return cards;
	}

	public boolean bust() {
		if (sumCardScore() > 21) {
			return true;
		}
		return false;
	}

	public int sumCardScore() {
		if (containAce()) {
			int sum_ace_eleven = sumScore() + 10;
			if (sum_ace_eleven <= 21) {
				return sum_ace_eleven;
			}
		}
		return sumScore();
	}

	private int sumScore() {
		int sum = 0;

		for (Card card : cards) {
			sum += card.getScore();
		}
		return sum;
	}

	public boolean isBlackJack() {
		if (cards.size() == 2 && containAce() && sumCardScore() == 21) {
			return true;
		}
		return false;
	}

	private boolean containAce() {
		for (Card card : cards) {
			if (isAce(card)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAce(Card card) {
		return card.getSymbol().equals(Symbol.ACE);
	}

	public void checkCardNumber(CardSupplier cardSupplier) {
		if (bust()) {
			System.out.println("BURST! 21 초과");
		} else {
			OutputView.printDealerCards(this);
			if (OutputView.isLessThanSeventeen(sumCardScore())) {
				addCard(cardSupplier.getDeal());
				checkCardNumber(cardSupplier);
				return;
			}
		}
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
