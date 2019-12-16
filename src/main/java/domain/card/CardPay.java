package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.user.Person;

public class CardPay {
	private final static int MAX_RANDOM = 52;
	private List<Card> cardList;
	private Random random;

	public CardPay() {
		cardList = new ArrayList<Card>();
		cardList = CardFactory.create();
		random = new Random();
	}

	public void giveUserCard(Person person) {
		person.addCard(randomNumberSelect());
	}

	private Card randomNumberSelect() {
		int value;
		while (hasCheck(value = random.nextInt(MAX_RANDOM))) {
		}
		return cardList.get(value);
	}

	private boolean hasCheck(int value) {
		return cardList.get(value).getExist();
	}
}
