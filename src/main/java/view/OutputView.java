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

	public void printCards(List<String> cardsText) {
		System.out.print(String.join(SEPARATOR[0], cardsText));
	}

	public void printPlayerCards(List<String> cardsData, String name) {
		String printData = name + CARD;
		System.out.print(printData + DELIMITERS[0]);
		printCards(cardsData);
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

	public void printNewLine() {
		System.out.println();
	}
}
