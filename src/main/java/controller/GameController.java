/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * GameController.java
 * 게임을 진행하기 위한 클래스
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package controller;

import domain.user.Player;
import view.InputView;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private static final InputView inputView = new InputView();

    private List<Player> initPlayer() {
        List<String> playerName = inputView.getPlayerName();
        List<Player> playerList = new ArrayList<>();
        for (String name : playerName) {
            int money = inputView.getBettingMoney(name);
            playerList.add(new Player(name,money));
        }
        return playerList;
    }

    public void play() {
        List<Player> playerList = initPlayer();
    }
}
