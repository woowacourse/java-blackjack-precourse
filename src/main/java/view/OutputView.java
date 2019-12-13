package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class OutputView {
	private static final String CARD_NAMES_DELIMITER = ", ";

	public static void printCards(String name, List<Card> cards) {
		String cardNames = String.join(CARD_NAMES_DELIMITER,
				cards.stream()
						.map(Card::getName)
						.collect(Collectors.toList())
		);
		System.out.println(name + ": " + cardNames);
	}
}
