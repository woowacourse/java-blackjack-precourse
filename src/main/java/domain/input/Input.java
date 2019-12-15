package domain.input;

import domain.user.Player;

import java.util.*;

/**
 * Input
 *
 * @author hyochan
 * @version 0.0.1
 * @since 2019-12-14
 */

public class Input {
    public static Scanner scan = new Scanner(System.in);
    public Validator validator = new Validator();

    public List<Player> inputPlayerNames() {
        List<String> playerNames;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리, 공백 없이, 8명 이내)");
            playerNames = splitNames(scan.nextLine());
        } while (!validator.isValidName(playerNames));
        return inputPlayerBettingAmount(playerNames);
    }

    public Player inputPlayerBettingAmount(String playerName) {
        int bettingAmount;
        do {
            System.out.println(playerName + "의 배팅 금액은?");
            bettingAmount = scan.nextInt();
        } while (!validator.isValidBettingAmount(bettingAmount));
        return new Player(playerName, bettingAmount);
    }

    public List<Player> inputPlayerBettingAmount(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        Iterator itr = playerNames.iterator();
        while (itr.hasNext()) {
            String playerName = (String) itr.next();
            players.add(inputPlayerBettingAmount(playerName));
        }
        return players;
    }

    public List<String> splitNames(String input) {
        List<String> Names = new ArrayList<>();
        Collections.addAll(Names, input.split(","));
        return Names;
    }

    public boolean askOneMoreCard(Player player) {
        String input;
        if (!player.isUnderMaxScore()) {
            return false;
        }
        do {
            System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            input = scan.next();
        } while (!validator.isValidYesNoInput(input));
        return convertTrueFalse(input);
    }

    public boolean convertTrueFalse(String input) {
        if (input.equals("y")) {
            return true;
        }
        return false;
    }
}

