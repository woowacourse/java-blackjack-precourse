package game;

import domain.card.Card;
import domain.deck.Deck;
import domain.user.Dealer;
import domain.user.Player;
import exception.InvalidException;
import gameInput.GameInputScanner;
import gameOutput.GamePrinter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private static final String USERNAME_DELEMITER = ",";
    private static final String USER_BURSTED_STATEMENT = "해당 플레이어는 죽었습니다.";
    private static final String DEALER_ADD_MORE_CARD_STATEMENT = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String MORE_CARD_STRING = "y";
    private static final String NO_MORE_CARD_STRING = "n";

    public void play() {
        Dealer dealer = new Dealer();
        Deck deck = initDeck();
        List<Player> players = initPlayers();
        runGame(dealer, players, deck);
        GamePrinter.printResult(dealer, players);
        GamePrinter.printProfit(dealer, players);
    }

    private Deck initDeck() {
        return new Deck();
    }

    private List<Player> initPlayers() {
        List<String> names = initNames();
        validateDuplication(names);
        return names.stream().map(String::trim).map(this::initPlayer).collect(Collectors.toList());
    }

    private List<String> initNames() {
        String namesInputFromUser = GameInputScanner.askNamesFromUser();
        return Arrays.asList(namesInputFromUser.split(USERNAME_DELEMITER));
    }

    private void validateDuplication(List<String> names) {
        if (names.stream().map(String::trim).distinct().count() != names.size()) {
            throw new InvalidException(InvalidException.PLAYER_NAME_DUPLICATE_EXCEPTION);
        }
    }

    private Player initPlayer(String name) {
        double battingMoney = initBattingMoney(name);
        return new Player(name, battingMoney);
    }

    private double initBattingMoney(String name) {
        String battingMoneyFromUser = GameInputScanner.askBattingMoney(name);
        System.out.println();
        return Double.parseDouble(battingMoneyFromUser);
    }

    private void runGame(Dealer dealer, List<Player> players, Deck deck) {
        handoutCards(dealer, players, deck);
        GamePrinter.printStatus(dealer, players);
        System.out.println();
        askPlayers(players, deck);
        checkDealer(dealer, deck);
    }

    private void handoutCards(Dealer dealer, List<Player> players, Deck deck) {
        for (int i = 0; i < 2; i++) {
            handoutCard(dealer, deck);
            handoutCard(players, deck);
        }
    }

    private void handoutCard(Dealer dealder, Deck deck) {
        dealder.addCard(deck.draw());
    }

    private void handoutCard(List<Player> players, Deck deck) {
        players.forEach(player -> handoutCard(player, deck));
    }

    private void handoutCard(Player player, Deck deck) {
        player.addCard(deck.draw());
    }

    private void askPlayers(List<Player> players, Deck deck) {
        players.forEach(player -> askPlayer(player, deck));
    }

    private void askPlayer(Player player, Deck deck) {
        boolean oneMoreCard = true;

        while (oneMoreCard) {
            GamePrinter.printPlayerStatus(player);
            oneMoreCard = pickOneMore(player, deck);
            System.out.println();
        }
    }

    private boolean pickOneMore(Player player, Deck deck) {
        if (!player.isBursted()) {
            return wantMoreCard(player, deck);
        }
        System.out.println(USER_BURSTED_STATEMENT);
        return false;
    }

    private boolean wantMoreCard(Player player, Deck deck) {
        String answer = GameInputScanner.askAddOneMoreCard(player);
        return checkAnswer(player, deck, answer.trim());
    }

    private boolean checkAnswer(Player player, Deck deck, String answer) {
        if (answer.equals(NO_MORE_CARD_STRING)) {
            return false;
        }
        if (answer.equals(MORE_CARD_STRING)) {
            Card card = deck.draw();
            player.addCard(card);
            return true;
        }
        throw new InvalidException(InvalidException.ANSWER_SHOULD_BE_Y_OR_N_EXCEPTION);
    }

    private void checkDealer(Dealer dealer, Deck deck) {
        while (dealer.isUnberSixteen()) {
            dealer.addCard(deck.draw());
            System.out.println();
            System.out.println(DEALER_ADD_MORE_CARD_STATEMENT);
        }
    }
}
