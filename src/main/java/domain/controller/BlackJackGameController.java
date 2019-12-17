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

import static domain.controller.CardDrawController.*;
import static view.PrintController.*;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 블랙잭 게임의 전체적인 진행 로직을 담당하는 클래스입니다.
 * @since : 2019.12.17 화요일
 */
public class BlackJackGameController {
    private static final String COMMA = ", ";
    private static final int EXTRA_CARD_LIMIT_SCORE_FOR_DEALER = 16;
    private static final int TWO = 2;
    public List<String> playerNameList;
    public ArrayList<Player> playerList = new ArrayList<>();
    public Dealer dealer;

    public void blackJackGameController() {
        makePlayerInstance();
        makeDealerInstance();
        makeEntireCardSet();

        initiateDrawing();
        moreDrawing();
        finishBlackJackGame();
    }

    public void makePlayerInstance() {
        double bettingMoney;
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
        ResultController resultController = new ResultController();
        drawInitialCards(playerList, dealer);
        PrintController.printHandingTwoCardsInformation(String.join(COMMA, playerNameList));
        PrintController.printDealerCardInformation(dealer);
        playerList.forEach(PrintController::printPlayerCardInformation);
        printBlankLine();

        if (dealer.isBlackJack()) { // 딜러 블랙잭발생.
            resultController.startBlackJackProcedure(playerList, dealer);
        }
    }

    public void moreDrawing() {
        playerList.forEach(this::askMoreDrawingLoop);
        tryDealerExtraDrawing();
    }

    public void askMoreDrawingLoop(Player player) {
        boolean userAnswer;

        if (CardDrawController.checkCanNotDrawMore(player)) { // 플레이어가 블랙잭이거나 버스트이면 더 드로우 하지 않게 한다.
            return;
        }

        do {
            userAnswer = controlPlayerExtraDrawing(player);
        } while (userAnswer);
    }

    public void tryDealerExtraDrawing() {

        if (dealer.getCurrentScore() > EXTRA_CARD_LIMIT_SCORE_FOR_DEALER) {
            PrintController.printDealerCanNotDrawMoreCard();
            return;
        }

        letDealerExtraDrawing();
        PrintController.printDealerExtraCards(dealer.getAllCards().size() - TWO);
    }

    public void letDealerExtraDrawing() {

        while (dealer.getCurrentScore() <= EXTRA_CARD_LIMIT_SCORE_FOR_DEALER) {
            controlDealerExtraDrawing(dealer);
        }
    }

    public void finishBlackJackGame() {
        ResultController resultController = new ResultController();
        resultController.controlPrintingFinalResult(playerList, dealer);

        if (dealer.isBurst()) {
            resultController.startDealerBurstProcedure(playerList);
            return;
        }

        resultController.startNormalFinishProcedure(playerList, dealer);
    }
}
