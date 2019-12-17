package domain.blackjackgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class BlackJackGame {
	private final Scanner scanner;
	private final List<Player> players;
	private String[] playerNames;

	public BlackJackGame() {
		scanner = new Scanner(System.in);
		players = new ArrayList<>();
	}

	public void init() {
		while (!inputPlayer()) {
		}
	}

	private boolean inputPlayer() {
		String playerInput = scanner.nextLine();
		playerNames = playerInput.split(",");
		if (playerNames.length == 0) {
			return false;
		}
		for (String playerName : playerNames) {
			while (!inputBettingMoney(playerName)) {
			}
		}
		return true;
	}

	private boolean inputBettingMoney(String playerName) {
		double bettingMoney = 0;
		try {
			bettingMoney = Double.parseDouble(scanner.nextLine());
			Player player = new Player(playerName, bettingMoney);
			players.add(player);
			return true;
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다. 다시 입력해 주세요");
			return false;
		}
	}

}
