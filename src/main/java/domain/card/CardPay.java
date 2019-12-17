package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.user.Person;

/**
 * CardPay
 * 버전 : 1.0
 * 카드를 딜러와 사용자에게 지급하는 클래스
 */
public class CardPay {
	private final static int MAX_RANDOM = 52;
	private final static int FIRST_CARD_NUM = 2;
	private List<Card> cardList;
	private Random random;

	public CardPay() {
		cardList = new ArrayList<Card>();
		cardList = CardFactory.create();
		random = new Random();
	}

	public void firstGiveUserCard(Person person) {
		for (int i = 0; i < FIRST_CARD_NUM; i++) {
			person.addCard(randomNumberSelect());
		}
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

	public void giveUserCard(String addCheck, Person person) {
		if (addCheck.equals("y")) {
			person.addCard(randomNumberSelect());
			person.printCard();
		}
	}
}
