package controller;

import java.util.ArrayList;
import java.util.List;

import domain.user.Player;
import view.InputOutputView;

public class Game {
	private static List<Player> players = new ArrayList<>();
	private static List<Player> cards = new ArrayList<>();

	public void start() {
		init();

	}

	private static void init() {
		setPlayers(InputOutputView.inputPlayerName());
//		setCards();
	}

	private static void setPlayers(String inputPlayer) {
		for (String name : inputPlayer.split(",")) {
			players.add(new Player(name, InputOutputView.inputBettingMoney(name)));
		}

	}

}
