package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class OutputView {
	private static final String CARD_NAMES_DELIMITER = ", ";
	private static final String DEALER_ADD_CARD_MESSAGE = "는 16이하라 한 장의 카드를 더 받았습니다.";

	public static void printCards(String name, List<Card> cards) {
		String cardNames = String.join(CARD_NAMES_DELIMITER,
				cards.stream()
						.map(Card::getName)
						.collect(Collectors.toList())
		);
		System.out.println(name + ": " + cardNames);
	}

	public static void printDealerAddCard(String name) {
		System.out.println(name + DEALER_ADD_CARD_MESSAGE);
	}
}
