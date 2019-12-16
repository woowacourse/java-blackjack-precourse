package game;

import domain.deck.Deck;
import domain.user.Dealer;
import domain.user.Player;
import gameInput.GameInputScanner;

import java.util.List;

public class GameRunner {

    private static final int NUMBER_OF_FIRST_DRAWS = 2;

    public void startGame(Dealer dealer, List<Player> players, Deck deck) {
        for (int i = 0; i < NUMBER_OF_FIRST_DRAWS; i++) {
            dealer.addCard(deck.draw());
            oneTurn(players, deck);
        }
    }

    private void oneTurn(List<Player> players, Deck deck) {
        players.stream().forEach(player -> player.addCard(deck.draw()));
    }

    public void askPlayers(List<Player> players, Deck deck, GameInputScanner gameInputScanner) {
        players.forEach(player -> askPlayer(player, deck, gameInputScanner));
    }

    private void askPlayer(Player player, Deck deck, GameInputScanner gameInputScanner) {
        boolean oneMoreCard = true;

        while (oneMoreCard) {
            oneMoreCard = pickOneMore(player, deck, gameInputScanner);
//            System.out.println(player.makeStatusStatement());
        }
    }

    private boolean pickOneMore(Player player, Deck deck, GameInputScanner gameInputScanner) {
        if (player.isInGame()) {
            return wantMoreCard(player, deck, gameInputScanner);
        }
        System.out.println("해당 플레이어는 죽었습니다.");
        return false;
    }

    private boolean wantMoreCard(Player player, Deck deck, GameInputScanner gameInputScanner) {
        while (true) {
            String answer = gameInputScanner.askAddOneMoreCard(player);
            if (answer.equals("y")) {
                player.addCard(deck.draw());
                return true;
            }
            if (answer.equals("n")) {
                return false;
            }
            System.out.println("답은 y 또는 n으로만 가능합니다.");
        }
    }

    private boolean checkMoreCard(Player player, Deck deck, String answer) {
        if (answer.equals("y")) {
            player.addCard(deck.draw());
            return true;
        }
        if (answer.equals("n")) {
            return false;
        }
        return false;
    }

    public void askDealer(Dealer dealer, Deck deck) {
        if (dealer.isUnberSixteen()) {
            System.out.println("딜러는 16이하라 1장의 카드를 더 받습니다.");
            dealer.addCard(deck.draw());
        }
    }

}
