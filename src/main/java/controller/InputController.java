package controller;

import static view.InputView.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputController {
	private Scanner scanner;

	public InputController() {
		this.scanner = new Scanner(System.in);
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
