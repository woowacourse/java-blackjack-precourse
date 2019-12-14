package domain.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import domain.user.Dealer;
import domain.user.Player;

public class BlackJackGame {
	private static final int FIRST_INDEX = 0;
	private static final int INITIAL_CARDS = 2;
	private static final int DEALER_BORDER_SCORE = 16;
	private static final int BLACKJACK_SCORE = 21;
	private static final double LOSE_RATIO = -1.0;
	private static final double BLACKJACK_RATIO = 1.5;
	private static final char HIT = 'y';

	private CardShoe cardShoe;
	private Dealer dealer;
	private String[] playerNames;
	private List<Player> playerList = new ArrayList<>();
	private HashMap<String, Double> playerRewardMap = new HashMap<>();
	private double dealerReward;
	Scanner input = new Scanner(System.in);
	
	public BlackJackGame() {
		cardShoe = new CardShoe();
		dealer = new Dealer();
	}
	
	public void play() {
		inputPlayerInfos();
		initialDeal();
		showInitialDeal();
		
		playersTurn();
		dealerTurn();
		
		showGameScore();
	}
	
	private void inputPlayerInfos() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)");
		playerNames = input.next().split(",");
	
		for (String name : playerNames) {
			System.out.println(name + "의 배팅 금액은?");
			double bettingMoney = input.nextDouble();
			playerList.add(new Player(name, bettingMoney));
		}
	}
	
	private void initialDeal() {
		for (int i = 0; i < INITIAL_CARDS; i++) {
			dealer.addCard(cardShoe.getOneCard());
			playerList.stream()
					.forEach(player -> player.addCard(cardShoe.getOneCard()));
		}
	}
	
	private void showInitialDeal() {
		System.out.println("딜러와 " + String.join(",", Arrays.asList(playerNames)) + "에게 " + INITIAL_CARDS + "장의 카드를 나누었습니다."); 
		System.out.print("딜러 : ");
		System.out.println(dealer.showCardInfo());
		for (Player player : playerList) {
			System.out.print(player.getName() + " : ");
			System.out.println(player.showCardInfo());
		}
		System.out.println();
	}
	
	private void playersTurn() {
		for (Player player : playerList) {
			int playerScore = eachPlayerTurn(player);
			if (playerScore > BLACKJACK_SCORE) {
				playerRewardMap.put(player.getName(), player.getBettingMoney() * LOSE_RATIO);
				dealerReward += player.getBettingMoney();
			}
		}
	}
	
	private int eachPlayerTurn(Player player) {
		int playerScore = player.getPlayerScore();
		if (playerScore == BLACKJACK_SCORE) {
			return blackJack(player, playerScore);
		}
		boolean hit = true;
		while (playerScore < BLACKJACK_SCORE && hit) {
			System.out.println(player.getName() + "의 현재 점수는 " + playerScore + "입니다.\n한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
			char choice = input.next().charAt(FIRST_INDEX);
			hit = isChoiceHit(choice, player);
			playerScore = player.getPlayerScore();
		}
		return playerScore;
	}
	
	private boolean isChoiceHit(char choice, Player player) {
		if (choice == HIT) {
			player.addCard(cardShoe.getOneCard());
			System.out.print(player.getName() + " : ");
			System.out.println(player.showCardInfo());
			return true;
		}
		return false;
	}
	
	private int blackJack(Player player, int playerScore) {
		if (playerScore != dealer.getDealerScore()) {
			playerRewardMap.put(player.getName(), player.getBettingMoney() * BLACKJACK_RATIO);
			dealerReward -= player.getBettingMoney() * BLACKJACK_RATIO;
		}
		return playerScore;
	}
	
	private void dealerTurn() {
		System.out.println();
		while (dealer.getDealerScore() <= DEALER_BORDER_SCORE) {
			System.out.println("딜러는 " + DEALER_BORDER_SCORE + "이하라 한장의 카드를 더 받았습니다.");
			dealer.addCard(cardShoe.getOneCard());
		}
		System.out.println();
	}
	
	private void showGameScore() {
		System.out.print("딜러 : ");
		System.out.println(dealer.showCardInfo() + "-" + dealer.getDealerScore());
		for (Player player : playerList) {
			System.out.print(player.getName() + " : ");
			System.out.println(player.showCardInfo() + "-" + player.getPlayerScore());
		}
		System.out.println();
	}
	
}
