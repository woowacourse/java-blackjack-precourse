/*
 * @(#)Application.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

import java.util.Scanner;

import api.BlackjackApi;
import api.BlackjackApiImpl;
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

    private static PlayerService setupPlayerService(Deck deck, BlackjackPrinter blackjackPrinter) {
        UserInterface userInterface = new Console(new Scanner(System.in), blackjackPrinter);
        PlayerFactory playerFactory = new PlayerFactory();
        return new PlayerServiceImpl(deck, blackjackPrinter, userInterface, playerFactory);
    }

    private static DealerService setupDealerService(Deck deck, BlackjackPrinter blackjackPrinter) {
        return new DealerServiceImpl(deck, blackjackPrinter);
    }

    private static BlackjackApi setupBlackjackApi() {
        Deck deck = new SingleDeck();
        BlackjackPrinter blackjackPrinter = new BlackjackPrinterImpl();
        DealerService dealerService = setupDealerService(deck, blackjackPrinter);
        PlayerService playerService = setupPlayerService(deck, blackjackPrinter);
        return new BlackjackApiImpl(dealerService, playerService, blackjackPrinter);
    }

    private static Blackjack setupBlackjack() {
        BlackjackApi blackjackApi = setupBlackjackApi();
        Dealer dealer = new Dealer();
        return new Blackjack(blackjackApi, dealer);
    }
}
