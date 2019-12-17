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
        String bettingMoney = sc.nextLine();
        while (!isGetPlayerBettingMoneyValidator(bettingMoney) || !(Double.parseDouble(bettingMoney) >= 1)) {
            this.output.printBettingMoneyInputAgain();
            this.output.printBettingMoneyInput(playerName);
            bettingMoney = sc.nextLine();
        }
        return Double.parseDouble(bettingMoney);
    }

    private boolean isGetPlayerBettingMoneyValidator(String bettingMoney) {
        try {
            Double.parseDouble(bettingMoney);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getYesOrNo(Player player) {
        this.output.printYesOrNo(player);
        String answer = sc.nextLine();
        while (!this.isGetYesOrNoValidator(answer)) {
            this.output.printYesOrNoAgain();
            this.output.printYesOrNo(player);
            answer = sc.nextLine();
        }
        return answer;
    }

    private boolean isGetYesOrNoValidator(String answer) {
        return answer.equals("y") || answer.equals("n")
                || answer.equals("Y") || answer.equals("N");
    }
}
