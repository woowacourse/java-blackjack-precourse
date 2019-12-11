package domain.card;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Random;

public class CardDeck {
	private Set<Card> cards;
	
	public CardDeck() {
		cards = CardFactory.create();
		System.out.println(this.toString());
	}
	
	public Card drawCard() {
		if (this.cards.isEmpty()) {
			throw new IllegalStateException("덱을 전부 뽑았습니다");
		}
		Card randomCard = selectRandomCard();
		this.cards.remove(randomCard);
		return randomCard;
	}
	
	private Card selectRandomCard() {
		return this.cards.stream()
				.skip(new Random().nextInt(this.cards.size()))
				.findFirst()
				.orElse(null);
	}
	
	public String toString() {
		return this.cards.stream()
				.map(Card::toString)
				.collect(Collectors.joining("\n"));
	}
	
	public int getSize() {
		return this.cards.size();
	}
	
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
}
