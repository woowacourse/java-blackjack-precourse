package ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import exception.InvalidInputException;

public class Input {
    private static final int MAX_PLAYER = 25;
    private Scanner scanner = new Scanner(System.in);

    public String[] getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        try {
            String answer = scanner.nextLine();
            validateNames(answer);
            String[] split = answer.split(",");
            validateNames(split);
            validateNumberOfPlayers(split);
            return split;
        } catch (Exception e) {
            System.out.println("에러: " + e.getMessage());
            return getNames();
        }
    }

    private void validateNames(String input) throws Exception {
        if (input.isEmpty() || input.endsWith(",") || input.contains(",,")) {
            throw new InvalidInputException("잘못된 형식의 입력입니다.");
        }
    }

    private void validateNames(String[] split) throws Exception {
        if (split.length != new HashSet<String>(Arrays.asList(split)).size()) {
            throw new InvalidInputException("이름은 중복될 수 없습니다.");
        }
        for (String name : split) {
            validateNames(name);
        }
    }

    private void validateNumberOfPlayers(String[] split) throws Exception {
        if (split.length > MAX_PLAYER) {
            throw new InvalidInputException("25명 이하의 참가자만 플레이 가능합니다.");
        }
    }

    public double getMoney(String name) {
        System.out.println("\n" + name + "의 배팅 금액은?");
        try {
            double answer = Double.parseDouble(scanner.nextLine());
            validatePositiveAmount(answer);
            return answer;
        } catch (Exception e) {
            System.out.println("에러: " + e.getMessage());
            return getMoney(name);
        }
    }

    private void validatePositiveAmount(double amount) throws Exception {
        if (amount < 0) {
            throw new InvalidInputException("잘못된 형식의 입력입니다.");
        }
    }

    public boolean wantsMoreCard(String name) {
        String answer;
        System.out.println("\n" + name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        do {
            answer = scanner.nextLine();
        } while (!answer.matches("[yn]"));
        return answer.equals("y");
    }
}
