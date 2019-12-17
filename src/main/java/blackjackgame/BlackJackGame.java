
/*
 * ClassName : BlackJackGame
 * 
 * version : 0.1
 * 
 * date : 2019.12.15
 * 
 * author : ParkDooWon
 */

package blackjackgame;

import java.util.ArrayList;
import java.util.List;

import domain.card.Deck;
import domain.user.*;
import view.*;
import blackjackgame.Profit;

public class BlackJackGame {
	private static final String COMMA = ",";
	private static final int DRAW_TWO_CARDS = 2;

	private List<Player> players = new ArrayList<>();
	private List<String> blackJackList = new ArrayList<>();
	private List<Profit> profit = new ArrayList<>();
	private Dealer dealer = new Dealer();
	private Input input = new Input();
	private Output output = new Output();
	private Deck deck = new Deck();
	private String[] playerNames;
	
	public void startGame() {
		playerNames = input.getPlayerNames().split(COMMA);
		makePlayers();
		makeCardSet();
		shuffleCards();
		drawTwoCards();
		printDrawCards();
		checkBlackJack();
	}

	private void makePlayers() {
		profit.add(new Profit("딜러", 0));
		for (String name : playerNames) {
			profit.add(new Profit(name, 0));
			players.add(new Player(name, input.getBettingMoney(name)));
		}
	}

	private void makeCardSet() {
		deck.createCardSet();
	}

	private void shuffleCards() {
		deck.shuffleCards();
	}

	private void drawTwoCards() {
		for (int i = 0; i < DRAW_TWO_CARDS; i++) {
			drawOneCard();
		}
	}

	private void drawOneCard() {
		dealer.addCard(deck.drawCard());
		for (Player player : players) {
			player.addCard(deck.drawCard());
		}
	}
	
	private void printDrawCards() {
		output.printDrawCards(dealer, players, playerNames);
	}
	
	private void checkBlackJack() {
		for (Player player : players) {
			if (player.findBlackJack()) {
				blackJackList.add(player.getName());
			}
		}
		if (dealer.findBlackJack()) {
			blackJackList.add("딜러");
		}
		if (blackJackList.size() > 0) {
			((Profit) profit).blackJackPrice(players, dealer, blackJackList);
			output.blackJack(players, dealer, blackJackList);
			System.exit(0);
		}
	}
	
}
