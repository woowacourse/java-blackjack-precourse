package domain.project;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private static List<Card> cardSet;
	private Dealer dealer;
	private List<Player> playerSet;
	private Scanner in;
	
	public Game() {
		
	}
	
	private void run() {
		
	}
	
	private static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
}
