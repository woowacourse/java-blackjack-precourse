package domain.blackjack;

import domain.user.BetProgression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserIO {
    private static Scanner sc;

    public UserIO() {
        this.sc = new Scanner(System.in);
    }

    public List<String> getName() {
        List<String> nameList;
        do {
            System.out.print("게임에 참여할 사람의 이름을 입력하세요. (이름의 최대 길이는 " +
                    "5자이며, +언더바로 시작할 수 없습니다. " +
                    "각 이름은 쉼표(,) 기준으로 구분합니다.)\n>>");
            String input = sc.nextLine();

            nameList = splitLine(input);    //쉼표 기준 파싱
            trimName(nameList);             //공백 제거

        } while (!validateName(nameList));
        return nameList;
    }

    private List<String> splitLine(String input) {
        final String delimiter = ",";   //  쉼표 기준으로 파싱
        return Arrays.asList(input.split(delimiter));
    }

    private void trimName(List<String> nameList) {
        for (String it : nameList)
            it = it.trim();
    }

    private boolean validateName(List<String> nameList) {
        final int maxPlayer = 25;
        List<String> errorList = new ArrayList<>();
        if(nameList.size() > maxPlayer)
            return false;

        for (String it : nameList)
            checkName(errorList, it);

        if (errorList.isEmpty()) {
            return true;
        }

        printError(errorList);
        return false;
    }

    private void checkName(List<String> errorList, String name) {
        final int maxLength = 5;
        final String underBar = "_";
        if (name.length() > maxLength) return;
        if (name.startsWith(underBar)) return;
        errorList.add(name);
    }

    private void printError(List<String> errorList) {
        System.out.print("다음 이름이 입력 조건을 만족하지 않습니다. ");
        System.out.print(String.join(", ", errorList));
        System.out.println();
    }

    public int getBetAmount(String name) {
        int betAmount = 0;
        do {
            System.out.print(name + "의 베팅 금액을 입력하세요.\n>>");
        } while ((betAmount = validateBetAmount()) < 0);
        return betAmount;
    }

    private int validateBetAmount() {
        String input = sc.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("베팅 금액은 1 이상 정수만 입력하세요.");
            return -1;
        }
    }

    public boolean getBetProgression(String name) {
        String upperInput;
        BetProgression[] betProgression = BetProgression.values();
        while (true) {
            System.out.print(name + "은(는) 한장의 카드를 더 받겠습니까? (Y/N)\n>>");
            String input = sc.nextLine();
            upperInput = input.toUpperCase();
            for (BetProgression intent : betProgression)
                if (upperInput.equals(intent))
                    return intent.getIntention();
            System.out.println("의사는 Yes, Y, No, N 형태로 입력해주세요.");
        }
    }


}
