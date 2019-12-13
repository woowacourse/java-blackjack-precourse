package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User{
	private static final int FIRST = 1;
	
	public Dealer() {}

	//TODO 추가 기능 구현
	public String getCardsExceptFirstInString() {
		return this.cards.stream()
				.skip(FIRST)
				.map(card -> card.toString())
				.collect(Collectors.joining(", "));
	}
}
