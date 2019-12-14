package domain.game;

import java.util.HashMap;
import java.util.List;

import domain.user.Player;

public class RewardCalculator {
	private static final double DEFAULT_REWARD = 0.0;
	private static final double LOSE_RATIO = -1.0;
	private static final double BLACKJACK_RATIO = 1.5;
	private static final int BLACKJACK_SCORE = 21;
	
	private HashMap<String, Double> playerRewardMap = new HashMap<>();
	private double dealerReward;
	
	public RewardCalculator(String[] playerNames) {
		initializeRewards(playerNames);
	}
	
	private void initializeRewards(String[] playerNames) {
		for (String name : playerNames) {
			playerRewardMap.put(name, DEFAULT_REWARD);
		}
	}
	
	public void blackJackReward(Player player) {
		playerRewardMap.put(player.getName(), player.getBettingMoney() * BLACKJACK_RATIO);
		dealerReward -= player.getBettingMoney() * BLACKJACK_RATIO;
	}
	
	public void playerBustReward(Player player) {
		playerRewardMap.put(player.getName(), player.getBettingMoney() * LOSE_RATIO);
		dealerReward += player.getBettingMoney();
	}
	
	private void dealerBustReward(List<Player> playerList) {
		for (Player player : playerList) {
			if (playerRewardMap.get(player.getName()) == DEFAULT_REWARD) {
				playerRewardMap.put(player.getName(), player.getBettingMoney());
				dealerReward -= player.getBettingMoney();
			}
		}
	}
	
	public void playerWinReward(Player player, int playerScore) {
		if (playerScore < BLACKJACK_SCORE) {
			playerRewardMap.put(player.getName(), player.getBettingMoney());
			dealerReward -= player.getBettingMoney();
		}
	}
	
	public void playerLoseReward(Player player, int playerScore, int dealerScore) {
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
		System.out.println("µô·¯ : " + dealerReward);
		for (String key : playerRewardMap.keySet()) {
			System.out.println(key + " : " + playerRewardMap.get(key));
		}
	}
}
