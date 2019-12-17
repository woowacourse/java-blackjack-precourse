package domain.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import controller.InputController;
import domain.card.Card;
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

	public List<Gambler> getPlayerList() {
		return playerList;
	}

	public List<String> getPlayerNames() {
		return playerList.stream()
			.map(player -> ((Player)player).getName())
			.collect(Collectors.toList());
	}

	public Gambler getDealer() {
		return dealer;
	}

	public List<String[]> getPlayersCardText() {
		ArrayList<String[]> playersCardText = new ArrayList<>();
		for (Gambler player : playerList
		) {
			playersCardText.add(player.getCardsText());
		}
		return playersCardText;
	}

	public String[] getDealerCardText() {
		return dealer.getCardsText();
	}

	public void drawAll(List<Gambler> gamblers, int count) throws Exception {
		for (Gambler gambler : gamblers
		) {
			drawCards(gambler, count);
		}
		return;
	}

	public void drawDealer(int count) throws Exception {
		drawCards(dealer, count);
	}

	public void drawCards(Gambler gambler, int count) throws Exception {
		for (int i = 0; i < count; i++) {
			gambler.addCard(deck.draw());
		}
	}

	public void setWinners(int winnerScore) {
		for (Gambler player : playerList
		) {
			player.setWinner(player.sumCardsMax() > winnerScore-1 && !player.isBust(winnerScore));
		}
		dealer.setWinner(dealer.getSum() > winnerScore);
	}

	public boolean isPlayerWin() {
		return playerList.stream()
			.anyMatch(Gambler::isWinner);
	}

	public boolean isDealerWin() {
		return dealer.isWinner();
	}

	public boolean hasWinner() {
		return isDealerWin() || isPlayerWin();
	}

	public void doSettlement(double winnerRatio, double loserRatio) {
		double dealerEarning = 0;
		dealerEarning += doWinnerSettlement(winnerRatio);
		dealerEarning += doLoserSettlement(loserRatio);
		dealer.setEarnings(dealerEarning);
		return;
	}

	private double doWinnerSettlement(double winnerRatio) {
		double dealerShare = 0;
		for (Gambler gambler : playerList.stream()
			.filter(gambler -> (gambler.isWinner()) && (gambler.getSum() > dealer.getSum()))
			.collect(Collectors.toList())
		) {
			Player winner = (Player)gambler;
			winner.setEarnings(winnerRatio * winner.getBettingMoney());
			dealerShare -= winnerRatio * winner.getBettingMoney();
		}
		return dealerShare;
	}

	private double doLoserSettlement(double loserRatio) {
		double dealerShare = 0;
		for (Gambler gambler : playerList.stream()
			.filter(gambler -> (!gambler.isWinner()))
			.collect(Collectors.toList())
		) {
			Player loser = (Player)gambler;
			loser.setEarnings(loserRatio * loser.getBettingMoney());
			dealerShare -= loserRatio * loser.getBettingMoney();
		}
		return dealerShare;
	}
}
