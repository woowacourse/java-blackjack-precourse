package domain.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.user.*;


public class Input {
    private static Scanner s = new Scanner(System.in);

    public static List<Player> setPlayerList() {
        List<Player> user = new ArrayList<>();

        String[] playerNameList = inputPlayerName();
        double[] playerBettingMoneyList = inputPlayerBettingMoney(playerNameList);

        for (int i = 0; i < playerNameList.length; i++) {
            user.add(new Player(playerNameList[i], playerBettingMoneyList[i]));
        }

        return user;
    }

    private static String[] inputPlayerName() {
        System.out.println("게임의 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        return s.nextLine().split(",");
    }

    private static double[] inputPlayerBettingMoney(String[] playerNameList) {
        double[] playerBettingMoneyList = new double[playerNameList.length];

        for (int i = 0; i < playerNameList.length; i++) {
            System.out.println(playerNameList[i] + "의 베팅 금액은?");
            playerBettingMoneyList[i] = s.nextDouble();
        }

        return playerBettingMoneyList;
    }
}