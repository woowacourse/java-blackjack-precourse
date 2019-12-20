package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
	private final String name;
	private final double bettingMoney;
	private final List<Card> cards = new ArrayList<>();

	public Player(String name, double bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public String getName() {
		return name;
	}

	public double getBettingMoney() {
		return bettingMoney;
	}

	public void getSymbolAndType() {
		System.out.print(name + ": ");
		cards.stream().forEach(card -> System.out.print(card.getSymbol() + card.getType() + " "));
		System.out.print('\n');
	}

	public void getSymbolAndType(Dealer dealer) {
		System.out.print("딜러: ");
		System.out.println(cards.get(0).getSymbol() + cards.get(0).getType());
	}

	public String findBlackJack(int BLACKJACK) {
		int sum = 0;
		for (Card card : cards) {
			sum += card.getCardScore();
		}
		if (hasAce() && sum <= 11) {
			sum += 10;
		}
		if (sum == BLACKJACK) {
			return name;
		}
		return "";
	}

	public boolean hasAce() {
		return cards.stream().filter(card -> card.getSymbol() == "A").count() > 0;
	}

	public int sumCardScore() {
		int cardScore = cards.stream().mapToInt(card -> card.getCardScore()).sum();
		if (hasAce() && cardScore <= 11) {
			return cardScore + 10;
		}
		return cards.stream().mapToInt(card -> card.getCardScore()).sum();
	}

	public void printBustPlayer() {
		System.out.println(name + " is bust!");
	}
	
	public void printAllCards() {
		System.out.print(name + " : ");
		cards.stream().forEach(card -> System.out.print(card.getSymbol() + card.getType() + " "));
		System.out.println(" - 결과 : " + sumCardScore());
	}
	
	public void printAllCards(Dealer dealer) {
		System.out.print(dealer.getName() + " : ");
		cards.stream().forEach(card -> System.out.print(card.getSymbol() + card.getType() + " "));
		System.out.println(" - 결과 : " + sumCardScore());
	}

}
