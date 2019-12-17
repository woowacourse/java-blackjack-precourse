
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

public class BlackJackGame {
	private static final String COMMA = ",";
	private static final int DRAW_TWO_CARDS = 2;

	private List<Player> players = new ArrayList<>();
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
	}

	private void makePlayers() {
		for (String name : playerNames) {
			players.add(new Player(name, input.getBattingMoney(name)));
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

}
