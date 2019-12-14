package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";
    private static final String BLANK = "";

    private static Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세. (쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();
        List<String> names = Arrays.stream(input.split(DELIMITER))
                .collect(Collectors.toList());

        validateInput(names);

        return names;
    }

    private static void validateInput(List<String> names) {
        boolean isInvalid = names.stream()
                .anyMatch(InputView::isBlank);
        if (names.isEmpty() || isInvalid) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        }
    }

    private static boolean isBlank(String name) {
        return name.trim().equals(BLANK);
    }

    public static List<PlayerDto> inputBettingMoney(List<String> names) {
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (String name : names) {
            System.out.println(name + "의 베팅 금액은?");
            Double money = Double.parseDouble(SCANNER.nextLine());
            PlayerDto playerDto = new PlayerDto(name, money);
            playerDtos.add(playerDto);
        }

        return playerDtos;
    }
}
