package gameRunner;

import util.StringParsing;
import view.InputView;

import java.util.List;

public class GameMachine {
    private InputView inputView;

    public GameMachine() {
        this.inputView = new InputView(System.in);
    }

    public void runBlackJackGame() {
        List<String> names = StringParsing.makeStringListFromString(inputView.getPlayerNames());
        List<Double> bettingMoneys = inputView.getBettingMoneys(names);

        BlackJackGame blackJackGame = new BlackJackGame(names, bettingMoneys);
        blackJackGame.giveInitCard();
        blackJackGame.giveMoreCardToGamers(inputView);
    }


}
