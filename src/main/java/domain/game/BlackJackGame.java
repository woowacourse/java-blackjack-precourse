package domain.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import domain.user.Dealer;
import domain.user.Player;

public class BlackJackGame {
	private static final int FIRST_INDEX = 0;
	private static final int INITIAL_CARDS = 2;
	private static final int DEALER_BORDER_SCORE = 16;
	private static final int BLACKJACK_SCORE = 21;
	private static final char HIT = 'y';
	private static final char STAY = 'n';

	private CardShoe cardShoe;
	private Dealer dealer;
	private List<Player> playerList = new ArrayList<>();
	private RewardCalculator rewardCalculator;
	Scanner input = new Scanner(System.in);

	public BlackJackGame() {
		cardShoe = new CardShoe();
		dealer = new Dealer();
	}

	public void play() {
		inputPlayerInfos();
		initialDeal();
		showInitialDeal();
		rewardCalculator = new RewardCalculator(playerList);

		playersTurn();
		dealerTurn();

		showGameScore();
		showGameReward();
	}

	private void inputPlayerInfos() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)");
		String[] playerNames = input.next().split(",");

		for (String name : playerNames) {
			System.out.println(name + "의 배팅 금액은?");
			double bettingMoney = input.nextDouble();
			playerList.add(new Player(name, bettingMoney));
		}
	}

	private void initialDeal() {
		for (int i = 0; i < INITIAL_CARDS; i++) {
			dealer.addCard(cardShoe.getOneCard());
			playerList.stream().forEach(player -> player.addCard(cardShoe.getOneCard()));
		}
	}

	private void showInitialDeal() {
		System.out.println(
				"딜러와 " + playerList.stream()
									.map(player -> player.getName())
									.collect(Collectors.joining(",")) 
				+ "에게 " + INITIAL_CARDS + "장의 카드를 나누었습니다.");
		System.out.println("딜러 : " + dealer.showInitialCardInfo());
		for (Player player : playerList) {
			System.out.println(player.getName() + " : " + player.showCardInfo());
		}
		System.out.println();
	}

	private void playersTurn() {
		for (Player player : playerList) {
			eachPlayerTurn(player);
		}
	}

	private void eachPlayerTurn(Player player) {
		int playerScore = player.getPlayerScore();
		if (playerScore == BLACKJACK_SCORE && playerScore != dealer.getDealerScore()) {
			rewardCalculator.blackJackReward(player);
		}
		boolean hit = true;
		while (playerScore < BLACKJACK_SCORE && hit) {
			System.out.println(player.getName() + "의 현재 점수는 " + playerScore + "입니다.\n한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
			char choice = input.next().charAt(FIRST_INDEX);
			hit = isChoiceHit(choice, player);
			playerScore = player.getPlayerScore();
		}
		if (playerScore > BLACKJACK_SCORE) {
			rewardCalculator.playerBustReward(player);
		}
	}

	private boolean isChoiceHit(char choice, Player player) {
		if (choice == HIT) {
			player.addCard(cardShoe.getOneCard());
			System.out.println(player.getName() + " : " + player.showCardInfo());
			System.out.println();
			return true;
		}
		return false;
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
			System.out.println(player.getName() + " : " + player.showCardInfo() + "-" + player.getPlayerScore());
		}
		System.out.println();
	}

	private void showGameReward() {
		System.out.println("## 최종 수익");
		rewardCalculator.calculateRewards(playerList, dealer.getDealerScore());
		rewardCalculator.showRewardInfo();
	}

}
