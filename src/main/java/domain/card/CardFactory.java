package domain.card;

import java.util.Set;
import java.util.HashSet;

public class CardFactory {
	/* 과제 시작시 주어진 메서드. List -> Set로 변경하여 사용중 */
	public static Set<Card> create() {
		Set<Card> cards = new HashSet<>();
		Symbol[] symbols = Symbol.values();
		for (Symbol symbol : symbols) {
			createByType(cards, symbol);
		}
		return cards;
	}

	/* 과제 시작시 주어진 메서드 . List -> Set로 변경하여 사용중 */
	private static void createByType(Set<Card> cards, Symbol symbol) {
		Type[] types = Type.values();
		for (Type type : types) {
			cards.add(new Card(symbol, type));
		}
	}
}
