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
        Blackjack blackjack = setupBlackjack();

        try {
            blackjack.play();
        } catch (RuntimeException e) {
            System.out.println(String.format("다음과 같은 이유로 프로그램을 종료합니다: %s", e.getMessage()));
            System.exit(-1);
        }
    }

    private static PlayerServiceImpl setupPlayerService(Deck deck, BlackjackPrinter blackjackPrinter) {
        UserInterface userInterface = new Console(new Scanner(System.in), blackjackPrinter);
        PlayerFactory playerFactory = new PlayerFactory();
        return new PlayerServiceImpl(deck, blackjackPrinter, userInterface, playerFactory);
    }

    private static DealerServiceImpl setupDealerService(Deck deck, BlackjackPrinter blackjackPrinter) {
        return new DealerServiceImpl(deck, blackjackPrinter);
    }

    private static UserApi setupUserApi() {
        Deck deck = new SingleDeck();
        BlackjackPrinter blackjackPrinter = new BlackjackPrinterImpl();
        DealerServiceImpl dealerService = setupDealerService(deck, blackjackPrinter);
        PlayerServiceImpl playerService = setupPlayerService(deck, blackjackPrinter);
        return new UserApi(dealerService, playerService, blackjackPrinter);
    }

    private static Blackjack setupBlackjack() {
        UserApi userApi = setupUserApi();
        Dealer dealer = new Dealer();
        return new Blackjack(userApi, dealer);
    }
}
