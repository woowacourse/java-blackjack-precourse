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
	public static final int ACE_TERM = 10;
	public static final int ZERO = 0;

	private List<Card> cards;
	private Dealer dealer;
	private List<Player> players;
	private List<BlackjackMember> entry;

	public BlackjackController(List<Player> players) {
		this.cards = new ArrayList<>();
		this.entry = new ArrayList<>();
		this.players = players;
		this.dealer = new Dealer();
		entry.add(new Dealer());
		entry.addAll(players);
	}

	private List<Card> initCards() {
		List<Card> cards = new ArrayList<>();

		for (Card card : CardFactory.create()) {
			cards.add(card);
		}

		return cards;
	}

	private void firstServe() {
		for (BlackjackMember blackjackMember : entry) {
			blackjackMember.addCard(cards.remove(ZERO));
		}

		printFirstServeMessage(entry);
		printAllStatus(entry);
	}



}
