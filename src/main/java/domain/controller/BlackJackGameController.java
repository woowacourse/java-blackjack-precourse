/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.user.Dealer;
import domain.model.user.Player;
import input.UserInput;

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
    public ArrayList<Player> playerList = new ArrayList<>();
    public Dealer dealer;
    public List<String> playerNameList;

    public void blackJackGameController() {
        makePlayerInstance();
        makeDealerInstance();
        CardDrawController.makeEntireCardSet();
        initiateDrawing();

    }

    public void makePlayerInstance() {

        Double bettingMoney;

        UserInput userInput = new UserInput();
        playerNameList = userInput.getPlayerName();

        // test
        for (String name : playerNameList) {
            System.out.println("test: " + name);
            bettingMoney = userInput.getBettingMoney(name);
            System.out.println("test: " + bettingMoney);
            playerList.add(new Player(name, bettingMoney));
        }
        System.out.println("==============");
        System.out.println("==============");
        System.out.println("==============");

        // test
        for (Player player : playerList) {
            player.getName();
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
    }


}
