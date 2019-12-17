package view;

import java.util.List;

public class OutputView {
	private static final String SEPARATOR[] = {", ", "쉼표"};
	private static final String DELIMITERS[] = {": ", " - "};
	private static final String CARD = "카드";
	private static final String DEALER = "딜러";
	private static final String RESULT = "결과";
	private static final String DEALER_DRAW_FORMAT = "%s는 %d이하라 한장의 카드를 더 받았습니다.";
	private static final String BLACKJACK_ROUND_DRAW_FORMAT = "%s와 %s에게 %d장의 나누었습니다.";
	private static final String EARNINGS_HEADER = "## 최종수익";

	private static OutputView outputView;

	private OutputView() {
	}

	public static OutputView getInstance() {
		if (outputView == null) {
			outputView = new OutputView();
		}
		return outputView;
	}

	public void printCards(List<String> cardsData) {
		System.out.print(String.join(SEPARATOR[0], cardsData));
	}

	public void printPlayerCards(String name, List<String> cardsData) {
		String printData = name + CARD;
		System.out.print(printData + DELIMITERS[0]);
		printCards(cardsData);
	}

	public void printPlayersCardsLine(List<String> names, List<List<String>> cardsData) {
		for (int i = 0; i < names.size(); i++) {
			printPlayerCards(names.get(i), cardsData.get(i));
			System.out.println();
		}
	}

	public void printPlayersCardsResultLine(List<String> names, List<List<String>> cardsData, List<Integer> results) {
		for (int i = 0; i < names.size(); i++) {
			printPlayerCards(names.get(i), cardsData.get(i));
			printResultLine(results.get(i));
		}
	}

	public void printDealerCards(List<String> cardsData, boolean withCardText) {
		String printData = DEALER;
		if (withCardText) {
			printData += " " + CARD;
		}
		System.out.print(printData + DELIMITERS[0]);
		printCards(cardsData);
	}

	public void printResultLine(int result) {
		String printData = DELIMITERS[1] + RESULT + DELIMITERS[0] + result;
		System.out.println(printData);
	}

	public void printDealerDrawLine(int dealerDrawPoint) {
		String printData = String.format(DEALER_DRAW_FORMAT, DEALER, dealerDrawPoint);
		System.out.println(printData);
	}

	public void printBlackJackRoundLine(List<String> names, int basicDraw) {
		String joinedNames = String.join(SEPARATOR[0], names);
		String printData = String.format(BLACKJACK_ROUND_DRAW_FORMAT, DEALER, joinedNames, basicDraw);
		System.out.println(printData);
	}

	public void printEarnings(List<String> names, List<Double> playerEarnings, Double dealerEarnings) {
		System.out.println(EARNINGS_HEADER);
		System.out.println(DEALER + DELIMITERS[0] + dealerEarnings);
		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i) + DELIMITERS[0] + playerEarnings.get(i));
		}
	}

	public void printNewLine() {
		System.out.println();
	}
}
