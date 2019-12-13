package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;
import view.InputOutputView;

public class Game {
	private static List<Player> players = new ArrayList<>();
	private static List<Card> cards = new ArrayList<>();

	public void start() {
		init();
		for (Card c : cards) {
			System.out.println(c.toString());
		}
	}

	private static void init() {
		initPlayers(InputOutputView.inputPlayerName());
		initCards();
	}

	private static void initPlayers(String inputPlayer) {
		for (String name : inputPlayer.split(",")) {
			players.add(new Player(name, InputOutputView.inputBettingMoney(name)));
		}
	}

	private static void initCards() {
		cards = CardFactory.create();
	}
}
