package domain.controller;

import java.io.IOException;
import java.util.List;

import model.GameModel;
import view.InputView;

public class GameController {
    public static void playBlackjack() throws IOException {
        InputView inputView = new InputView();
        List<String> userNames = inputView.inputUserNames();
        List<Double> userBettingMoneys = inputView.inputBettingMoneys(userNames);
        GameModel gameModel = new GameModel(userNames, userBettingMoneys);
        gameModel.play();
    }
}
