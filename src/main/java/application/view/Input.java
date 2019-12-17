package application.view;

import application.domain.user.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    private static Scanner SC = new Scanner(System.in);
    private static Pattern FIVE_LENGTH_NAME_REGEX_PATTERN = Pattern.compile("^[a-zA-Z]{1,5}$");
    private static String INVALID_NAME_MESSAGE = "5자리수 이하의 영단어로 입력해 주세요.";
    private static String YES = "y";
    private static String NO = "n";


    public static String getNamesFromConsole() {
        try {
            return checkInputNamesAndReturn();
        } catch(IOException e) {
            getNamesFromConsole();
        }
        return "";
    }

    private static String checkInputNamesAndReturn() throws IOException {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String value = SC.nextLine();
        String[] nameArray = value.split(",");
        boolean res = Arrays.stream(nameArray)
                .allMatch(name -> isValidPattern(name, FIVE_LENGTH_NAME_REGEX_PATTERN));

        if (!res) {
            System.out.println(INVALID_NAME_MESSAGE);
            throw new IOException();
        }
        return value;
    }

    private static boolean isValidPattern(String name, Pattern pattern) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static double getBettingMoney(String name) {
        try {
            return checkInputBettingData(name);
        } catch(NumberFormatException e) {
            getBettingMoney(name);
        }
        return 0;
    }

    private static double checkInputBettingData(String name) throws NumberFormatException {
        System.out.println(name + "의 배팅 금액은 ? ");
        String value = SC.nextLine();
        return Double.parseDouble(value);
    }

    public static boolean isAddingCardFlag(Player player) {
        try {
            return checkInputFlag(player);
        } catch(IOException e) {
            isAddingCardFlag(player);
        }
        return false;
    }

    private static boolean checkInputFlag(Player player) throws IOException {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까? y/n");
        String value = SC.nextLine();
        if (!value.equals(YES) && !value.equals(NO)) {
            throw new IOException();
        }
        return value.equals(YES);
    }
}
