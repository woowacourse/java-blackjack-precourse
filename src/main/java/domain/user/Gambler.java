package domain.user;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public abstract class Gambler {
	private int sum;

	private boolean winner;

	private double earnings = 0;

	public abstract void addCard(Card card);

	protected abstract List<Card> getCards();

	public int sumCardsMin() {
		sum = getCards().stream()
			.map(Card::getSymbolScore)
			.reduce(0,Integer::sum);

		return sum;
	}

	public int sumCardsMax() {
		sumCardsMin();
		List<Card> aces = getCards().stream()
			.filter(card -> (card.getSymbolScore() == 1))
			.collect(Collectors.toList());

		for (Iterator<Card> iterator = aces.iterator(); sum + 10 <= 21 && iterator.hasNext(); iterator.next()) {
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

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public double getEarnings() {
		return earnings;
	}

	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}

	public List<String> getCardsText() {
		return getCards().stream()
			.map(Card::getCardData)
			.collect(Collectors.toList());
	}
}
