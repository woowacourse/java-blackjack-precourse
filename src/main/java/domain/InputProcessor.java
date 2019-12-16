package domain;

import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InputProcessor {

    static ArrayList<String> getPlayerName() {
        ArrayList<String> playerNamesArray;
        boolean isPlayerNameCorrect;
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. 이름은 쉼표(,) 기준으로 분리해주세요.");
            playerNamesArray = getPlayerNameInput();
            isPlayerNameCorrect = checkPlayerNameInput(playerNamesArray);
        } while (!isPlayerNameCorrect);
        return playerNamesArray;
    }

    static ArrayList<String> getPlayerNameInput() {
        Scanner playerNameInput = new Scanner(System.in);
        String playerNames = playerNameInput.next();

        ArrayList<String> playerNameInputArray = new ArrayList<String>();
        Collections.addAll(playerNameInputArray, playerNames.split(","));

        return playerNameInputArray;
    }

    static boolean checkPlayerNameInput(ArrayList<String> playerNameInputArray) {
        if (playerNameInputArray.size() < 1) {
            return false;
        }
        return true;
    }

    static Double getPlayerMoney(String playerName) {
        System.out.println(playerName + " 플레이어의 베팅 금액을 입력해주세요.");
        Scanner playerMoneyInput = new Scanner(System.in);
        Double playerMoney = playerMoneyInput.nextDouble();
        return playerMoney;
    }
}
