package domain.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.*;
import domain.user.*;

public class BlackJack {
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
        checkDealerBlackJack();
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

    public void checkDealerBlackJack() {
        isBlackJack();
        if (winner.contains(dealer))
            showFinalMoney();
    }

    public void isBlackJack() {
        winner = user.stream()
                .filter(p -> p.getScore() == Constant.BUST_POINT)
                .collect(Collectors.toList());
    }

    public void askDraw() {
        for (Player player : user.subList(1, user.size())) {
            playerDraw(player);
        }
        dealerDraw();
        getWinnerList();
        showResult();
        showFinalMoney();
    }

    public void playerDraw(Player player) {
        while (player.getScore() < Constant.BUST_POINT && Input.askDraw(player).equals("y"))  {
            player.addCard(deck.draw());
            player.showCards();
        }
        if(player.getScore() < Constant.BUST_POINT)
            player.showCards();
    }

    public void dealerDraw() {
        if (dealer.getScore() <= Constant.DEALER_POINT) {
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
            dealer.addCard(deck.draw());
        }
    }
    
    public void getWinnerList() {
        if (dealer.getScore() > Constant.BUST_POINT) {
            winner = user;
            return;
        }

        winner = user.stream()
                .filter(p -> p.getScore() >= dealer.getScore() && p.getScore() <= Constant.BUST_POINT)
                .collect(Collectors.toList());
    }

    public List<Player> getLoserList() {
        if (dealer.getScore() > Constant.BUST_POINT)
            return new ArrayList<>();

        return user.stream()
                .filter(p -> p.getScore() < dealer.getScore() || p.getScore() > Constant.BUST_POINT)
                .collect(Collectors.toList());
    }

    public void showResult() {
        System.out.println();
        for (Player player : user) {
            player.showResult();
        }
    }

    public void showFinalMoney() {
        System.out.println("\n##최종수익");

        List<Player> loser = getLoserList();
        dealer.showMoney(loser);

        for (Player player : user.subList(1, user.size())) {
            if (winner.contains(player))
                player.showMoney(Constant.WIN);
            if (loser.contains(player))
                player.showMoney(Constant.LOSE);
            if (!winner.contains(player) && !loser.contains(player))
                player.showMoney(Constant.DRAW);
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.start();
    }
}