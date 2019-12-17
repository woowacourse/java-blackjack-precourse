package domain.Game;

import domain.user.Player;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName : Input
 * @ClassExplanation : 입력 UI 관련 클래스
 * @author : Kim SeYun
 * @Date : 2019. 12. 17
 * @Copyright (c) 2019 SeYun Kim
 */
public class Input {
    Scanner scanner = new Scanner(System.in);

    public String[] playerNameInput() {
        String playerNamesInput;
        String[] playerNames;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            System.out.println("입력 조건 : 1. 영대소문자만 가능 2. 공백 사용 불가");
            playerNamesInput = scanner.nextLine();
            playerNames = playerNamesInput.split(",");
        } while (playerNameInputConfirm(playerNames, playerNamesInput));
        return playerNames;
    }

    private boolean playerNameInputConfirm(String[] playerNames, String playerNamesInput) {
        if (playerNamesInput.endsWith(",")) {
            return true;
        }
        return !Arrays.stream(playerNames).allMatch(playerName -> playerName.matches("^[a-zA-Z]*$") &&
                !playerName.isEmpty());
    }

    public double bettingMoneyInput(String playerName) {
        String bettingMoneyInput;
        do {
            System.out.println(playerName + "의 배팅 금액은?");
            bettingMoneyInput = scanner.nextLine();
        } while (bettingMoneyInputConfirm(bettingMoneyInput));

        return Double.parseDouble(bettingMoneyInput);
    }

    private boolean bettingMoneyInputConfirm(String bettingMoneyInput) {
        try {
            Double.parseDouble(bettingMoneyInput);
        } catch (Exception e) {
            return true;
        }

        return !bettingMoneyInput.matches("^[0-9]*$");
    }

    public String addCardDrawInput(Player player) {
        String addCardAnwser;
        do {
            System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            addCardAnwser = scanner.nextLine();
        } while (addCardDrawInputCheck(addCardAnwser) && player.scoreExcess());
        return addCardAnwser;
    }

    public boolean addCardDrawInputCheck(String addCardAnswer){
        if(addCardAnswer.equals("y") || addCardAnswer.equals("n")){
            return false;
        }
        return true;
    }
}
