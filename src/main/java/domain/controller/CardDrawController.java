/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.card.Card;
import domain.model.card.CardFactory;
import domain.model.user.Dealer;
import domain.model.user.Player;

import java.util.ArrayList;

import static util.RandomIntegerGenerator.generateRandomIntegerByInputInteger;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 카드 뽑는 기능을 담당하는 클래스입니다.
 * @since : 2019.12.13 금요일
 */
public class CardDrawController {
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
        dealer.addCard(drawCard());
        dealer.addCard(drawCard());
    }


}
