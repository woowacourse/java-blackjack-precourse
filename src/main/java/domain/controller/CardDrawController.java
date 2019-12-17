/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.card.Card;
import domain.model.card.CardFactory;
import domain.model.user.Dealer;
import domain.model.user.Player;
import input.UserInput;
import view.PrintController;

import java.util.ArrayList;

import static util.RandomIntegerGenerator.generateRandomIntegerByInputInteger;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 카드 뽑는 기능을 담당하는 클래스입니다.
 * @since : 2019.12.17 화요일
 */
public class CardDrawController {
    private static ArrayList<Card> entireCards = new ArrayList<>();
    private static UserInput userInput = new UserInput();

    public static void makeEntireCardSet() {
        entireCards = new ArrayList<>(CardFactory.create());
    }

    public static boolean checkCanNotDrawMore(Player player) {
        return player.isBlackJack() || player.isBurst();
    }

    public static void drawInitialCards(ArrayList<Player> playerList, Dealer dealer) {
        drawInitialPlayersCards(playerList);
        drawInitialDealerCards(dealer);
    }

    public static void drawInitialPlayersCards(ArrayList<Player> playerList) {

        for (Player player : playerList) {
            player.addCard(drawCard());
            player.addCard(drawCard());
        }
    }

    public static void drawInitialDealerCards(Dealer dealer) {
        dealer.addCard(drawCard());
        dealer.addCard(drawCard());
    }

    public static Card drawCard() {
        int cardIndex = generateRandomIntegerByInputInteger(entireCards.size());
        Card drawnCard = entireCards.get(cardIndex);
        entireCards.remove(cardIndex);
        return drawnCard;
    }

    public static boolean controlPlayerExtraDrawing(Player player) {
        boolean userAnswer = controlPlayerAnswer(player);

        if (checkCanNotDrawMore(player)) { // 블랙잭상황 혹은 버스트 발생시 false를 리턴
            return false;
        }

        return userAnswer;
    }

    public static boolean controlPlayerAnswer(Player player) {
        boolean userAnswer = userInput.getPlayerAnswer(player);

        if (userAnswer) {
            player.addCard(drawCard());
            PrintController.printPlayerCardInformation(player);
        }

        return userAnswer;
    }

    public static void controlDealerExtraDrawing(Dealer dealer) {
        dealer.addCard(drawCard());
    }


}
