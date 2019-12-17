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

	public Table(List<String> players, List<Double> bettings) {
		playerList = PlayerFactory.create(players, bettings);
		dealer = new Dealer();
		deck = new Deck();
		deck.shuffle();
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
		dealer.setWinner(dealer.sumMax() >= winnerScore);
		for (Gambler player : playerList
		) {
			player.setWinner((!player.isBust() && player.sumMax() >= winnerScore )|| dealer.isBust());
		}
	}

	public void setDraws() {
		for (Gambler player:playerList
			 ) {
			player.setDraw(player.sumMax()==dealer.sumMax());
		}
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
			.filter(gambler -> (gambler.isWinner()&&!gambler.isDraw()))
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
			.filter(gambler -> (!gambler.isWinner()) && (!gambler.isDraw()))
			.collect(Collectors.toList())
		) {
			Player loser = (Player)gambler;
			loser.setEarnings(loserRatio * loser.getBettingMoney());
			dealerShare -= loserRatio * loser.getBettingMoney();
		}
		return dealerShare;
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
		return playerList.stream()
			.map(Gambler::getEarnings)
			.collect(Collectors.toList());
	}

	public Gambler getDealer() {
		return dealer;
	}

	public Double getDealerEarnings() {
		return dealer.getEarnings();
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
