package domain.user;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.game.Rule;

public abstract class Gambler {
	private double earnings = 0;

	public abstract void addCard(Card card);

	protected abstract List<Card> getCards();

	public int sumMin(){
		return getCards().stream()
			.map(Card::getSymbolScore)
			.reduce(0,Integer::sum);
	}

	public int sumMax(){
		int sumMax=sumMin();
		List<Card> aces = getCards().stream()
			.filter(card -> (card.getSymbolScore() == 1))
			.collect(Collectors.toList());
		for (Iterator<Card> iterator = aces.iterator(); sumMax + 10 <= Rule.BLACKJACK_POINT && iterator.hasNext(); iterator.next()) {
			sumMax += 10;
		}
		return sumMax;
	}

	public boolean isBust() {
		return sumMin() > Rule.BLACKJACK_POINT;
	}


	public double getEarnings() {
		return earnings;
	}

	public List<String> getCardsText() {
		return getCards().stream()
			.map(Card::getCardData)
			.collect(Collectors.toList());
	}
}
