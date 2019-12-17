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
		getCardPerPlayer();
		getCardDealer();
		printResult();
		printWinner();
	}
	
	/**
	 * 플레이어를 입력받아서 초기화한다.
	 */
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
		double money = Double.parseDouble(bettingMoney);

		if (money == 0) {
			myPrinter.printNonZeroBettingMoney();
			throw new GameException();
		}
		return money;
	}
	
	/**
	 * 이름과 배팅 금액을 맵에 저장해둔 후 배팅 금액까지 정확하게 입력받으면
	 * 맵을 순차적으로 읽으면서 플레이어를 생성한다.
	 */
	private void addPlayer() {
		for (String name : nameMoneyMap.keySet()) {
			playerSet.add(new Player(name, nameMoneyMap.get(name)));
		}
	}
	
	/**
	 * 초기 두 장의 카드를 분배하는 함수
	 */
	private void giveInitialCard() {
		myPrinter.printInitialCard(makePlayerNameString());
		giveInitialCardToDealer();
		giveInitialCardToPlayer();
		printDealerCard();
		printPlayerSetCard();
	}

	private String makePlayerNameString() {
		ArrayList<String> nameSet = new ArrayList<String>();
		for (Player p : playerSet) {
			nameSet.add(p.getName());
		}
		return String.join(", ", nameSet);
	}
	
	/**
	 * 랜덤변수를 이용해 카드 묶음에서 선택할 카드를 생성한다.
	 * 심볼에서 하나, 타입에서 하나를 선택하고 만약 그 카드가 카드 묶음에
	 * 없다면 다시 카드를 생성한다.
	 * 카드를 사람에게 나눠준 후 그 카드는 카드 묶음에서 지운다.
	 * @return 카드 묶음에서 지운 카드
	 */
	private Card makeRandomCard() {
		Random ran = new Random();
		Symbol[] symbols = Symbol.values();
		Type[] types = Type.values();
		Card card;

		do {
			card = new Card(symbols[ran.nextInt(Constant.SYMBOL_COUNT)], types[ran.nextInt(Constant.TYPE_COUNT)]);
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

	private void printDealerCard() {
		myPrinter.printDealer();
		myPrinter.printCard(dealer.getCardString());
	}

	private void printPlayerSetCard() {
		for (Player p : playerSet) {
			printPlayerCard(p);
		}
	}

	private void printPlayerCard(Player player) {
		myPrinter.printPlayerNameWithCard(player.getName());
		myPrinter.printCard(player.getCardString());
	}

	private void getCardPerPlayer() {
		myPrinter.printNewLine();
		for (Player p : playerSet) {
			chooseGetCard(p);
		}
	}
	
	/**
	 * 카드를 추가로 뽑는 여부를 결정하는 함수
	 * 사용자의 입력값의 유효성을 검사하고 그 입력값에 맞는 동작을 수행한다.
	 * @param player 카드를 뽑을 플레이어
	 */
	private void chooseGetCard(Player player) {
		String answer;

		do {
			myPrinter.printChooseCard(player.getName());
			answer = in.next();
		} while (isGetAnotherCard(player, answer) && checkPlayerCardSum(player));
		myPrinter.printNewLine();
	}
	
	/**
	 * 새로운 카드를 뽑을지 선택하는 함수.
	 * 입력값의 유효성을 검사해 그 결과를 이용해 카드를 추가로 선택한다.
	 * @param player 카드를 뽑을 플레이어
	 * @param answer 사용자의 입력값
	 * @return 카드 선택 여부
	 */
	private boolean isGetAnotherCard(Player player, String answer) {
		try {
			checkAnswerValidation(answer);
			return returnByAnswer(player, answer);
		} catch (GameException e) {
			return true;
		}
	}

	private void checkAnswerValidation(String answer) {
		if (!(answer.equals(Constant.YES) || answer.equals(Constant.NO))) {
			myPrinter.printNotAllowedAnswer();
			throw new GameException();
		}
	}
	
	/**
	 * 사용자의 입력값에 따라 카드를 추가로 더하거나 뽑기를 멈춘다.
	 */
	private boolean returnByAnswer(Player player, String answer) {
		if (answer.equals(Constant.YES)) {
			player.addCard(makeRandomCard());
			printPlayerCard(player);
			return true;
		}
		return false;
	}
	
	/**
	 * 현재 플레이어의 카드 총 합이 21이 넘는다면 카드 뽑기를 멈추고 다음 플레이어에게 차례를 넘긴다.
	 * @param player 현재 뽑기 중인 플레이어
	 * @return 추가 카드 선택 가능 여부
	 */
	private boolean checkPlayerCardSum(Player player) {
		if (player.getCardSum() <= Constant.BLACKJACK) {
			return true;
		}
		myPrinter.printStopGetCard(player.getName());
		return false;
	}
	
	/**
	 * 딜러가 소유한 카드 총합을 계산해 16이하면 카드를 더 뽑고
	 * 17 이상이라면 카드를 뽑지 않는다.
	 */
	private void getCardDealer() {
		if (dealer.isContainAce() == true) {
			checkDealerSumWithAce();
			return;
		}
		checkDealerSum();
	}

	private void checkDealerSumWithAce() {
		if (dealer.getCardSumWithAce() == Constant.BLACKJACK) {
			myPrinter.printDealerStopGetCard();
			return;
		}
		checkDealerSum();
	}
	
	private void checkDealerSum() {
		if (dealer.getCardSum() <= Constant.DEALER_PICK) {
			myPrinter.printDealerGetCard();
			dealer.addCard(makeRandomCard());
			return;
		}
		myPrinter.printDealerStopGetCard();
	}
	
	/**
	 * 딜러와 모든 플레이어의 결과를 출력한다.
	 */
	private void printResult() {
		myPrinter.printNewLine();
		printDealerResult();
		printPlayerSetResult();
	}

	private void printDealerResult() {
		myPrinter.printDealer();
		myPrinter.printCard(dealer.getResultString());
	}

	private void printPlayerSetResult() {
		for (Player p : playerSet) {
			printPlayerResult(p);
		}
	}

	private void printPlayerResult(Player player) {
		myPrinter.printPlayerNameWithCard(player.getName());
		myPrinter.printCard(player.getResultString());
	}
	
	/**
	 * 우승자를 결정해 최종 수익을 출력한다.
	 * 딜러가 21을 초과하는 경우,
	 * 딜러가 블랙잭인 경우,
	 * 딜러가 21 미만의 수를 갖는 경우로 나눈다.
	 */
	private void printWinner() {
		myPrinter.printPrizeResult();
		if (dealer.determineScore() > Constant.BLACKJACK) {
			allPlayerWin();
		}
		if (dealer.determineScore() == Constant.BLACKJACK) {
			dealerWin();
		}
		if (dealer.determineScore() < Constant.BLACKJACK) {
			checkWinPerson();
		}
	}
	
	/**
	 * 딜러가 21을 초과하는 경우 모든 플레이어는 배팅 금액을 돌려 받는다.
	 */
	private void allPlayerWin() {
		printDealerAndPlayer(0d);
	}
	
	/**
	 * 딜러가 이겼을 때 딜러는 자신보다 적은 수를 갖은 플레이어들의 배팅 금액을 갖는다.
	 */
	private void dealerWin() {
		double dealerMoney = 0d;

		for (Player p : playerSet) {
			dealerMoney = dealerMoney + checkBlackJack(p);
		}
		printDealerAndPlayer(dealerMoney);
	}
	
	/**
	 * 플레이어가 블랙잭이거나 딜러와의 점수 차이를 계산해
	 * 딜러의 수익에 포함되는 금액을 계산한다.
	 * @param player 딜러와 비교할 플레이어
	 * @return 딜러의 수익금액에 더해질 금액
	 */
	private double checkBlackJack(Player player) {
		if (player.determineScore() == dealer.determineScore()) {
			player.setRate(Constant.WIN_RATE);
			return player.getBettingMoney();
		}
		player.setRate(Constant.LOOSE_RATE);
		if (player.determineScore() < dealer.determineScore()) {
			return player.getBettingMoney();
		}
		return 0d;
	}
	
	/**
	 * 딜러와 플레이어들 중 우승자를 가려낸다
	 */
	private void checkWinPerson() {
		int minScore = getMinScore();

		if (minScore == dealer.calculateMin()) {
			dealerWin();
			return;
		}
		calculateMoney(minScore);
	}
	
	/**
	 * 딜러와 플레이어들의 카드 중 21을 넘지 않으면서 가장 적은 차이를 계산한다.
	 * @return
	 */
	private int getMinScore() {
		int min = 100;

		min = isMin(dealer.calculateMin(), min);
		for (Player p : playerSet) {
			min = isMin(p.calculateMin(), min);
		}
		return min;
	}

	private int isMin(int num1, int num2) {
		if (num1 <= num2)
			return num1;
		return num2;
	}
	
	/**
	 * 딜러가 블랙잭이 아닐 때 딜러의 수익을 계산한다.
	 * @param min 딜러와 플레이어들의 수 중 가장 21과 근접한 차이값
	 */
	private void calculateMoney(int min) {
		double dealerMoney = 0;

		for (Player p : playerSet) {
			setPlayerRate(p, min);
			dealerMoney = dealerMoney + addDealerMoney(p);
		}
		printDealerAndPlayer(dealerMoney);
	}
	
	/**
	 * 플레이어의 상금 비율을 계산한다.
	 * @param player 상금을 계산할 플레이어
	 * @param min 딜러와 플레이어들의 수 중 가장 21과 근접한 차이값
	 */
	private void setPlayerRate(Player player, int min) {
		if (player.calculateMin() > min) {
			player.setRate(Constant.LOOSE_RATE);
			return;
		}
		if (player.calculateMin() == min) {
			checkCardSetSize(player, min);
		}
	}
	
	/**
	 * 플레이어가 받을 상금이 본전인지 보너스인지 계산한다.
	 * @param player player 상금을 계산할 플레이어
	 * @param min min 딜러와 플레이어들의 수 중 가장 21과 근접한 차이값
	 */
	private void checkCardSetSize(Player player, int min) {
		if (player.getCardSize() == Constant.TWO) {
			player.setRate(Constant.BONUS_RATE);
			return;
		}
		player.setRate(Constant.WIN_RATE);
	}
	
	/**
	 * 딜러가 받을 상금을 계산한다
	 * @param player
	 * @return 플레이어의 배팅 금액 * 플레이어의 상금 비율
	 */
	private double addDealerMoney(Player player) {
		if (player.getRate() == Constant.LOOSE_RATE) {
			return 0d;
		}
		return player.getBettingMoney() * player.getRate();
	}
	
	/**
	 * 결과를 출력한다.
	 * @param dealerMoney 딜러가 받는 수익금
	 */
	private void printDealerAndPlayer(double dealerMoney) {
		myPrinter.printDealer();
		myPrinter.printBettingMoney(dealerMoney);
		for (Player p : playerSet) {
			myPrinter.printPlayerName(p.getName());
			myPrinter.printBettingMoney(p.getBettingMoney() * p.getRate());
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
}
