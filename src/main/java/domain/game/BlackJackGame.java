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
	List<Player> players = new ArrayList<Player>();
	List<Card> cards = CardFactory.create();
	int cardsIndex = 0;
	boolean isDealerAlive = true;
	boolean isDealerBlackJack = false;
	List<Boolean> isPlayersAlive = new ArrayList<Boolean>();
	List<Boolean> isPlayersBlackJack = new ArrayList<Boolean>();
	List<Double> resultMoney = new ArrayList<Double>();

	public void allPlay() {
		initiate();
		for (int i = 0; i < players.size(); i++) {
			play(players.get(i), i);
		}
		dealerPlay();
		printResult();
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

	public void distributeCards() {
		Collections.shuffle(cards);
		dealer.addCard(cards.get(cardsIndex++));
		dealer.addCard(cards.get(cardsIndex++));
		checkDealerBlackJack();
		for (int i = 0; i < players.size(); i++) {
			players.get(i).addCard(cards.get(cardsIndex++));
			players.get(i).addCard(cards.get(cardsIndex++));
			checkPlayerBlackJack(i, players.get(i));
		}
		UI.printDistributeResult(dealer, players);
	}
	
	public void checkDealerBlackJack() {
		if(dealer.getScore() == 21) {
			isDealerBlackJack = true;
		}
	}
	
	public void checkPlayerBlackJack(int index, Player player) {
		if(player.getScore() == 21) {
			isPlayersBlackJack.set(index, true);
			return;
		}
		isPlayersBlackJack.set(index, false);
	}
	
	public void play(Player player, int index) {
		while (UI.isOneMoreCard(player)) {
			player.addCard(cards.get(cardsIndex++));
			UI.printCards(player);
			checkPlayerAlive(player, index);
		}
	}
	
	public void checkPlayerAlive(Player player, int index) {
		if(isPlayersBlackJack.get(index)) {
			isPlayersAlive.set(index, false);
			return;
		}
		if(player.getScore() > 22) {
			isPlayersAlive.set(index, false);
			return;
		}
		isPlayersAlive.set(index, true);
	}
	
	public void checkDealerAlive() {
		if(dealer.getScore() > 22) {
			isDealerAlive = false;
		}
	}
	
	public void dealerPlay() {
		checkDealerBlackJack();
		if(dealer.isUnderOrEqual16()) {
			dealer.addCard(cards.get(cardsIndex++));
			UI.announceDealerReceivedOneCard();
		}
		checkDealerAlive();
	}
	
	public void printResult() {
		UI.printCardsAndResult(dealer);
		for(int i = 0; i<players.size(); i++) {
			UI.printCardsAndResult(players.get(i));
		}
		calculateResult();
		
	}
	
	public void calculateResult() {
		for(int i = 0; i<players.size(); i++) {
			if(!isPlayersAlive.get(i)) {
				resultMoney.set(i,-1*players.get(i).getBettingMoney());
			}
		}
		if(isDealerBlackJack) {
			inCaseDealerBlackJack();
		}
		if(!isDealerBlackJack) {
			inCaseDealerNotBlackJack();
		}
		
	}
	
	public void inCaseDealerBlackJack() {
		for(int i = 0; i<players.size(); i++) {
			if(isPlayersBlackJack.get(i)) {
				resultMoney.set(i, players.get(i).getBettingMoney());
			}
		}
	}
	
	public void inCaseDealerNotBlackJack() {
		for(int i = 0; i<isPlayersBlackJack.size(); i++) {
			if(isPlayersBlackJack.get(i)) {
				resultMoney.set(i, (1.5)*players.get(i).getBettingMoney());
			}
		}
	}
}
