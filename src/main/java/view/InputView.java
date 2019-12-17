package view;

import java.util.Scanner;

public class InputView {
    private static final String MSG_REQUEST_ENTRY = "게임에 참여할 사람의 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String MSG_REQUEST_MONEY = "의 배팅 금액은?";
    private static final String MSG_MONEY_ERROR = "1 이상의 수가 아닙니다. 다시 입력해주세요.";
    private static final String MSG_REQUEST_CARD = "는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String MSG_REQUEST_ERROR = "y 또는 n을 입력해주세요.";
    private final Scanner scanner = new Scanner(System.in);

    public static void printNumberError() {
        System.out.println(MSG_MONEY_ERROR);
    }

    public static void printChoiceError() {
        System.out.println(MSG_REQUEST_ERROR);
    }

    public String inputEntry() {
        System.out.println(MSG_REQUEST_ENTRY);
        return scanner.nextLine();
    }

    public String inputMoney(String name) {
        System.out.println(name + MSG_REQUEST_MONEY);
        return scanner.nextLine();
    }

    public String inputMoreCard(String name) {
        System.out.println(name + MSG_REQUEST_CARD);
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
