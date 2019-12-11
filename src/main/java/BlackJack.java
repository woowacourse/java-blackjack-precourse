import utils.InputHandler;
import view.InputView;

public class BlackJack {
    void play() {
        for(String playerName : InputHandler.splitByComma(InputView.playerNames())) {
            int bettingMoney = InputView.bettingMoney(playerName);
        }
    }
}
