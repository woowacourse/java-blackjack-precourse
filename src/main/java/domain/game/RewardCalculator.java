package domain.game;

import java.util.HashMap;
import java.util.List;

import domain.user.Player;

/**
 * 딜러와 플레이어의 수익 계산을 담당하는 객체
 */
public class RewardCalculator {
	private static final double DEFAULT_REWARD = 0.0;
	private static final double LOSE_RATIO = -1.0;
	private static final double BLACKJACK_RATIO = 1.5;
	private static final int BLACKJACK_SCORE = 21;

	/** 플레이어의 수익을 저장 */
	private HashMap<String, Double> playerRewardMap = new HashMap<>();
	private double dealerReward;

	/**
	 * 수익 계산기 생성시 playerRewardMap 초기화
	 */
	public RewardCalculator(List<Player> playerList) {
		for (Player player : playerList) {
			playerRewardMap.put(player.getName(), DEFAULT_REWARD);
		}
	}

	/**
	 * 블랙잭의 경우 수익률 계산
	 * @param player
	 */
	public void blackJackReward(Player player) {
		playerRewardMap.put(player.getName(), player.getBettingMoney() * BLACKJACK_RATIO);
		dealerReward -= player.getBettingMoney() * BLACKJACK_RATIO;
	}

	/**
	 * 플레이어 버스트 될 경우 수익률 계산
	 * @param player
	 */
	public void playerBustReward(Player player) {
		playerRewardMap.put(player.getName(), player.getBettingMoney() * LOSE_RATIO);
		dealerReward += player.getBettingMoney();
	}

	/**
	 * 딜러가 버스트인 경우 남아있는 플레이어 수익률 계산
	 * @param playerList
	 */
	private void dealerBustReward(List<Player> playerList) {
		for (Player player : playerList) {
			if (playerRewardMap.get(player.getName()) == DEFAULT_REWARD) {
				playerRewardMap.put(player.getName(), player.getBettingMoney());
				dealerReward -= player.getBettingMoney();
			}
		}
	}

	/**
	 * 플레이어 이겼을 경우 수익률 계산
	 * @param player
	 * @param playerScore
	 */
	private void playerWinReward(Player player, int playerScore) {
		if (playerScore < BLACKJACK_SCORE) {
			playerRewardMap.put(player.getName(), player.getBettingMoney());
			dealerReward -= player.getBettingMoney();
		}
	}

	/**
	 * 플레이어 졌을 경우 수익률 계산
	 * @param player
	 * @param playerScore
	 * @param dealerScore
	 */
	private void playerLoseReward(Player player, int playerScore, int dealerScore) {
		if (playerScore != dealerScore) {
			playerRewardMap.put(player.getName(), player.getBettingMoney() * LOSE_RATIO);
			dealerReward += player.getBettingMoney();
		}
	}

	public void calculateRewards(List<Player> playerList, int dealerScore) {
		if (dealerScore > BLACKJACK_SCORE) {
			dealerBustReward(playerList);
			return;
		}
		for (Player player : playerList) {
			int playerScore = player.getPlayerScore();
			if (dealerScore < playerScore) {
				playerWinReward(player, playerScore);
				continue;
			}
			playerLoseReward(player, playerScore, dealerScore);
		}
	}

	public void showRewardInfo() {
		System.out.println("딜러 : " + dealerReward);
		for (String key : playerRewardMap.keySet()) {
			System.out.println(key + " : " + playerRewardMap.get(key));
		}
	}
}
