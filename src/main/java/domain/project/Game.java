package domain.project;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

public class Game {
	private static List<Card> cardSet;
	private Dealer dealer;
	private HashMap<String, Double> nameMoneyMap;
	private List<Player> playerSet;
	private Scanner in;
	private Printer myPrinter;

	public Game() {
		CardFactory cardFactory = new CardFactory();
		cardSet = new ArrayList<Card>();
		cardSet.addAll(cardFactory.create());
		dealer = new Dealer();
		playerSet = new ArrayList<Player>();
		in = new Scanner(System.in);
		myPrinter = new Printer();
	}

	private void run() {
		initialSet();
		giveInitialCard();
	}

	private void initialSet() {
		inputName();
		inputBettingMoney();
		addPlayer();
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
		nameMoneyMap = new HashMap<String, Double>();
		String[] playerName = name.split(Constant.COMMA);

		for (String s : playerName) {
			isEmptyPlayerName(s);
		}
		checkPlayerSize(playerName);
		addName(playerName);
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

	private void addName(String[] playerName) {
		for (String s : playerName) {
			nameMoneyMap.put(s, 0d);
		}
	}

	private void inputBettingMoney() {
		for (String s : nameMoneyMap.keySet()) {
			setBettingMoneyPerPlayer(s);
		}
	}

	private void setBettingMoneyPerPlayer(String name) {
		String bettingMoney;

		try {
			myPrinter.printInputBettingMoney(name);
			bettingMoney = in.next();
			checkBettingMoneyValidation(name, bettingMoney);
		} catch (GameException e) {
			setBettingMoneyPerPlayer(name);
		}
	}

	private void checkBettingMoneyValidation(String name, String bettingMoney) {
		isNum(bettingMoney);
		addBettingMoney(name, bettingMoney);
	}

	private void isNum(String bettingMoney) {
		for (char c : bettingMoney.toCharArray()) {
			checkAscii(c);
		}
	}

	private void checkAscii(char c) {
		if (!(c >= Constant.ASCII_ZERO && c <= Constant.ASCII_NINE)) {
			myPrinter.printInputNumber();
			throw new GameException();
		}
	}

	private void addBettingMoney(String name, String bettingMoney) {
		nameMoneyMap.put(name, convertBettingMoney(bettingMoney));
	}

	private double convertBettingMoney(String bettingMoney) {
		return Double.parseDouble(bettingMoney);
	}

	private void addPlayer() {
		for (String name : nameMoneyMap.keySet()) {
			playerSet.add(new Player(name, nameMoneyMap.get(name)));
		}
	}

	private void giveInitialCard() {
		myPrinter.printInitialCard(makePlayerNameString());
		giveInitialCardToDealer();
		giveInitialCardToPlayer();
	}

	private String makePlayerNameString() {
		ArrayList<String> nameSet = new ArrayList<String>();
		for (Player p : playerSet) {
			nameSet.add(p.getName());
		}
		return String.join(", ", nameSet);
	}

	private Card makeRandomCard() {
		Random ran = new Random();
		Symbol[] symbols = Symbol.values();
		Type[] types = Type.values();
		Card card;

		do {
			card = new Card(symbols[ran.nextInt(12)], types[ran.nextInt(3)]);
		} while (!checkCardInCardSet(card));
		removeCardInCardSet(card);
		return card;
	}

	private boolean checkCardInCardSet(Card card) {
		if (cardSet.contains(card)) {
			return true;
		}
		return false;
	}

	private void removeCardInCardSet(Card card) {
		cardSet.remove(card);
	}

	private void giveInitialCardToDealer() {
		for (int i = 0; i < 2; i++) {
			dealer.addCard(makeRandomCard());
		}
	}

	private void giveInitialCardToPlayer() {
		for (Player p : playerSet) {
			giveTwoCardToPlayer(p);
		}
	}

	private void giveTwoCardToPlayer(Player player) {
		for (int i = 0; i < 2; i++) {
			player.addCard(makeRandomCard());
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
}
