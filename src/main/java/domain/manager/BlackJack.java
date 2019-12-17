package domain.manager;

import java.util.ArrayList;
import java.util.List;

import domain.card.*;
import domain.user.*;

public class BlackJack {
    private static final int WINNER_PIVOT = 21;
    private static final int DEALER_PIVOT = 16;
    private static final int WIN = 1;
    private static final double WIN_BLACKJACK = 1.5;
    private static final int LOSE = -1;

    private Dealer dealer;
    private List<Player> user;
    private Deck deck;

    public BlackJack() {
        user = new ArrayList<>();
        user.add(new Dealer());
        user.addAll(Input.setPlayerList());
        dealer = (Dealer)user.get(0);
        deck = new Deck();
    }

    public void start() {
        System.out.println("딜러와 " + getPlayerNameList() + "에게 카드를 2장씩 분배합니다.");
        for (Player player : user) {
            player.addCard(deck.draw());
            player.addCard(deck.draw());
            player.showCards();
        }
    }

    public String getPlayerNameList() {
        List<String> playerNameList = new ArrayList<>();

        for (Player player : user) {
            playerNameList.add(player.getName());
        }

        return String.join(",", playerNameList);
    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.start();
    }
}