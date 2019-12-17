/*
 * BlackJackGame
 * v1.0
 * 2019.12.17
 */

package domain.game;

import domain.card.*;
import domain.user.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlackJackGame {

	Dealer dealer = new Dealer();
	List<Player> players = new ArrayList<Player>();;
	List<Card> cards = CardFactory.create();
	int cardsIndex = 0;

	public void allPlay() {
		initiate();
		for (int i = 0; i < players.size(); i++) {
			play(players.get(i));
		}
//		dealerPlay();
//		printResult();
	}

	public void initiate() {
		inputUserInfo();
		distributeCards();
	}

	// 유저 정보 입력받고 생성
	public void inputUserInfo() {
		List<String> playerNames = inputNames();
		for (int playerIndex = 0; playerIndex < playerNames.size(); playerIndex++) {
			double bettingMoney = inputBettingMoney(playerNames.get(playerIndex));
			players.add(new Player(playerNames.get(playerIndex), bettingMoney));
		}
	}

	public void distributeCards() {
		Collections.shuffle(cards);
		dealer.addCard(cards.get(cardsIndex++));
		dealer.addCard(cards.get(cardsIndex++));
		for (int i = 0; i < players.size(); i++) {
			players.get(i).addCard(cards.get(cardsIndex++));
			players.get(i).addCard(cards.get(cardsIndex++));
		}
		UI.printDistributeResult(dealer, players);
	}

	public List<String> inputNames() {
		Scanner scan = new Scanner(System.in);
		UI.requestUserNameUI();
		String input = scan.nextLine();// 1명 미만, 이상한입력 예외처리 필요
		return Arrays.asList(input.split(","));
	}

	public double inputBettingMoney(String playerName) {
		Scanner scan = new Scanner(System.in);
		UI.printBettingMoney(playerName);
		double bettingMoney = scan.nextDouble();
		return bettingMoney;
	}

	public void play(Player player) {
		while (UI.isOneMoreCard(player)) {
			player.addCard(cards.get(cardsIndex++));
			UI.printCards(player);
		}
	}
}
