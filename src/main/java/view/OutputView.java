package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.profit.Profit;

public class OutputView {
	private static final String CARD_NAMES_DELIMITER = ", ";
	private static final String DEALER_ADD_CARD_MESSAGE = "는 16이하라 한 장의 카드를 더 받았습니다.";
	private static final String CONCAT_DELIMITER = " 카드: ";
	private static final String SCORE_DELIMITER = " - 결과: ";
	private static final String PROFIT_MESSAGE = "## 최종 수익";
	private static final String PROFIT_FORMAT = "%s: %+d\n";

	private static String concatNameAndCards(String name, List<Card> cards) {
		String cardNames = cards.stream()
				.map(Card::getName)
				.collect(Collectors.joining(CARD_NAMES_DELIMITER));
		return name + CONCAT_DELIMITER + cardNames;
	}

	public static void printNewLine() {
		System.out.println();
	}

	public static void printCards(String name, List<Card> cards) {
		System.out.println(concatNameAndCards(name, cards));
	}

	public static void printScore(String name, List<Card> cards, int score) {
		System.out.println(concatNameAndCards(name, cards) + SCORE_DELIMITER + score);
	}

	public static void printDealerAddCard(String name) {
		System.out.println(name + DEALER_ADD_CARD_MESSAGE);
	}

	private static void printProfit(Profit profit) {
		System.out.printf(PROFIT_FORMAT, profit.getName(), (int)profit.getProfit());
	}

	public static void printProfits(List<Profit> profits) {
		System.out.println(PROFIT_MESSAGE);
		profits.forEach(OutputView::printProfit);
	}
}
