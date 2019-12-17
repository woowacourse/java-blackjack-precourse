/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * GameManager.java
 * 게임을 진행하기 위한 클래스
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

import java.util.ArrayList;
import java.util.List;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import view.InputView;

public class GameManager {
    private static final InputView inputView = new InputView();

    private List<User> initPlayer() {
        List<String> playerName = inputView.getPlayerName();
        List<User> playerList = new ArrayList<>();
        Dealer dealer = new Dealer();
        for (String name : playerName) {
            int money = inputView.getBettingMoney(name);
            playerList.add(new Player(name,money));
        }
        playerList.add(dealer);
        return playerList;
    }

    private void giveCardAtFirst(List<User> alivePlayerList, Deck deck) {
        for (User user : alivePlayerList) {
            user.addCard(deck);
            user.addCard(deck);
        }
    }

    public void play() {
        List<User> playerList = initPlayer();    // 플레이어들과 딜러를 담고 있는 list
        Deck deck = new Deck();
        giveCardAtFirst(playerList, deck);
    }
}
