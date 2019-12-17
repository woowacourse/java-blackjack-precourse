
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
import domain.user.Player;
import view.Input;

public class BlackJackGame {
	private static final String COMMA = ",";
	
	private List<Player> players = new ArrayList<>();
	private Input input = new Input();
	private Deck deck = new Deck();
	private String[] playerNames;

	public void startGame() {
		playerNames = input.getPlayerNames().split(COMMA);
		makePlayers();
		makeCardSet();
		shuffleCards();
	}
	
	private void makePlayers () {
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

}
