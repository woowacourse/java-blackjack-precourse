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
        endGame();
    }

    private void startGame() {
        playerNaming();
        for (String playerName : playerInfo.keySet()) {
            playerInfo.put(playerName, playerSetBettingMoney(playerName));
        }
        firstProceedGame();
    }

    private void firstProceedGame() {
        boolean proceed = false;
        System.out.println("딜러와 " + getPlayerNamesALine() + "에게 2장의 카드를 나누었습니다.");

        if (proceed == true) {
            secondProceedGame();
        }
    }

    private void secondProceedGame() {
        boolean proceed = false;
        System.out.println("는/(은) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        if (true) {
            System.out.println("딜러는 16이하라 카드 한 장을 더 받았습니다.");
        }
        if (proceed == true) {
            proceedGame();
        }
    }

    private void proceedGame() {
        boolean proceed = false;
        System.out.println("" + "는/(은) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        if (proceed == true) {
            proceedGame();
        }
    }

    private void endGame() {
        System.out.println("$이름" + "카드 : " + "$카드값");
        System.out.println("## 최종수익");
        System.out.println("$이름" + " : " + "$값");
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
            System.out.println(playerName + "의 배팅 금액은? (10원 단위로 쉼표없이 입력해주세요, 10억 미만으로만 가능합니다.)");
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
