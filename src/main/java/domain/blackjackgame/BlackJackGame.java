package domain.blackjackgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class BlackJackGame {
	private final Scanner scanner;
	private final List<Player> players;

	public BlackJackGame() {
		scanner = new Scanner(System.in);
		players = new ArrayList<>();
	}

}
