package domain.user;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public abstract class Gambler {
	private List<Card> cards;

	public abstract void addCard(Card card);

	public int sumCardsMin() {
		List<Integer> cardScores = cards.stream()
			.flatMapToInt(card -> card.getSymbol().getScore())
			.sorted().collect(Collectors.toList());
		int sum = cardScores.stream().reduce(0, Integer::sum);

		return sum;
	}

	public int sumCardsMax() {
		int sum = sumCardsMin();
		List<Integer> aces = cards.stream()
			.flatMapToInt(card -> card.getSymbol().getScore())
			.filter(score -> (score == 1))
			.collect(Collectors.toList());
		for (Iterator<Integer> iterator = aces.iterator(); sum < 21 && iterator.hasNext(); ) {
			sum += 10;
		}
		return sum;
	}
}
