/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.user.Dealer;
import domain.model.user.Player;
import input.UserInput;
import view.PrintController;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

import static view.PrintController.*;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 블랙잭 게임의 전체적인 진행 로직을 담당하는 클래스입니다.
 * @since : 2019.12.12 목요일
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

        // test
        System.out.println("fin");

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
        if (resultController.checkInitialResult(playerList, dealer)) {
            // TODO 처음에 블랙잭이 발생하면 If문으로 들어옴. 이 안에는 결과를 내야함.
            // 블랙잭이 딜러인지 플레이어인지 판단하고 그에 따라서 총 결과와 돈 내보이고 출력하기.
            // resultcontroller에 만들어서 처리하기
            System.out.println("누가 블랙잭인지 보고 돈계산하고 끝내야함~");
        }
        // test
        System.out.println("DEALER?" + dealer.getAllCardsScore());
        System.out.println("DEALER?" + dealer.getCurrentScore());

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
        // test
        System.out.println("DEALER?" + dealer.getAllCardsScore());

    }
}
