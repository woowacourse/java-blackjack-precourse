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
        splitPlayersName(SCANNER.nextLine());
        return null;
    }

    private static void splitPlayersName(String playerString) {
        List<String> list = new ArrayList<>();
        Arrays.stream(playerString.trim().replace(" ", "").split(",")).forEach(x -> list.add(x));
    }

    public static Dealer enrollDealer() {
        return null;
    }
}