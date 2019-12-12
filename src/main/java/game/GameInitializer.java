package game;

import domain.deck.Deck;
import domain.user.Player;
import exception.InvalidException;
import gameInput.GameInputScanner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameInitializer {

    public List<Player> initPlayers(GameInputScanner gameInputScanner) {
        List<String> names = initNames(gameInputScanner);
        return names.stream().map(name -> initPlayer(name, gameInputScanner)).collect(Collectors.toList());
    }

    private List<String> initNames(GameInputScanner gameInputScanner) {
        String namesInputFromUser = gameInputScanner.askNamesFromUser();
        return Arrays.asList(namesInputFromUser.split(","));
    }

    private Player initPlayer(String name, GameInputScanner gameInputScanner) {
        double battingMoney = initBattingMoney(name, gameInputScanner);
        return new Player(name, battingMoney);
    }

    private double initBattingMoney(String name, GameInputScanner gameInputScanner) {
        String battingMonyeFromUser = gameInputScanner.askBattingMoney(name);
        parseBattingMoneyToDouble(battingMonyeFromUser);
        return Double.parseDouble(battingMonyeFromUser);
        // 예외 처리 필요
    }

    private void parseBattingMoneyToDouble(String battingMoneyString) {
        try {
            Double.parseDouble(battingMoneyString);
        } catch (NumberFormatException ne) {
            throw new InvalidException(InvalidException.NOT_A_NUMBER_BATTING_EXCEPTION);
        }
    }

    public void initDeck(Deck deck) {
        deck.initCards();
        deck.shuffle();
    }

}
