package domain.user;

import java.util.*;

public class PlayerInputData {
    private static final String SEPARATOR = ",";
    private static boolean doesNeedInput;
    private static String userInput;

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

    private static String getUserInput(String askMessage) {
        System.out.println(askMessage);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println(userInput);
        return userInput;
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

}
