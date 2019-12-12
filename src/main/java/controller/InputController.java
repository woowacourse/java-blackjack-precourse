package controller;

import static view.InputView.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class InputController {
	private Scanner scanner;

	public InputController() {
		this.scanner = new Scanner(System.in);
	}

	public List<Player> getPlayers(List<String> playerNames) {
		List<Player> players = new ArrayList<>();

		for (String playerName : playerNames) {
			players.add(new Player(playerName, getPlayerBettingMoney(playerName)));
		}

		return players;
	}

	private int getPlayerBettingMoney(String playerName) {
		printAskPlayerBettingAmount(playerName);
		return Integer.parseInt(getUserInput());
	}

	private List<String> getPlayerNames() {
		printAskPlayerName();
		String[] playerNames = getUserInput().split(",");
		return Arrays.asList(playerNames);
	}

	private String getUserInput() {
		return scanner.next();
	}
}
