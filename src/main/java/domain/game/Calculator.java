package domain.game;

import domain.user.Dealer;
import domain.user.Player;

/**
 * Calculator
 *
 * @author hyochan
 * @version 0.0.1
 * @since 2019-12-14
 */

public class Calculator {
    public double calculateBlackJack(Dealer dealer, Player player) {
        if (!player.isBlackJack()) {
            return player.getBettingMoney() * calculateWin(dealer, player);
        }
        if (dealer.isBlackJack()) {
            return 0;
        }
        return player.getBettingMoney() * 1.5;
    }

    public double calculateWin(Dealer dealer, Player player) {
        if (player.isOverMaxScore()) {
            return -1;
        }
        if (dealer.isOverMaxScore()) {
            return 1;
        }
        if (player.getCardScore() < dealer.getCardScore()) {
            return -1;
        }
        if (player.getCardScore() > dealer.getCardScore()) {
            return 1;
        }
        return 0;
    }
}
