import java.util.*;
import domain.user.User;

public class BlackJackGame {
    private List<User> users = new ArrayList<User>();
    Scanner scanner = new Scanner(System.in);
    PlayerNamingChecker playerNamingChecker = new PlayerNamingChecker();

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.playGame();
    }

    private void playGame() {

    }

    private void startGame() {
        Map<String, Integer> playerInfo = new HashMap<String, Integer>();
        playerInfo = playerNaming();
    }

    private void proceedGame() {

    }

    private void endGame() {

    }

    private Map<String, Integer> playerNaming() {
        Map<String, Integer> players = new HashMap<String, Integer>();
        String playerNames = "";
        do {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리, 중복은 권장하지 않습니다.)");
            playerNames = scanner.nextLine().trim();
        } while (!playerNamingChecker.playerNamingCheck(playerNames));
        for (String player : playerNames.split(",")) {
            players.put(player.trim(), 0);
        }
        return players;
    }
}
