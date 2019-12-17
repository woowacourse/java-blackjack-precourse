/*
 * @(#)BlackjackApiImpl.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package api;

import java.util.List;

import domain.BlackjackPrinter;
import domain.user.*;

public class BlackjackApiImpl implements BlackjackApi {

    private DealerService dealerService;
    private PlayerService playerService;
    private BlackjackPrinter blackjackPrinter;

    public BlackjackApiImpl(DealerService dealerService, PlayerService playerService, BlackjackPrinter blackjackPrinter) {
        this.dealerService = dealerService;
        this.playerService = playerService;
        this.blackjackPrinter = blackjackPrinter;
    }

    /**
     * 플레이어들이 게임에 참여하도록 하는 함수입니다.
     */
    @Override
    public List<Player> join() {
        return playerService.join();
    }

    /**
     * 카드를 섞어주는 함수입니다.
     */
    @Override
    public void shuffle() {
        dealerService.shuffle();
    }

    /**
     * 게임 시작 후, 딜러 및 모든 플레이어들이 기본 카드를 지급받는 함수입니다.
     */
    @Override
    public void receiveDefaultCards(Dealer dealer, List<Player> players) {
        blackjackPrinter.printStart(dealer, players);
        dealerService.receiveDefaultCards(dealer);
        playerService.receiveDefaultCards(players);
        blackjackPrinter.printBreaktime();
    }

    /**
     * 딜러는 규칙에 따라 카드를 더 받거나 받지 않고, 플레이어들은 판단에 따라 더 받거나 받지 않아 카드를 확정합니다.
     */
    @Override
    public void confirmCards(List<Player> players, Dealer dealer) {
        playerService.confirmCards(players);
        dealerService.confirmCards(dealer);
        blackjackPrinter.printBreaktime();
    }

    /**
     * 확정된 카드로 딜러 vs 각 플레이어가 경합하는 함수입니다.
     */
    @Override
    public void match(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            Result result = match(dealer, player);
            settleResult(result, dealer, player);
        }
    }

    /**
     * 결과 및 최종 수익을 출력하는 함수입니다.
     */
    @Override
    public void analyze(Dealer dealer, List<Player> players) {
        printResult(dealer, players);
        printProfit(dealer, players);
    }

    private Result match(Dealer dealer, Player player) {
        if (bustExists(dealer, player)) {
            return matchWithBust(dealer, player);
        }

        if (blackjackExists(dealer, player)) {
            return matchWithBlackjack(dealer, player);
        }

        return distinguishWinner(dealer, player);
    }

    private boolean bustExists(Dealer dealer, Player player) {
        return dealer.isBust() || player.isBust();
    }

    private Result matchWithBust(Dealer dealer, Player player) {
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

    private Result matchWithBlackjack(Dealer dealer, Player player) {
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

    /**
     * 딜러, 플레이어의 경합 결과에 따라 수익을 정산하는 함수입니다.
     */
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

    private void printResult(Dealer dealer, List<Player> players) {
        dealerService.printResult(dealer);
        playerService.printResult(players);
    }

    private void printProfit(Dealer dealer, List<Player> players) {
        blackjackPrinter.printProfitGuide();
        dealerService.printProfit(dealer);
        playerService.printProfit(players);
    }
}
