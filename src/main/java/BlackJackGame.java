import java.util.*;
import domain.user.User;

public class BlackJackGame {
    private List<User> users = new ArrayList<User>();
    Map<String, Integer> playerInfo = new HashMap<String, Integer>();
    Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.playGame();
    }

    private void playGame() {

    }

    private void startGame() {
        playerNaming();
        for (String playerName : playerInfo.keySet()) {
            playerInfo.put(playerName, playerSetBettingMoney(playerName));
        }
    }

    private void proceedGame() {

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
}
