import java.util.*;

import domain.user.User;

public class BlackJackGame {
    private List<User> users = new ArrayList<User>();
    private Map<String, Integer> playerInfo = new HashMap<String, Integer>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.playGame();
    }

    private void playGame() {
        boolean loop = false;
        startGame();
        firstProceedGame();
        secondProceedGame();
        do {
            loop = !proceedGame();
        } while (loop);
        endGame();
    }

    private void startGame() {
        playerNaming();
        for (String playerName : playerInfo.keySet()) {
            playerInfo.put(playerName, playerSetBettingMoney(playerName));
        }
    }

    private void firstProceedGame() {
        System.out.println("딜러와 " + getPlayerNamesALine() + "에게 2장의 카드를 나누었습니다.");

    }

    private void secondProceedGame() {

    }

    private boolean proceedGame() {
        return true;
    }

    private void endGame() {

    }

    private void playerNaming() {
        String playerNames = "";
        PlayerNamingChecker playerNamingChecker = new PlayerNamingChecker();
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리, 중복은 권장하지 않습니다.)");
            playerNames = scanner.nextLine().trim();
        } while (!playerNamingChecker.playerNamingCheck(playerNames));
        for (String player : playerNames.split(",")) {
            playerInfo.put(player.trim(), 0);
        }
    }

    private int playerSetBettingMoney(String playerName) {
        String bettingMoney = "";
        PlayerBettingMoneyChecker playerBettingMoneyChecker = new PlayerBettingMoneyChecker();
        do {
            System.out.println(playerName + "의 배팅 금액은?(10원단위로 쉼표없이 입력해주세요)");
            bettingMoney = scanner.nextLine().trim();
        } while (!playerBettingMoneyChecker.playerBettingMoneyCheck(bettingMoney));
        return Integer.parseInt(bettingMoney);
    }

    private String getPlayerNamesALine() {
        StringBuilder playerNamesALineSB = new StringBuilder();
        for (String playerName : playerInfo.keySet()) {
            playerNamesALineSB.append(playerName);
            playerNamesALineSB.append(", ");
        }
        String playerNamesALine = playerNamesALineSB.toString();
        return playerNamesALine.substring(0, playerNamesALine.length() - 2);
    }
}
