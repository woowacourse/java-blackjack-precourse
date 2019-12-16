package domain.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import domain.user.*;


public class Input {
    private static Scanner s = new Scanner(System.in);

    public static List<Player> setPlayerList() {
        List<Player> user = new ArrayList<>();
        List<String> playerNameList = inputPlayerName();
        List<Double> playerBettingMoneyList = inputPlayerBettingMoney(playerNameList);

        for (int i = 0; i < playerNameList.size(); i++) {
            user.add(new Player(playerNameList.get(i), playerBettingMoneyList.get(i)));
        }

        return user;
    }

    private static List<String> inputPlayerName() {
        System.out.println("게임의 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> playerNameList = Arrays.asList(s.nextLine().split(","));

        if(execptionPlayerNameOverlap(playerNameList)) {
            System.out.println("중복되는 이름이 있습니다. 처음부터 다시 입력해주세요.");
            return inputPlayerName();
        }

        return playerNameList;
    }

    private static boolean execptionPlayerNameOverlap(List<String> playerNameList) {
        return playerNameList.size() != playerNameList.stream().distinct().count();
    }

    private static List<Double> inputPlayerBettingMoney(List<String> playerNameList) {
        List<Double> playerBettingMoneyList = new ArrayList<>();

        for (String strings : playerNameList) {
            System.out.println(strings + "의 베팅 금액은?");
            playerBettingMoneyList.add(execptionBettingMoneyNegative(s.nextDouble()));
        }

        return playerBettingMoneyList;
    }

    private static double execptionBettingMoneyNegative(double bettingMoney) {
        if (bettingMoney < 0) {
            System.out.println("베팅 금액은 음수가 될 수 없습니다. 다시 입력하세요.");
            bettingMoney = s.nextDouble();
        }
        return bettingMoney;
    }
}