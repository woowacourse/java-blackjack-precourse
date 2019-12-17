package controller;

import static view.InputView.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class InputController {
	public static final String COMMA = ",";
	private Scanner scanner;

	public InputController() {
		this.scanner = new Scanner(System.in);
	}

	public List<Player> getPlayers() {
		List<Player> players = new ArrayList<>();

		for (String playerName : getPlayerNames()) {
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
		String[] playerNames = getUserInput().split(COMMA);
		return Arrays.asList(playerNames);
	}

	private String getUserInput() {
		return scanner.next();
	}
}
