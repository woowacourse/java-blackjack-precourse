package woowacourse.blackjack;

import domain.user.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Input {
    private Scanner sc = new Scanner(System.in);
    private Output output = new Output();

    public List<String> getPlayerNames() {
        this.output.printNamesInput();
        return Arrays.asList(sc.nextLine().split(","));
    }

    public double getPlayerBettingMoney(String playerName) {
        this.output.printBettingMoneyInput(playerName);
        double bettingMoney = sc.nextDouble();
        sc.nextLine();
        return bettingMoney;
    }

    public String getYesOrNo(Player player) {
        this.output.printYesOrNo(player);
        return sc.nextLine();
    }
}
