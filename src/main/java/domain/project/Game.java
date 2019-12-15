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
	private String[] playerName;
	private List<Player> playerSet;
	private Scanner in;
	private Printer myPrinter;
	
	public Game() {
		CardFactory cardFactory = new CardFactory();
		cardSet = cardFactory.create();
		dealer = new Dealer();
		playerSet = new ArrayList<Player>();
		in = new Scanner(System.in);
		myPrinter = new Printer();
	}
	
	private void run() {
		initialSet();
	}
	
	private void initialSet() {
		inputName();
	}
	
	private void inputName() {
		String name;
		
		try {
			myPrinter.printInputPlayer();
			name = in.next();
			checkNameValidation(name);	
		} catch (GameException e) {
			inputName();
		}
	}
	
	private void checkNameValidation(String name) {
		isStartComma(name);
		isEndComma(name);
		parseName(name);
	}
	
	private void isStartComma(String name) {
		if (name.startsWith(Constant.COMMA)) {
			myPrinter.printEmptyPlayerName();
			throw new GameException();
		}
	}
	
	private void isEndComma(String name) {
		if (name.endsWith(Constant.COMMA)) {
			myPrinter.printEmptyPlayerName();
			throw new GameException();
		}
	}
	
	private void parseName(String name) {
		playerName = name.split(Constant.COMMA);
		for (String s : playerName) {
			isEmptyPlayerName(s);
		}
		checkPlayerSize(playerName);
	}
	
	private void isEmptyPlayerName(String name) {
		if (name.equals(Constant.EMPTY)) {
			myPrinter.printEmptyPlayerName();
			throw new GameException();
		}
	}
	
	private void checkPlayerSize(String[] playerName) {
		int size = playerName.length;
		
		checkSizeUnderTwo(size);
		checkSizeOverEight(size);
	}
	
	private void checkSizeUnderTwo(int size) {
		if (size < Constant.TWO) {
			myPrinter.printPlayerSizeUnderTwo();
			throw new GameException();
		}
	}
	
	private void checkSizeOverEight(int size) {
		if (size > Constant.EIGHT) {
			myPrinter.printPlayerSizeOverEight();
			throw new GameException();
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
}
