package io;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static io.BlackJackGame.ZERO;

public class InputView {

    public static final String DELIMITER = ",";
    public static final int ONE = 1;

    private static final String HIT = "y";
    private static final String STAY = "n";
    private static final String DOUBLE_COMMA = ",,";

    private static Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String inputLine = SCANNER.nextLine();
        validateNames(inputLine);
        return Arrays.stream(inputLine.split(DELIMITER))
                .collect(Collectors.toList());
    }

    private static void validateNames( String inputLine ) {
        if (inputLine.contains(DOUBLE_COMMA) || inputLine.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("이름이 정확하게 입력되지 않았습니다");
        }
    }

    public static Double inputBettingMoney( String name ) {
        System.out.println(name + "의 베팅 금액은?");
        try {
            Double bettingMoney = Double.parseDouble(SCANNER.nextLine());
            validateBettingMoney(bettingMoney);
            return bettingMoney;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }

    private static void validateBettingMoney( Double bettingMoney ) {
        if (bettingMoney <= ZERO) {
            throw new IllegalArgumentException("베팅 금액은 0 보다 큰 금액 입니다.");
        }
    }

    public static boolean inputHitAndStay( String name ) {
        System.out.println(name + "는(은) 카드 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String inputLine = SCANNER.nextLine();
        validateHitAndStay(inputLine);
        return inputLine.equals(HIT);
    }

    private static void validateHitAndStay( String inputLine ) {
        if (inputLine.length() > ONE || !(inputLine.contains(HIT) || inputLine.contains(STAY))) {
            throw new IllegalArgumentException("카드 추가 입력이 올바르지 않습니다.");
        }
    }
}

