package domain.user;

import domain.function.CardDistributor;
import domain.function.Viewer;

import java.util.*;

import static domain.user.BlackjackUserResult.PERFECT_SCORE;

public class PlayerInputData {
    private static final String SEPARATOR = ",";
    private static final int NO_MONEY = 0;

    private PlayerInputData() {
    }

    public static List<Player> getPlayerList() {
        List<Player> playerList = new ArrayList<>();
        for (String name : getNameList()) {
            playerList.add(new Player(name, getBettingMoney(name)));
        }
        return playerList;
    }

    private static String getUserInput(String askMessage) {
        System.out.println(askMessage);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println();
        return userInput;
    }

    private static List<String> getNameList() {
        List<String> nameList = null;
        boolean needInput = true;
        while (needInput) {
            String userInput = getUserInput("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            String[] names = userInput.split(SEPARATOR);
            nameList = getTrimmedNameList(names);
            needInput = isUnusableName(nameList);
        }
        return nameList;
    }

    private static List<String> getTrimmedNameList(String[] names) {
        List<String> trimmedNameList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            trimmedNameList.add(i, names[i].trim());
        }
        return trimmedNameList;
    }

    private static boolean isUnusableName(List<String> nameList) {
        if (haveBlankName(nameList)) {
            System.out.println("※ 공백으로만 구성된 이름은 사용할 수 없습니다.");
            return true;
        }
        if (haveDuplicatedName(nameList)) {
            System.out.println("※ 중복되는 이름은 사용할 수 없습니다.");
            return true;
        }
        return false;
    }

    private static boolean haveBlankName(List<String> nameList) {
        return nameList.contains("");
    }

    private static boolean haveDuplicatedName(List<String> nameList) {
        Set<String> nameSet = new HashSet<>(nameList);
        return nameList.size() != nameSet.size();
    }

    private static double getBettingMoney(String name) {
        boolean needInput = true;
        String userInput = "";
        while (needInput) {
            userInput = getUserInput(String.format("%s의 베팅 금액은?", name));
            needInput = isUnusableMoney(userInput);
        }
        return Double.parseDouble(userInput);
    }

    private static boolean isUnusableMoney(String userInput) {
        if (userInput.equals("")) {
            System.out.println("※ 베팅 금액은 반드시 입력해야 합니다.");
            return true;
        }
        if (isNoMoney(userInput)) {
            System.out.println("※ 베팅 금액은 0을 초과하는 숫자이어야 합니다.");
            return true;
        }
        return false;
    }

    private static boolean isNoMoney(String input) {
        if (isNotNumber(input)) {
            return true;
        }
        return Double.parseDouble(input) <= NO_MONEY;
    }

    private static boolean isNotNumber(String input) {
        Set<Boolean> isNotNumberCharacterAnswer = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            char inputCharacter = input.charAt(i);
            isNotNumberCharacterAnswer.add(isNotNumberCharacter(inputCharacter));
        }
        return isNotNumberCharacterAnswer.contains(true);
    }

    private static boolean isNotNumberCharacter(char inputCharacter) {
        return inputCharacter < '0' || '9' < inputCharacter;
    }

    public static void askCardAddition(Player player, CardDistributor cardDistributor) {
        boolean needToAsk = true;
        while (needToAsk) {
            System.out.println(String.format("%s, 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            needToAsk = needMoreCard(answer, player, cardDistributor) && isUnderPerfectScore(player);
            System.out.println();
        }
        System.out.println();
    }

    private static boolean needMoreCard(String answer, Player player, CardDistributor cardDistributor) {
        if (answer.equals("y")) {
            cardDistributor.giveCardToBlackjackUser(player);
            Viewer.printAllCards(player);
            return true;
        } else if (answer.equals("n")) {
            Viewer.printAllCards(player);
            return false;
        }
        System.out.println("※ y와 n만 입력할 수 있습니다.");
        return true;
    }

    private static boolean isUnderPerfectScore(Player player) {
        BlackjackUserResult playerResult = player.createBlackjackUserResult();
        if (playerResult.isPerfectScore()) {
            System.out.print(String.format("\n※ 카드 숫자의 합이 %d이므로 더 이상 카드를 추가하지 않습니다.", PERFECT_SCORE));
            return false;
        }
        if (playerResult.isBust()) {
            System.out.print(String.format("\n※ 카드 숫자의 합이 %d을 넘었으므로 %s는 패배하였습니다.", PERFECT_SCORE, player.getName()));
            return false;
        }
        return true;
    }
}
