/*
 * @(#)Application.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

import java.util.Scanner;

import api.UserApi;
import domain.Blackjack;
import domain.BlackjackPrinter;
import domain.UserInterface;
import domain.card.Deck;
import domain.card.SingleDeck;
import domain.user.*;
import ui.Console;
import util.BlackjackPrinterImpl;



public class Application {
    public static void main(String[] args) {

        Deck deck = new SingleDeck();
        BlackjackPrinter blackjackPrinter = new BlackjackPrinterImpl();
        UserInterface userInterface = new Console(new Scanner(System.in), blackjackPrinter);
        PlayerFactory playerFactory = new PlayerFactory();
        PlayerServiceImpl playerService = new PlayerServiceImpl(deck, blackjackPrinter, userInterface, playerFactory);

        DealerServiceImpl dealerService = new DealerServiceImpl(deck, blackjackPrinter);
        Dealer dealer = new Dealer();

        UserApi userApi = new UserApi(dealerService, playerService, blackjackPrinter);

        Blackjack blackjack = new Blackjack(userApi, dealer);

        blackjack.play();
    }
}
