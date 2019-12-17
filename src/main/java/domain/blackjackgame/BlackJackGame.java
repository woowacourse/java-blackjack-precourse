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

	private boolean inputPlayer() {
		String playerNames = scanner.nextLine();
		String[] splitedPlayerNames = playerNames.split(",");
		if (splitedPlayerNames.length == 0) {
			return false;
		}
		for (String playerName : splitedPlayerNames) {
			inputBettingMoney(playerName);
		}
	}

	private void inputBettingMoney(String playerName) {
		double bettingMoney = 0;
		try {
			bettingMoney = Double.parseDouble(scanner.nextLine());
			Player player = new Player(playerName, bettingMoney);
			players.add(player);
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다. 다시 입력해 주세요");
			inputBettingMoney(playerName);
		}
	}

}
