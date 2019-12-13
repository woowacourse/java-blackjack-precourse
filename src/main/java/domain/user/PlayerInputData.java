package domain.user;

import java.util.*;

public class PlayerInputData {
    private static final String SEPARATOR = ",";
    private static final int NO_MONEY = 0;
    private static List<String> nameList;
    private static boolean doesNeedInput;
    private static String userInput;

    public List<Player> getPlayerList() {
        List<Player> playerList = new ArrayList<>();
        nameList = getNameList();
        List<Double> bettingMoneyList = new ArrayList<>();
        for (String name : nameList) {
            bettingMoneyList.add(getBettingMoney(name));
        }
        for (int i=0; i<nameList.size(); i++) {
            playerList.add(new Player(nameList.get(i), bettingMoneyList.get(i)));
        }
        return playerList;
    }

    public List<String> getPlayerNameList() {
        return nameList;
    }

    private static String getUserInput(String askMessage) {
        System.out.println(askMessage);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println(userInput+"\n");
        return userInput;
    }

    private static List<String> getNameList() {
        List<String> nameList = new ArrayList<>();
        doesNeedInput = true;
        while (doesNeedInput) {
            userInput = getUserInput("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            String[] names = userInput.split(SEPARATOR);
            nameList = getTrimmedNameList(names);
            setDoesNeedInputForName(nameList);
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

    private static void setDoesNeedInputForName(List<String> nameList) {
        if (haveBlankName(nameList)) {
            doesNeedInput = true;
            System.out.println("※ 공백으로만 구성된 이름은 사용할 수 없습니다.");
            return;
        }
        if (haveDuplicatedName(nameList)) {
            doesNeedInput = true;
            System.out.println("※ 중복되는 이름은 사용할 수 없습니다.");
            return;
        }
        doesNeedInput = false;
    }

    private static boolean haveBlankName(List<String> nameList) {
        return nameList.contains("");
    }

    private static boolean haveDuplicatedName(List<String> nameList) {
        Set<String> nameSet = new HashSet<>(nameList);
        return nameList.size() != nameSet.size();
    }

    private static double getBettingMoney(String name) {
        doesNeedInput = true;
        while (doesNeedInput) {
            userInput = getUserInput(String.format("%s의 베팅 금액은?", name));
            setDoesNeedInputForMoney(userInput);
        }
        return Double.parseDouble(userInput);
    }

    private static void setDoesNeedInputForMoney(String userInput) {
        if (isNoMoney(userInput)) {
            doesNeedInput = true;
            System.out.println("※ 베팅 금액은 0을 초과하는 숫자이어야 합니다.");
            return;
        }
        doesNeedInput = false;
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

}
