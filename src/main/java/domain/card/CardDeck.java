package domain.card;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Random;

public class CardDeck {
	private Set<Card> cards;
	
	public CardDeck() {
		cards = CardFactory.create();
	}
	
	public Card pop() {
		checkEmpty();
		Card randomCard = selectRandomCard();
		this.cards.remove(randomCard);
		return randomCard;
	}
	
	private void checkEmpty() {
		if (this.cards.isEmpty()) {
			throw new IllegalStateException("덱을 전부 뽑았습니다");
		}
	}
	
	private Card selectRandomCard() {
		int randNum = new Random().nextInt(this.cards.size());
		return this.cards.stream()
				.skip(randNum)
				.findFirst()
				.orElse(null);
	}
	
	public String toString() {
		return this.cards.stream()
				.map(Card::toString)
				.collect(Collectors.joining("\n"));
	}
}
