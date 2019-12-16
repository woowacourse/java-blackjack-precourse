package domain;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GameProcess {
    private Scanner sc = new Scanner(System.in);
    private String[] playerName;
    private int[] betting;
    private int playerNumber;
    private final int ZERO_BETTING = 0;
    private List<Card> cards = new ArrayList<>();

    public void Game() {
        inputName();
        inputBetting();
    }

    private void inputName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요." +
                "(쉼표 기준으로 분리)");

        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            inputName();
            return;
        }
        playerName = input.split(",");
        playerNumber = playerName.length;
    }

    private void inputBetting() {
        betting = new int[playerNumber];
        for (int i = 0; i < playerName.length; i++) {
            while (betting[i] <= ZERO_BETTING) {
                System.out.println(playerName[i] + "의 배팅 금액은? (금액은 0원 이상이어야 합니다.)");
                betting[i] = sc.nextInt();
            }
        }
    }
}
