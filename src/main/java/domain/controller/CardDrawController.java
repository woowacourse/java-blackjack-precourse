/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.card.Card;
import domain.model.card.CardFactory;
import domain.model.card.Symbol;
import domain.model.card.Type;
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
 * @since : 2019.12.13 금요일
 */
public class CardDrawController {
    private static final int EXTRA_CARD_LIMIT_FOR_DEALER = 16;
    private static ArrayList<Card> entireCards = new ArrayList<>();

    public static void makeEntireCardSet() {
        entireCards = new ArrayList<>(CardFactory.create());
    }

    public void drawInitialCards(ArrayList<Player> playerList, Dealer dealer) {
        drawInitialPlayersCards(playerList);
        drawInitialDealerCards(dealer);

    }

    public Card drawCard() {
        int cardIndex = generateRandomIntegerByInputInteger(entireCards.size());
        Card drawnCard = entireCards.get(cardIndex);
        entireCards.remove(cardIndex);
        return drawnCard;
    }

    public void drawInitialPlayersCards(ArrayList<Player> playerList) {
        for (Player player : playerList) {
            player.addCard(drawCard());
            player.addCard(drawCard());
        }
    }

    public void drawInitialDealerCards(Dealer dealer) {
//        Card card1 = new Card(Symbol.ACE, Type.CLUB);
//        Card card2 = new Card(Symbol.QUEEN, Type.CLUB);
//        dealer.addCard(card1);
//        dealer.addCard(card2);
        dealer.addCard(drawCard());
        dealer.addCard(drawCard());

    }

    public boolean controlPlayerExtraDrawing(Player player) {
        UserInput userInput = new UserInput();
        boolean userAnswer = userInput.getPlayerAnswer(player);

        ResultController resultController = new ResultController();
        if (userAnswer) {
            player.addCard(drawCard());
            PrintController.printPlayerCardInformation(player);
        }

        if (resultController.checkBurstOrNot(player.getCurrentScore())) {
            // 버스트라도 그 판은 종료해야함. 다음 플레이어도 갈 수 있도록!! (휴먼카지노에 넣어줘도 좋을듯)
            System.out.println("버스트 처리해주기");
            return false;
        }

        return userAnswer;

    }

    public void controlDealerExtraDrawing(Dealer dealer) {
        dealer.addCard(drawCard());
    }


}
