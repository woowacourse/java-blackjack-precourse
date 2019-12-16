package domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.user.Dealer;
import domain.user.GameParticipant;
import domain.user.Player;

/**
 * 블랙잭 게임을 진행하는 객체
 * 
 * @author smr60
 *
 */
public class BlackJackGame {
	private static final int INITIAL_CARDS = 2;
	private static final int DEALER_BORDER_SCORE = 16;
	private static final int BLACKJACK_SCORE = 21;
	private static final char HIT = 'y';

	private InputManager inputManager;
	private CardShoe cardShoe;
	private RewardCalculator rewardCalculator;
	private GameParticipant dealer;
	private List<Player> playerList = new ArrayList<>();
	

	public BlackJackGame() {
		inputManager = new InputManager();
		cardShoe = new CardShoe();
		dealer = new Dealer();
	}

	/**
	 * 블랙잭 게임 전체 로직 담당하는 템플릿 메서드
	 */
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

	/**
	 * 플레이어 정보 입력 (이름, 배팅 금액)
	 */
	private void inputPlayerInfos() {
		for (String name : inputManager.inputPlayerNames()) {
			playerList.add(new Player(name, inputManager.inputBettingMoney(name)));
		}
	}

	/**
	 * 딜러와 플레이어 모두 2장씩 패를 돌려받는 메서드
	 */
	private void initialDeal() {
		for (int i = 0; i < INITIAL_CARDS; i++) {
			dealer.addCard(cardShoe.getOneCard());
			playerList.stream().forEach(player -> player.addCard(cardShoe.getOneCard()));
		}
	}

	private void showInitialDeal() {
		System.out
				.println("\n딜러와 " + playerList.stream().map(player -> player.getName()).collect(Collectors.joining(","))
						+ "에게 " + INITIAL_CARDS + "장의 카드를 나누었습니다.");
		System.out.println("딜러 : " + dealer.getCardInfo());
		for (Player player : playerList) {
			System.out.println(player.getName() + " : " + player.getCardInfo());
		}
	}

	/**
	 * 플레이어 한명씩 순서대로 게임진행
	 */
	private void playersTurn() {
		for (Player player : playerList) {
			eachPlayerTurn(player, player.getCardScore());
		}
	}

	/**
	 * 임의의 플레이어의 턴 
	 * 1) 블랙잭 케이스 확인 
	 * 2) 총 점수가 21에 가까워 질때까지 Hit 또는 Stay 가능
	 * 3) Bust 케이스 확인
	 * 
	 * @param player
	 */
	private void eachPlayerTurn(Player player, int playerScore) {
		if (playerScore == BLACKJACK_SCORE && playerScore != dealer.getCardScore()) {
			rewardCalculator.blackJackReward(player);
		}
		boolean hit = true;
		while (playerScore < BLACKJACK_SCORE && hit) {
			System.out.println("\n" + player.getName() + "의 현재 점수는 " + playerScore + "입니다.");
			hit = isChoiceHit(inputManager.chooseHitOrStay(), player);
			playerScore = player.getCardScore();
		}
		if (playerScore > BLACKJACK_SCORE) {
			rewardCalculator.playerBustReward(player);
		}
	}

	private boolean isChoiceHit(char choice, Player player) {
		if (choice == HIT | choice == Character.toUpperCase(HIT)) {
			player.addCard(cardShoe.getOneCard());
			System.out.println(player.getName() + " : " + player.getCardInfo());
			return true;
		}
		return false;
	}

	/**
	 * 딜러의 턴 총 점수가 16이하일 경우 17이상이 될 때까지 카드 추가
	 */
	private void dealerTurn() {
		while (dealer.getCardScore() <= DEALER_BORDER_SCORE) {
			System.out.println("\n딜러는 " + DEALER_BORDER_SCORE + "이하라 한장의 카드를 더 받았습니다.");
			dealer.addCard(cardShoe.getOneCard());
		}
	}

	private void showGameScore() {
		System.out.println("\n딜러 : " + dealer.getFinalCardInfo());
		for (Player player : playerList) {
			System.out.println(player.getName() + " : " + player.getFinalCardInfo());
		}
	}

	private void showGameReward() {
		System.out.println("\n## 최종 수익");
		rewardCalculator.calculateRewards(playerList, dealer.getCardScore());
		System.out.println(rewardCalculator.showRewardInfo());
	}

}
