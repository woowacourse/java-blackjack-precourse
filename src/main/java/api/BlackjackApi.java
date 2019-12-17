/*
 * @(#)BlackjackApi.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package api;

import java.util.List;

import domain.BlackjackPrinter;
import domain.user.*;

public class BlackjackApi {

    private DealerService dealerService;
    private PlayerService playerService;
    private BlackjackPrinter blackjackPrinter;

    public BlackjackApi(DealerService dealerService, PlayerService playerService, BlackjackPrinter blackjackPrinter) {
        this.dealerService = dealerService;
        this.playerService = playerService;
        this.blackjackPrinter = blackjackPrinter;
    }

    public void receiveDefaultCards(Dealer dealer, List<Player> players) {
        blackjackPrinter.printStart(dealer, players);
        dealerService.receiveDefaultCards(dealer);
        playerService.receiveDefaultCards(players);
        blackjackPrinter.printBreaktime();
    }

    public void confirmCards(List<Player> players, Dealer dealer) {
        playerService.confirmCards(players);
        dealerService.confirmCards(dealer);
        blackjackPrinter.printBreaktime();
    }

    public void match(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            Result result = match(dealer, player);
            settleResult(result, dealer, player);
        }
    }

    private Result match(Dealer dealer, Player player) {
        if (bustExists(dealer, player)) {
            return checkBust(dealer, player);
        }

        if (blackjackExists(dealer, player)) {
            return checkBlackjack(dealer, player);
        }

        return distinguishWinner(dealer, player);
    }

    private boolean bustExists(Dealer dealer, Player player) {
        return dealer.isBust() || player.isBust();
    }

    private Result checkBust(Dealer dealer, Player player) {
        if (player.isBust()) {
            return Result.PlayerLose;
        }

        if (dealer.isBust() && player.isBlackjack()) {
            return Result.PlayerWinWithBlackjack;
        }

        return Result.PlayerWin;
    }

    private boolean blackjackExists(Dealer dealer, Player player) {
        return dealer.isBlackjack() || player.isBlackjack();
    }

    private Result checkBlackjack(Dealer dealer, Player player) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return Result.Draw;
        }
        if (player.isBlackjack()) {
            return Result.PlayerWinWithBlackjack;
        }
        return Result.PlayerLose;
    }

    private Result distinguishWinner(Dealer dealer, Player player) {
        int scoreOfDealer = dealer.calculateScore();
        int scoreOfPlayer = player.calculateScore();
        if (scoreOfDealer < scoreOfPlayer) {
            return Result.PlayerWin;
        }
        if (scoreOfPlayer < scoreOfDealer) {
            return Result.PlayerLose;
        }
        return Result.Draw;
    }

    private void settleResult(Result result, Dealer dealer, Player player) {
        settlePlayerWinWithBlackjack(result, dealer, player);
        settlePlayerWin(result, dealer, player);
        settlePlayerLose(result, dealer, player);
    }

    private void settlePlayerWinWithBlackjack(Result result, Dealer dealer, Player player) {
        if (result.equals(Result.PlayerWinWithBlackjack)) {
            double profit = player.winWithBlackjack();
            dealer.lose(profit);
        }
    }

    private void settlePlayerWin(Result result, Dealer dealer, Player player) {
        if (result.equals(Result.PlayerWin)) {
            double profit = player.win();
            dealer.lose(profit);
        }
    }

    private void settlePlayerLose(Result result, Dealer dealer, Player player) {
        if (result.equals(Result.PlayerLose)) {
            double losedMoney = player.lose();
            dealer.win(Math.abs(losedMoney));
        }
    }

    public void analyze(Dealer dealer, List<Player> players) {
        printResult(dealer, players);
        printProfit(dealer, players);
    }

    private void printResult(Dealer dealer, List<Player> players) {
        dealerService.printResult(dealer);
        playerService.printResult(players);
    }

    private void printProfit(Dealer dealer, List<Player> players) {
        blackjackPrinter.printProfitGuide();
        dealerService.printProfit(dealer);
        playerService.printProfit(players);
    }

    public List<Player> join() {
        return playerService.join();
    }
}
