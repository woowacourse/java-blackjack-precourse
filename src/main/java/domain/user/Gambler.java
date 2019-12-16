package domain.user;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public abstract class Gambler {
	private List<Integer> cardScores;

	private int sum;

	public abstract void addCard(Card card);

	public abstract List<Card> getCards();

	public int sumCardsMin() {
		cardScores = getCards().stream()
			.map(card -> card.getSymbolScore())
			.collect(Collectors.toList());

		sum = cardScores.stream()
			.reduce(0, Integer::sum);

		return sum;
	}

	public int sumCardsMax() {
		sumCardsMin();
		List<Integer> aces = cardScores.stream()
			.filter(score -> (score == 1))
			.collect(Collectors.toList());

		for (Iterator<Integer> iterator = aces.iterator(); sum+10 <= 21 && iterator.hasNext(); iterator.next()) {
			sum += 10;
		}

		return sum;
	}

	public int getSum() {
		return sum;
	}

	public boolean isBust(int blackJackPoint) {
		sumCardsMin();
		return sum > blackJackPoint;
	}
}
