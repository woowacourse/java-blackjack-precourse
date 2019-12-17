/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.user.Dealer;
import domain.model.user.Player;
import view.PrintController;

import java.util.ArrayList;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 플레이어와 딜러의 점수를 계산하고 버스트, 블랙잭, 최종결과를 계산하고 처리하는 클래스입니다.
 * @since : 2019.12.17 화요일
 */
public class ResultController {

    private static final int EXIT_STATUS = 0;

    public void startBlackJackProcedure(ArrayList<Player> playerList, Dealer dealer) {
        if (checkPlayerBlackJackOrNot(playerList)) {// 딜러가 블랙잭이고 플레이어 중에도 블랙잭이 있는 경우
            controlPrintingFinalResult(playerList, dealer);
            continueBlackJackProcedureForBoth(playerList, dealer);
            System.exit(EXIT_STATUS);
        }

        // 딜러만 블랙잭인 경우
        controlPrintingFinalResult(playerList, dealer);
        continueBlackJackProcedureForDealer(playerList, dealer);
        System.exit(EXIT_STATUS);
    }

    public void continueBlackJackProcedureForBoth(ArrayList<Player> playerList, Dealer dealer) {
        double totalPlayerProfit = 0;
        for (Player player : playerList) {
            totalPlayerProfit
                    += player.getProfitByBettingMoney(ProfitController.getPlayerProfitForBlackJack(player));
        }
        PrintController.printFinalProfit();
        PrintController.printDealerProfit(-totalPlayerProfit);
        for (Player player : playerList) {
            PrintController.printPlayerProfit(player.getProfitByBettingMoney(ProfitController.getPlayerProfitForBlackJack(player)), player);
        }
    }

    public void continueBlackJackProcedureForDealer(ArrayList<Player> playerList, Dealer dealer) {
        double totalPlayerProfit = 0;
        for (Player player : playerList) {
            totalPlayerProfit += player.getProfitByBettingMoney(ProfitController.getPlayerDefeatedProfitRate());
        }
        PrintController.printFinalProfit();
        PrintController.printDealerProfit(-totalPlayerProfit);
        for (Player player : playerList) {
            PrintController.printPlayerProfit(player.getProfitByBettingMoney(ProfitController.getPlayerDefeatedProfitRate()), player);
        }
    }

    public boolean checkPlayerBlackJackOrNot(ArrayList<Player> playerList) {
        boolean ifSomePlayerBlackJack = false;

        for (Player player : playerList) {
            ifSomePlayerBlackJack = player.isBlackJack() || ifSomePlayerBlackJack;
        }

        return ifSomePlayerBlackJack;
    }

    public void controlPrintingFinalResult(ArrayList<Player> playerList, Dealer dealer) {
        PrintController.printDealerCardFinalInformation(dealer);
        for (Player player : playerList) {
            PrintController.printPlayerCardFinalInformation(player);
        }
    }

    public void startDealerBurstProcedure(ArrayList<Player> playerList, Dealer dealer) {
        double totalPlayerProfit = 0;
        for (Player player : playerList) {
            totalPlayerProfit += player.getProfitByBettingMoney(ProfitController.getPlayerWonProfitRate());
        }
        PrintController.printFinalProfit();
        PrintController.printDealerProfit(-totalPlayerProfit);
        for (Player player : playerList) {
            PrintController.printPlayerProfit(player.getProfitByBettingMoney(ProfitController.getPlayerWonProfitRate()), player);
        }
    }

    public void startNormalFinishProcedure(ArrayList<Player> playerList, Dealer dealer) {
        double totalPlayerProfit = 0;
        for (Player player : playerList) {
            totalPlayerProfit += player.getProfitByBettingMoney(ProfitController.getPlayerResultOnNormalFinishProcedure(player, dealer));
        }
        PrintController.printFinalProfit();
        PrintController.printDealerProfit(-totalPlayerProfit);
        for (Player player : playerList) {
            PrintController.printPlayerProfit(player.getProfitByBettingMoney(ProfitController.getPlayerResultOnNormalFinishProcedure(player, dealer)), player);
        }
    }
}
