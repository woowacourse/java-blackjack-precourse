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
	private List<Double> earningsList;
	private List<Gambler> winnerList;
	private List<Gambler> drawsList;
	private Gambler dealer;
	private Double dealerShare = 0.0;
	private boolean dealerWin;

	public Table(List<String> players, List<Double> bettings) {
		playerList = PlayerFactory.create(players, bettings);
		dealer = new Dealer();
		deck = new Deck();
		deck.shuffle();
		Double tempList[] = new Double[players.size()];
		Arrays.fill(tempList, 0.0);
		earningsList = Arrays.asList(tempList);
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
		winnerList = playerList.stream()
			.filter(player -> (!player.isBust() && player.sumMax() >= winnerScore) || dealer.isBust())
			.collect(Collectors.toList());
		if (dealer.sumMax() >= winnerScore) {
			dealerWin = true;
		}
	}

	public void setDraws() {
		drawsList = playerList.stream()
			.filter(player -> (player.sumMax() == dealer.sumMax()))
			.collect(Collectors.toList());
	}

	public boolean isPlayerWin() {
		return winnerList.size() > 0;
	}

	public boolean isDealerWin() {
		return dealerWin;
	}

	public boolean hasWinner() {
		return isDealerWin() || isPlayerWin();
	}

	public void doSettlement(double winnerRatio, double loserRatio) {
		double dealerEarning = 0;
		dealerEarning += doWinnerSettlement(winnerRatio);
		dealerEarning += doLoserSettlement(loserRatio);
		dealerShare = dealerEarning;
		return;
	}

	private double doWinnerSettlement(double winnerRatio) {
		double dealerEarning = 0;

		for (Gambler gambler : playerList.stream()
			.filter(gambler -> (winnerList.contains(gambler) && !drawsList.contains(gambler)))
			.collect(Collectors.toList())
		) {
			Player winner = (Player)gambler;
			earningsList.set(playerList.indexOf(gambler), winnerRatio * winner.getBettingMoney());
			dealerEarning -= winnerRatio * winner.getBettingMoney();
		}
		return dealerEarning;
	}

	private double doLoserSettlement(double loserRatio) {
		double dealerEarning = 0;
		for (Gambler gambler : playerList.stream()
			.filter(gambler -> (!winnerList.contains(gambler)) && (!drawsList.contains(gambler)))
			.collect(Collectors.toList())
		) {
			Player loser = (Player)gambler;
			earningsList.set(playerList.indexOf(gambler), loserRatio * loser.getBettingMoney());
			dealerEarning -= loserRatio * loser.getBettingMoney();
		}
		return dealerEarning;
	}

	public List<Gambler> getPlayerList() {
		return playerList;
	}

	public List<String> getPlayerNames() {
		return playerList.stream()
			.map(player -> ((Player)player).getName())
			.collect(Collectors.toList());
	}

	public List<Double> getPlayerEarnings() {
		return earningsList;
	}

	public Gambler getDealer() {
		return dealer;
	}

	public Double getDealerShare() {
		return dealerShare;
	}

	public List<List<String>> getPlayersCardText() {
		ArrayList<List<String>> playersCardText = new ArrayList<>();
		for (Gambler player : playerList
		) {
			playersCardText.add(player.getCardsText());
		}
		return playersCardText;
	}

	public List<Integer> getPlayerResults() {
		return playerList.stream()
			.map(Gambler::sumMax)
			.collect(Collectors.toList());
	}

	public List<String> getDealerCardText() {
		return dealer.getCardsText();
	}

}
