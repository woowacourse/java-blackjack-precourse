package game;/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * game.GameManager.java
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
    public static final InputView inputView = new InputView();
    public static final OutputView outputView = new OutputView();
    private List<User> userList;
    private Deck deck;

    public GameManager() {
        initPlayer();
        deck = new Deck();
    }

    public void play() {
        giveCardAtFirst();
        showCards();
        giveMoreCard();
        showCards();
    }

    private void initPlayer() {
        List<String> playerName = inputView.getPlayerName();
        userList = new ArrayList<>();
        for (String name : playerName) {
            int money = inputView.getBettingMoney(name);
            userList.add(new Player(name,money));
        }
        Dealer dealer = new Dealer();
        userList.add(dealer);
    }

    private void giveCardAtFirst() {
        for (User user : userList) {
            user.addCard(deck);
            user.addCard(deck);
        }
    }

    private void showCards() {
        for (User user : userList) {
            outputView.printCards(user);
        }
    }

    private void giveMoreCard(){
        for (User user : userList) {
            user.addCardIfWant(deck);
        }
    }
}
