package woowacourse.blackjack;

import domain.user.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Input {
    private final Scanner sc = new Scanner(System.in);
    private final Output output = new Output();

    public List<String> getPlayerNames() {
        this.output.printNamesInput();
        List<String> playerNames = Arrays.asList(sc.nextLine().split(","));
        while (!this.isPlayerNamesValidator(playerNames)) {
            this.output.printNamesInput();
            playerNames = Arrays.asList(sc.nextLine().split(","));
        }
        return playerNames;
    }

    private boolean isPlayerNamesValidator(List<String> playerNames) {
        long count = playerNames.stream().filter(x -> x.matches("^[a-zA-Z0-9_-]+$")).count();
        if (playerNames.size() == count) {
            return this.isNameLengthValidator(playerNames);
        }
        this.output.printNamesInputAgain();
        return false;
    }

    private boolean isNameLengthValidator(List<String> playerNames) {
        long count = playerNames.stream().filter(x -> x.length() > 16).count();
        if (count == 0) {
            return this.isNameListLengthValidator(playerNames);
        }
        this.output.printNamesLengthInputAgain();
        return false;
    }

    private boolean isNameListLengthValidator(List<String> playerNames) {
        if (playerNames.size() < 9) {
            return true;
        }
        this.output.printNameListLengthInputAgain();
        return false;
    }

    public double getPlayerBettingMoney(String playerName) {
        this.output.printBettingMoneyInput(playerName);
        String bettingMoney = sc.nextLine();
        while (!isPlayerBettingMoneyValidator(bettingMoney) || !(Double.parseDouble(bettingMoney) >= 1)) {
            this.output.printBettingMoneyInputAgain();
            this.output.printBettingMoneyInput(playerName);
            bettingMoney = sc.nextLine();
        }
        return Double.parseDouble(bettingMoney);
    }

    private boolean isPlayerBettingMoneyValidator(String bettingMoney) {
        try {
            Double.parseDouble(bettingMoney);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isYesOrNo(Player player) {
        this.output.printYesOrNo(player);
        String answer = sc.nextLine();
        while (!this.isYesOrNoValidator(answer)) {
            this.output.printYesOrNoAgain();
            this.output.printYesOrNo(player);
            answer = sc.nextLine();
        }
        return answer.equals("Y") || answer.equals("y");
    }

    private boolean isYesOrNoValidator(String answer) {
        return answer.equals("y") || answer.equals("n")
                || answer.equals("Y") || answer.equals("N");
    }
}
