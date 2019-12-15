package domain.output;

import domain.game.Calculator;
import domain.user.Dealer;
import domain.user.Player;

import java.util.Iterator;
import java.util.List;

/**
 * Output
 *
 * @author hyochan
 * @version 0.0.1
 * @since 2019-12-14
 */

public class Output {
    private Calculator calculator = new Calculator();

    public void printCards(List<Player> players, Dealer dealer, boolean isLast) {
        printCards(dealer, isLast);
        Iterator itr = players.iterator();
        while (itr.hasNext()) {
            Player player = (Player) itr.next();
            printCards(player, isLast);
        }
    }

    public void printCards(Player player, boolean isLast) {
        System.out.print(player.getName() + "카드:" + player.showCards());
        if (isLast) {
            System.out.print(" - 결과: " + player.getCardScore());
        }
        System.out.println();
    }

    public void printCards(Dealer dealer, boolean isLast) {
        if (!isLast) {
            System.out.println("딜러 카드:" + (dealer.showCard(dealer.getCards().get(0))));
            return;
        }
        System.out.println("\n딜러 카드:" + dealer.showCards() + " - 결과: " + dealer.getCardScore());
    }

    public void printResult(List<Player> players, Dealer dealer) {
        double dealerProfit = 0;
        System.out.println("\n##최종 수익");
        Iterator itr = players.iterator();
        while (itr.hasNext()) {
            Player player = (Player) itr.next();
            double playerProfit = calculator.calculateBlackJack(dealer, player);
            System.out.println(player.getName() + ": " + playerProfit);
            dealerProfit -= playerProfit;
        }
        System.out.println("딜러: " + dealerProfit);
    }

    public void printFirstInputFinish(List<Player> players) {
        String printString = "딜러";
        Iterator itr = players.iterator();
        while (itr.hasNext()) {
            Player player = (Player) itr.next();
            printString += ", " + player.getName();
        }
        printString += "에게 2장의 카드를 나누었습니다.";
        System.out.println(printString);
    }
}
