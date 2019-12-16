/*
 * ErrorMessage.java
 *
 * version 1.0
 *
 * 20191216
 *
 * copyright by jiwonSong(s26788761@naver.com)
 *
 */

package domain.game;

import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;

public class GameManager {

    private static final int START_CARD_COUNT = 2;

    private CardDeck cardDeck;
    private Dealer dealer;
    private ArrayList<Player> players;
    private InputManager inputManager;

    public GameManager() {
        cardDeck = new CardDeck();
        dealer = new Dealer();
        inputManager = new InputManager();
    }

    public void start() {
        initializePlayers();
        drawTwoCardsToDealerAndPlayer();
        showCardLists();
        getMoreCards();
        showFinalCardLists();
    }

    private void initializePlayers() {
        ArrayList<String> names = inputManager.getPlayerNames();
        double battingMoney;
        for (String name : names) {
            battingMoney = inputManager.getPlayerBattingMoney(name);
            players.add(new Player(name, battingMoney));
        }
    }

    private void drawTwoCardsToDealerAndPlayer() {
        print(Message.makeMessageDrawTwoCardsToDealerAndPlayer(players));

        for (int i=0; i<START_CARD_COUNT; i++) {
            dealer.addCard(cardDeck.getRandomCard());
        }
        for (Player player : players) {
            drawTwoCardsToPlayer(player);
        }
    }

    private void drawTwoCardsToPlayer(Player player) {

        for (int i=0; i<START_CARD_COUNT; i++) {
            player.addCard(cardDeck.getRandomCard());
        }
    }

    private void print(String message) {
        System.out.println(message);
    }

    private void showCardLists() {
        print(Message.makeMessageDealerSmallestState(dealer));
        for (Player player : players) {
            print(Message.makeMessagePlayerState(player));
        }
    }

    private void getMoreCards() {
        for (Player player : players) {
            askPlayerDrawOrNot(player);
        }
        checkDealerDrawOrNot(dealer);
    }

    private void askPlayerDrawOrNot(Player player) {
        while (inputManager.getPlayerChoiceDrawOneMoreOrNot(player)) {
            player.addCard(cardDeck.getRandomCard());
            print(Message.makeMessagePlayerState(player));
        }
        print(Message.makeMessagePlayerState(player));
    }

    private void checkDealerDrawOrNot(Dealer dealer) {
        if (dealer.getMoreDraw()) {
            dealerDrawMore(dealer);
        }
        print(Message.makeMessageDealerGetNoMoreCard());
    }

    private void dealerDrawMore(Dealer dealer) {
        while (dealer.getMoreDraw()) {
            dealer.addCard(cardDeck.getRandomCard());
            print(Message.makeMessageDealerGetMoreCard());
        }
    }

    private void showFinalCardLists() {
        print(Message.makeMessageDealerResult(dealer));

        for (Player player : players) {
            print(Message.makeMessagePlayerResult(player));
        }
    }
}
