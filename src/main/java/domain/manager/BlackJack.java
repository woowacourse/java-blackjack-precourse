package domain.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.*;
import domain.user.*;

public class BlackJack {
    private static final int WINNER_PIVOT = 21;
    private static final int DEALER_PIVOT = 16;
    private static final int WIN = 1;
    private static final double WIN_BLACKJACK = 1.5;
    private static final int NOTTING = 0;
    private static final int LOSE = -1;

    private Dealer dealer;
    private List<Player> user;
    private Deck deck;
    private List<Player> winner;

    public BlackJack() {
        user = new ArrayList<>();
        user.add(new Dealer());
        user.addAll(Input.setPlayerList());
        dealer = (Dealer)user.get(0);
        deck = new Deck();
    }

    public void start() {
        System.out.println("\n딜러와 " + getPlayerNameList() + "에게 카드를 2장씩 분배합니다.");
        for (Player player : user) {
            player.addCard(deck.draw());
            player.addCard(deck.draw());
            player.showCards();
        }
        checkBlackJack();
        askDraw();
    }

    public String getPlayerNameList() {
        List<String> playerNameList = new ArrayList<>();

        for (Player player : user) {
            playerNameList.add(player.getName());
        }
        playerNameList.remove(0);

        return String.join(",", playerNameList);
    }

    public void isBlackJack() {
        winner = user.stream()
                .filter(p -> p.getScore() == WINNER_PIVOT)
                .collect(Collectors.toList());
    }

    public List<Player> getLoserList() {
        return 
        user.stream()
            .filter(p -> winner.contains(p))
            .collect(Collectors.toList());
    }

    public void checkBlackJack() {
        isBlackJack();
        if (winner.contains(dealer) && winner.size() == 1)
            showOnlyDealerBlackJack();
        if (winner.contains(dealer))
            showBothBlackJack(winner);
        if (winner.size() != 0)
            showOnlyPlayrBlackJack(winner);
    }

    public void showOnlyDealerBlackJack() {
        System.out.println("\n##최종수익");

        dealer.showMoney(user);
        for (Player player : user) {
            player.showMoney(LOSE);
        }

        System.exit(0);
    }

    public void showBothBlackJack(List<Player> winner) {
        System.out.println("\n##최종수익");

        dealer.showMoney(getLoserList());
        user.remove(0);
        for (Player player : user) {
            if (winner.contains(player))
                player.showMoney(WIN);
            if (!winner.contains(player))
                player.showMoney(LOSE);
        }

        System.exit(0);
    }

    public void showOnlyPlayrBlackJack(List<Player> winner) {
        System.out.println("\n##최종수익");

        dealer.showMoney(getLoserList());
        user.remove(0);
        for (Player player : user) {
            if (winner.contains(player))
                player.showMoney(WIN_BLACKJACK);
            if (!winner.contains(player))
                player.showMoney(NOTTING);
        }

        System.exit(0);
    }

    public void askDraw() {
        for (Player player : user) {
            playerDraw(player);
        }
    }

    public void playerDraw(Player player) {
        while (Input.askDraw(player).equals("y") && player.getScore() < WINNER_PIVOT) {
            player.addCard(deck.draw());
            player.showCards();
        }
        player.showCards();
    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.start();
    }
}