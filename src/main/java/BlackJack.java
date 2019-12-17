import domain.manager.InputManager;
import domain.manager.PlayManager;

public class BlackJack {
    public static void main(String[] args) {
        PlayManager playManager = new PlayManager(InputManager.getPlayerNameList(), InputManager.getBettingMoneyList());
        playManager.playGame();
    }
}
