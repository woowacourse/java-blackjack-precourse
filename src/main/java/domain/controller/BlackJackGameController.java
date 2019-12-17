/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.user.Dealer;
import domain.model.user.Player;
import input.UserInput;
import view.PrintController;

import java.util.ArrayList;
import java.util.List;

import static view.PrintController.*;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 블랙잭 게임의 전체적인 진행 로직을 담당하는 클래스입니다.
 * @since : 2019.12.17 화요일
 */
public class BlackJackGameController {
    private static final String COMMA = ", ";
    private static final int EXTRA_CARD_LIMIT_FOR_DEALER = 16;
    private static final int TWO = 2;
    public ArrayList<Player> playerList = new ArrayList<>();
    public Dealer dealer;
    public List<String> playerNameList;


    public void blackJackGameController() {
        makePlayerInstance();
        makeDealerInstance();
        CardDrawController.makeEntireCardSet();
        initiateDrawing();
        moreDrawing();
        finishBlackJackGame();
    }

    public void makePlayerInstance() {
        Double bettingMoney;
        UserInput userInput = new UserInput();

        playerNameList = userInput.getPlayerName();

        for (String name : playerNameList) {
            bettingMoney = userInput.getBettingMoney(name);
            playerList.add(new Player(name, bettingMoney));
        }

    }

    public void makeDealerInstance() {
        dealer = new Dealer();
    }

    public void initiateDrawing() {
        CardDrawController cardDrawController = new CardDrawController();
        cardDrawController.drawInitialCards(playerList, dealer);
        printHandingTwoCardsInformation(String.join(COMMA, playerNameList));
        printDealerCardInformation(this.dealer);
        for (Player player : this.playerList) {
            printPlayerCardInformation(player);
        }
        ResultController resultController = new ResultController();
        if (dealer.isBlackJack()) { // 딜러 블랙잭발생.
            resultController.startBlackJackProcedure(playerList, dealer);
        }
//        // test
//        System.out.println("DEALER?" + dealer.getAllCardsScore());
//        System.out.println("DEALER?" + dealer.getCurrentScore());
//        System.out.println("DEALER?" + dealer.checkHowManyAceInCards());

    }

    public void moreDrawing() {
        for (Player player : playerList) {
            askMoreDrawingLoop(player);
        }
        tryDealerExtraDrawing();

    }

    public void askMoreDrawingLoop(Player player) {
        boolean userAnswer;
        CardDrawController cardDrawController = new CardDrawController();

        if (CardDrawController.checkCanNotDrawMore(player)) return; // 플레이어가 블랙잭이거나 버스트이면 더 드로우 하지 않게 한다.

        do {
            userAnswer = cardDrawController.controlPlayerExtraDrawing(player);
        } while (userAnswer);
    }

    public void tryDealerExtraDrawing() {
        CardDrawController cardDrawController = new CardDrawController();
        if (dealer.getCurrentScore() > EXTRA_CARD_LIMIT_FOR_DEALER) {
            PrintController.printDealerCanNotDrawMoreCard();
            return;
        }
        while (dealer.getCurrentScore() <= EXTRA_CARD_LIMIT_FOR_DEALER) {
            cardDrawController.controlDealerExtraDrawing(dealer);
        }

        PrintController.printDealerExtraCards(dealer.getAllCards().size() - TWO);
//        // test
//        System.out.println("DEALER?" + dealer.getAllCardsScore());
    }

    public void finishBlackJackGame() {
        ResultController resultController = new ResultController();
        resultController.controlPrintingFinalResult(playerList, dealer);

        if (dealer.isBurst()) {
            resultController.startDealerBurstProcedure(playerList, dealer);
            return;
        }

        resultController.startNormalFinishProcedure(playerList, dealer);

    }
}
