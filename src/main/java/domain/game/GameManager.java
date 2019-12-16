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
    }

    private void initializePlayers() {
        ArrayList<String> names = inputManager.getPlayerNames();
        double battingMoney;
        for (String name : names) {
            battingMoney = inputManager.getPlayerBattingMoney(name);
            players.add(new Player(name, battingMoney));
        }
    }
}
