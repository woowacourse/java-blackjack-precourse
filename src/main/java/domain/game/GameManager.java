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

}
