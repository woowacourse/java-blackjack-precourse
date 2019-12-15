package domain.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import controller.InputController;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Gambler;
import domain.user.Player;
import domain.user.PlayerFactory;

public class Table {
	private Deck deck;
	private List<Gambler> playerList;
	private Gambler dealer;
	private List<Gambler> winnerList;

	public Table(List<String> players, List<Double> bettings) {
		playerList = PlayerFactory.create(players, bettings);
		dealer = new Dealer();
		winnerList = new ArrayList<>();
		deck = new Deck();
		deck.shuffle();
	}

	public void distribute(int count) {
		try {
			drawAll(Arrays.asList(dealer), count);
			drawAll(playerList, count);
		} catch (Exception e) {
			System.out.println(Rule.getOutOfCardsMessage());
		}
	}

	public List<Gambler> getPlayerList() {
		return playerList;
	}

	public Gambler getDealer() {
		return dealer;
	}

	public void drawAll(List<Gambler> gamblers, int count) throws Exception {
		for (Gambler gambler : gamblers
		) {
			drawCards(gambler, count);
		}
		return;
	}

	public void drawCards(Gambler gambler, int count) throws Exception {
		for (int i = 0; i < count; i++) {
			gambler.addCard(deck.draw());
		}
	}
}
