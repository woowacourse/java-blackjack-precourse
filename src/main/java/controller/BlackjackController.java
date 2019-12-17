package controller;

import static view.OutputView.*;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.BlackjackMember;
import domain.user.Dealer;
import domain.user.Player;

public class BlackjackController {
	public static final int BLACKJACK = 21;
	public static final int DEALER_ADD_POINT = 16;
	public static final int ACE_TERM = 10;
	public static final int ZERO = 0;

	private List<Card> cards;
	private Dealer dealer;
	private List<Player> players;
	private List<BlackjackMember> entry;

	public BlackjackController(List<Player> players) {
		this.cards = initCards();
		this.entry = new ArrayList<>();
		this.players = players;
		this.dealer = new Dealer();
		entry.add(new Dealer());
		entry.addAll(players);
	}

	public List<Card> initCards() {
		List<Card> cards = new ArrayList<>();

		for (Card card : CardFactory.create()) {
			cards.add(card);
		}

		return cards;
	}

	public void firstServe() {
		for (BlackjackMember blackjackMember : entry) {
			blackjackMember.addCard(cards.remove((int)(Math.random() * cards.size())));
			blackjackMember.addCard(cards.remove((int)(Math.random() * cards.size())));
		}

		printFirstServeMessage(entry);
		printAllStatus(entry);
	}

	private void checkDeck() {

	}

}
