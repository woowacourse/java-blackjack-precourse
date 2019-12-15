package domain.game;

import domain.user.*;

import java.util.*;

public class BlackJack {
    private static final String PROMPT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PROMPT_BETTING_MONEY = "의 베팅 금액은?";

    public static void main(String[] args) {
        Players players = new Players(createPlayers(receiveInput(PROMPT_PLAYER_NAMES)));
        System.out.println(players);
    }

    private static List<Player> createPlayers(String playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName: split(playerNames)) {
            Positive bettingMoney = new Positive(receiveInput(playerName + PROMPT_BETTING_MONEY));
            players.add(new Player(playerName, bettingMoney.getNumber()));
        }
        return players;
    }

    private static String receiveInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private static String[] split(String text) {
        return text.split(",");
    }
}