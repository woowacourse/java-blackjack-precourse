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
import view.OutputView;

public class GameManager {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private List<User> playerList;
    private Deck deck;

    public GameManager() {
        playerList = new ArrayList<>();
        deck = new Deck();
    }

    public void play() {
        initPlayer();
        giveCardAtFirst();
        showCards();
    }

    private void initPlayer() {
        List<String> playerName = inputView.getPlayerName();
        for (String name : playerName) {
            int money = inputView.getBettingMoney(name);
            playerList.add(new Player(name,money));
        }
        Dealer dealer = new Dealer();
        playerList.add(dealer);
    }

    private void giveCardAtFirst() {
        for (User player : playerList) {
            player.addCard(deck);
            player.addCard(deck);
        }
    }

    private void showCards() {
        for (User player : playerList) {
            outputView.printCards(player);
        }
    }
}
