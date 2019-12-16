package utils;

import java.util.List;

import domain.card.Card;

public class CardsPrinter {
	public static void printWithName(String name, List<Card> cards) {
		System.out.println(name + " 카드 : " + String.join(", ", cards.toString()));
	}
}
