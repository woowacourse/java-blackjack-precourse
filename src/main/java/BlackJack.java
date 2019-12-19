import domain.manager.PlayManager;
import domain.ui.input.PlayerNames;

public class BlackJack {
    public static void main(String[] args) {
        PlayManager playManager = new PlayManager(PlayerNames.input());
        playManager.playGame();
    }
}
