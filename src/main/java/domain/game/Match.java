package domain.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Gambler;
import domain.user.PlayerFactory;

public class Match {
	private Deck deck;
	private List<Gambler> playerList;
	private Gambler dealer;
	private List<Gambler> winnerList;

	public Match(List<String> players, List<Double> bettings) {
		playerList = PlayerFactory.create(players, bettings);
		dealer = new Dealer();
		winnerList = new ArrayList<>();
		deck = new Deck();
		deck.shuffle();
	}

	public void drawAll(int count) {
		try {
			drawCards(Arrays.asList(dealer), count);
			drawCards(playerList, count);
		} catch (Exception e) {
			System.out.println(Rule.getOutOfCardsMessage());
		}
	}

	private void drawCards(List<Gambler> gamblers, int count) throws Exception {
		for (Gambler gambler : gamblers
		) {
			drawCard(gambler, count);
		}
		return;
	}

	private void drawCard(Gambler gambler, int count) throws Exception {
		for (int i = 0; i < count; i++) {
			gambler.addCard(deck.draw());
		}
	}

	public void findBlackJackWinner(int blackjackPoint) {
		winnerList = playerList.stream()
			.filter(player -> (player.sumCardsMax() == blackjackPoint))
			.collect(Collectors.toList());
	}

	public boolean hasWinners() {
		return winnerList.size() > 0;
	}
}
