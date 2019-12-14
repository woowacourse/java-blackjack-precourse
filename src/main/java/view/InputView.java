package view;

import view.dto.PlayerDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";
    private static final String BLANK = "";
    private static final String MORE_CARD = "y";
    private static final String NO_MORE_CARD = "n";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
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

    public static List<PlayerDTO> inputBettingMoney(List<String> names) {
        List<PlayerDTO> playerDTOs = new ArrayList<>();
        for (String name : names) {
            System.out.println(name + "의 베팅 금액은?");
            Double money = Double.parseDouble(SCANNER.nextLine());
            PlayerDTO playerDTO = new PlayerDTO(name, money);
            playerDTOs.add(playerDTO);
        }
        return playerDTOs;
    }

    public static boolean receiveCard(String name) {
        System.out.println(name + "은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String receiveCommand = SCANNER.nextLine();
        if (MORE_CARD.equals(receiveCommand)) {
            return true;
        }
        if (NO_MORE_CARD.equals(receiveCommand)) {
            return false;
        }
        return receiveCard(name);
    }
}
