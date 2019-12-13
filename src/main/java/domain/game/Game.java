package domain.game;

import java.util.ArrayList;

import domain.user.Player;
import domain.view.ViewInput;

public class Game {
	private static Game blackJack = new Game();
	
	private ArrayList<String> playerName = new ArrayList<String>();
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		makePlayerName(ViewInput.getPlayerNames());
		makePlayer();
	}
	
	public String nameIsValid(String name) throws IllegalArgumentException {
		if (name.equals("")) {
			throw new IllegalArgumentException();
		}
		
		return name;
	}
	
	public void makePlayerName(String name) {
		String[] names = name.split(",");
		
		for (String player : names) {
			playerName.add(player.trim());
		}
	}
	
	public void makePlayer() {
		for (String name : playerName) {
			Player player = new Player(name, ViewInput.getBettingPrice(name));
			
			players.add(player);
		}
	}
	
	public int moneyIsValid(int money) throws IllegalArgumentException {
		return money;
	}
	
 }
