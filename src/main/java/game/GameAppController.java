package game;

import util.InputValidator;
import util.StringUtil;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class GameAppController {
    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();
    private GameHost host = new GameHost(inputView, outputView);

    public static void main(String[] args) {
        GameAppController blackjack = new GameAppController();
        blackjack.requestInput();
        blackjack.start();
        blackjack.closeGame();
    }

    private void start() {
        host.start();
    }

    private void requestInput() {
        List<String> names = requestNames();
        List<Double> money = requestAllMoney(names);
        host.setParticipants(names, money);
    }

    private List<String> requestNames() {
        String input = inputView.inputEntry();
        List<String> entry = StringUtil.getListFromString(input);
        return StringUtil.removeBlank(entry);
    }

    private List<Double> requestAllMoney(List<String> entry) {
        List<Double> moneyList = new ArrayList<>();

        while (moneyList.size() < entry.size()) {
            String name = entry.get(moneyList.size());
            moneyList.add(requestMoney(name));
        }

        return moneyList;
    }

    private double requestMoney(String name) {
        String money;

        do {
            money = inputView.inputMoney(name);
        } while (!InputValidator.isNumberInRange(money, GameConstants.RANGE_MIN));

        return Double.parseDouble(money);
    }

    private void closeGame() {
        inputView.close();
    }
}
