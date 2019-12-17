package game;

import domain.deck.Deck;
import domain.user.Dealer;
import domain.user.Player;
import gameInput.GameInputScanner;
import gameOutput.GamePrinter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private GameInputScanner gameInputScanner = new GameInputScanner();

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    public void play() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        initDeck(deck);
        List<Player> players = initPlayers();
        runGame(dealer, players, deck);
        makeResult(dealer, players);
    }

    private void initDeck(Deck deck) {
        deck.initCards();
        deck.shuffle();
    }

    private List<Player> initPlayers() {
        List<String> names = initNames();
        return names.stream().map(this::initPlayer).collect(Collectors.toList());
    }

    private List<String> initNames() {
        String namesInputFromUser = GameInputScanner.askNamesFromUser();
        return Arrays.asList(namesInputFromUser.split(","));
    }

    private Player initPlayer(String name) {
        double battingMoney = initBattingMoney(name);
        return new Player(name, battingMoney);
    }

    private double initBattingMoney(String name) {
        String battingMoneyFromUser = GameInputScanner.askBattingMoney(name);
        return Double.parseDouble(battingMoneyFromUser);
    }

    private void runGame(Dealer dealer, List<Player> players, Deck deck) {
        handoutCards(dealer, players, deck);
        GamePrinter.printStatus(dealer, players);
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
            oneMoreCard = pickOneMore(player, deck);
        }
    }

    private boolean pickOneMore(Player player, Deck deck) {
        if (!player.isBursted()) {
            return wantMoreCard(player, deck);
        }
        System.out.println("해당 플레이어는 죽었습니다");
        return false;
    }

    private boolean wantMoreCard(Player player, Deck deck) {
        while (true) {
            String answer = GameInputScanner.askAddOneMoreCard(player);
            if (answer.equals("y")) {
                player.addCard(deck.draw());
                GamePrinter.printPlayerStatus(player);
                return true;
            }
            if (answer.equals("n")) {
                return false;
            }
            System.out.println("답은 y 또는 n으로만 가능합니다.");
        }
    }

    private void checkDealer(Dealer dealer, Deck deck) {
        while (dealer.isUnberSixteen()) {
            dealer.addCard(deck.draw());
            System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
        }
    }

    private void makeResult(Dealer dealer, List<Player> players) {
        GamePrinter.printResult(dealer, players);
    }
}
