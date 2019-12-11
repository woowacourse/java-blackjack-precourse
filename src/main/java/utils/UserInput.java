package utils;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<Player> enrollPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리");
        return splitPlayersName(SCANNER.nextLine());
    }

    private static List<Player> splitPlayersName(String playerString) {
        List<Player> playerNameList = new ArrayList<>();
        Arrays.stream(playerString.trim().replace(" ", "").split(","))
                .forEach(x -> playerNameList.add(addPlayer(x)));
        return playerNameList;
    }

    private static Player addPlayer(String name) {
        System.out.println(name+"의 배팅 금액은?");
        return new Player(name, SCANNER.nextInt());
    }

    public static Dealer enrollDealer() {
        return null;
    }
}