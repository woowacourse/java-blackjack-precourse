package domain.controller;

import java.io.IOException;
import java.util.List;

import model.GameModel;
import view.InputView;

/**
 * @author KIMSIYOUNG
 * @apiNote 게임진행을 위한 Controller 로, 사용자 이름과 배팅머니를 입력받아, 게임모델에게 게임 진행을 요청합니다.
 * @since 2019-12-13
 */
public class GameController {
    public static void playBlackjack() throws IOException {
        InputView inputView = new InputView();
        List<String> userNames = inputView.inputUserNames();
        List<Double> userBettingMoneys = inputView.inputBettingMoneys(userNames);
        GameModel gameModel = new GameModel(userNames, userBettingMoneys);
        gameModel.play();
    }
}
