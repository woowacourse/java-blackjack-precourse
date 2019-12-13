package view;

import domain.user.Player;

import java.util.*;

public class Input {
    private static final String COMMA = ",";

    private Scanner scanner = new Scanner(System.in);

    public Input() {
    }

    public String input() {
        return scanner.nextLine();
    }

    public List<String> asGamers() {
        //TODO 사용자입력 Output 출력하기 코드
        return Optional.of(input())
                .filter(this::containsDoubleComma)
                .filter(this::endWithComma)
                .map(this::splitAsComma)
                .orElseGet(this::asGamers);
    }

    public double asBettingMoney() {
        //TODO 사용자이름과 배팅금액 Output 출력코드
        return Optional.of(input())
                .map(Double::parseDouble)
                .orElseGet(this::asBettingMoney);
    }

    public boolean containsDoubleComma(String input) {
        return input.contains(COMMA + COMMA);
    }

    public boolean endWithComma(String input) {
        return input.endsWith(COMMA);
    }

    public List<String> splitAsComma(String input) {
        return Arrays.asList(input.split(COMMA));
    }
}
