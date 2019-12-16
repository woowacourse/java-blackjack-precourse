import java.util.List;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class ProfitCalculation {
	/*
	 * 수익 계산
	 */
    public static void countProfit(List<User> users, int step) {
    	for (int i = 1; i < users.size(); i++) {
        	countProfit((Dealer) users.get(0), (Player) users.get(i), step);
    	}
    }
    
    private static void countProfit(Dealer dealer, Player player, int step) {
    	if (step == 1) {
    		countProfitBlackJack(dealer, player);
    		return;
    	}
		countProfitTwentyOne(dealer, player);
    }
    
    private static void countProfitBlackJack(Dealer dealer, Player player) {
    	if (dealer.getSumNumber() == 21 && player.getSumNumber() != 21) {
    		dealerWinPlayerProfit(dealer, player);
    	} else if (dealer.getSumNumber() != 21 && player.getSumNumber() == 21) {
    		playerBlackJackProfit(dealer, player);
    	}
    }
    
    private static void countProfitTwentyOne(Dealer dealer, Player player) {
    	if (Math.abs(21 - dealer.getSumNumber()) < Math.abs(21 - player.getSumNumber())) {
    		dealerWinPlayerProfit(dealer, player);
    	} else if (Math.abs(21 - dealer.getSumNumber()) > Math.abs(21 - player.getSumNumber())) {
    		dealerLosePlayerProfit(dealer, player);
    	}
    }
    
    private static void dealerWinPlayerProfit(Dealer dealer, Player player) {
    	dealer.setProfit(player.getBettingMoney());
    	player.setProfit(-player.getBettingMoney());
    }
        
    private static void dealerLosePlayerProfit(Dealer dealer, Player player) {
    	dealer.setProfit(-player.getBettingMoney());
    	player.setProfit(player.getBettingMoney());
    }

    private static void playerBlackJackProfit(Dealer dealer, Player player) {
    	dealer.setProfit(-player.getBettingMoney() * 1.5);
    	player.setProfit(player.getBettingMoney() * 1.5);
    }
    
    /*
     * 유저 수익 출력
     */
    public static void usersTotalProfit(List<User> users) {
    	usersTotalProfit((Dealer) users.get(0));
    	for (int i = 1; i < users.size(); i++) {
        	usersTotalProfit((Player) users.get(i));
    	}
    }
    
    private static void usersTotalProfit(Dealer dealer) {
    	dealer.userProfit("딜러");
    }
    
    private static void usersTotalProfit(Player player) {
    	player.userProfit(player.getPlayerName());
    }
}
