package domain.game;

import java.util.ArrayList;

import domain.view.ViewInput;

public class Game {
	private static Game blackJack = new Game();
	
	private ArrayList<String> playerName = new ArrayList<String>();
	
	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		makePlayerName(ViewInput.getPlayerNames());
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
		
		getAllBettingPrice();
	}
	
	public void getAllBettingPrice() {
		for (String player : playerName) {
			int money = ViewInput.getBettingPrice(player);
		}
	}
	
	public int moneyIsValid(int money) throws IllegalArgumentException {
		return money;
	}
 }
