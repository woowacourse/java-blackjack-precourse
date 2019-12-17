/*
 * @(#)BlackJackGame.java       1.3 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import domain.card.Deck;
import domain.ui.Input;
import domain.ui.Output;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

/**
 * 블랙잭 게임을 진행하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 1.3 2019.12.17
 */
public class BlackJackGame {
    /**
     * Dealer와 관련된 정보를 출력할 때 Dealer의 이름으로 사용할 상수.
     */
    private static final String DEALER_NAME = "Dealer";

    /**
     * Player가 card를 더 받는다는 응답을 했는지 확인하기 위한 상수.
     */
    private static final String DRAW_MORE_CARD_REPLY = "y";

    /**
     * 처음에 deck에서 card를 2장씩 뽑을 때 사용할 상수.
     */
    private static final int FIRST_TWO_DRAW = 2;

    /**
     * 입력과 관련된 기능을 담당할 Input 객체.
     */
    private Input in = new Input();

    /**
     * 출력과 관련된 기능을 담당할 Output 객체.
     */
    private Output out = new Output();

    /**
     * 게임에 참여하는 Dealer 객체.
     */
    private Dealer dealer = new Dealer();

    /**
     * 생성된 Player들을 저장할 Player 객체 List.
     */
    private List<Player> players = new ArrayList<>();

    /**
     * Dealer와 Player를 상위 클래스인 User로 함께 접근하기 위한 User 객체 List.
     */
    private List<User> users = new ArrayList<>();

    /**
     * 게임을 진행하는 동안 사용할 card들을 생성하고 관리하는 Deck 객체.
     */
    private Deck deck = new Deck();

    /**
     * Player의 이름과 배팅 머니로 Player를 생성하여 Player List에 추가하는 메소드.
     *
     * @param userName     Player의 이름.
     * @param bettingMoney Player의 배팅 머니.
     */
    public void generatePlayer(String userName, double bettingMoney) {
        players.add(new Player(userName, bettingMoney));
    }

    /**
     * 블랙잭 게임을 시작하면서 처음 2장의 카드를 각각 뽑는 과정을 진행하는 메소드.
     */
    public void progressFirstDrawTwoCardsEachUsers() {
        integrateDealerAndPlayersIntoUsers();
        drawTwoCardsEachUsers();
    }

    /**
     * Dealer와 Player들을 한번에 관리하기 위해 User 객체 List에 추가하는 메소드.
     */
    private void integrateDealerAndPlayersIntoUsers() {
        users.add(dealer);
        users.addAll(players);
    }

    /**
     * User(Dealer 또는 Player)들이 처음 2장의 card를 뽑는 메소드.
     */
    private void drawTwoCardsEachUsers() {
        out.printFirstDrawTwoCards(StringUtil.joinPlayersName(players));
        for (User user : users) {
            drawTwoCardsUser(user);
        }
    }

    /**
     * User에게 2장의 card를 뽑도록 하는 메소드.
     */
    private void drawTwoCardsUser(User user) {
        for (int i = 0; i < FIRST_TWO_DRAW; i++) {
            user.addCard(deck.drawCard());
        }
    }

    /**
     * Dealer가 블랙잭(처음 2장의 care의 총점이 21)인지 확인하고, 블랙잭이면 Dealer가 블랙잭이라는 출력문도 함께 출력하는 메소드.
     *
     * @return Dealer가 블랙잭이면 true 반환.
     */
    public boolean isDealerBlackJack() {
        if (dealer.isBlackJack()) {
            out.printDealerIsBlackJack();
            out.printNewLine();
            return true;
        }
        return false;
    }

    /**
     * Dealer가 블랙잭이 아닌 경우, 현재까지의 card들을 출력하고 지정된 규칙에 따라 게임을 계속 진행하는 메소드.
     */
    public void restartGameAccordingToRules() {
        printUsersCurrentCardsStatus();
        drawMoreCardPlayers();
        drawMoreCardDealerAccordingRule();
        out.printNewLine();
    }

    /**
     * Dealer가 BlackJack이 아니라서 게임을 다시 진행할 경우, User들의 현재 card들을 출력하는 메소드.
     */
    private void printUsersCurrentCardsStatus() {
        for (User user : users) {
            user.printCurrentCardStatus();
        }
        out.printNewLine();
    }

    /**
     * Player들에게 card를 더 뽑을지 물어보는 메소드.
     */
    private void drawMoreCardPlayers() {
        for (Player player : players) {
            drawMoreCardIfNotBust(player);
        }
        out.printNewLine();
    }

    /**
     * Player가 버스트(총점이 21을 초과)가 되면 버스트가 되었다는 메시지를 출력하고 더 이상 뽑지 못하도록 하는 메소드.
     *
     * @param player 카드를 뽑을 수 있는 지 확인할 Player.
     */
    private void drawMoreCardIfNotBust(Player player) {
        if (player.isBust()) {
            out.printPlayerIsBust(player.getName());
            return;
        }
        drawMoreCardPlayer(player);
    }

    /**
     * Player에게 card를 더 뽑을지 물어보고 y이면 card를 추가하고 다시 물어보는 메소드.
     *
     * @param player card를 뽑을 Player
     */
    private void drawMoreCardPlayer(Player player) {
        out.printDrawOneMoreCardPlayer(player.getName());
        if (receiveDrawOneMoreCardReply().equals(DRAW_MORE_CARD_REPLY)) {
            player.addCard(deck.drawCard());
            player.printCurrentCardStatus();
            drawMoreCardIfNotBust(player);
        }
    }

    /**
     * Player에게 card를 추가로 받을지 여부를 입력받는 메소드.
     *
     * @return card를 추가로 받을지에 대한 응답(y 또는 n).
     */
    private String receiveDrawOneMoreCardReply() {
        try {
            String userReply = in.receivePlayerGetMoreCardInput();

            Validator.isValidGetMoreCardReply(userReply);
            return userReply;
        } catch (InputMismatchException e) {
            out.printInputRequestAgain();
            return receiveDrawOneMoreCardReply();
        }
    }

    /**
     * Dealer의 총점이 17이 안될 경우 규칙에 따라 계속 card를 뽑도록 하는 메소드.
     */
    private void drawMoreCardDealerAccordingRule() {
        if (dealer.isDrawContinue()) {
            out.printDrawMoreCardDealerAccordingToRule();
            dealer.addCard(deck.drawCard());
            drawMoreCardDealerAccordingRule();
        }
    }

    /**
     * User들의 최종 card들을 출력하는 메소드.
     */
    public void printUsersFinalCardStatus() {
        for (User user : users) {
            user.printFinalCardStatus();
        }
    }

    /**
     * 블랙잭 게임 전체 User들의 수익 결과를 계산하여 출력하는 메소드.
     */
    public void printUsersFinalProfitResult() {
        ProfitCalculator profitCalculator = new ProfitCalculator(dealer, players);
        HashMap<String, Double> usersProfit = profitCalculator.calculateUsersFinalProfit();

        out.printFinalProfitNotice();
        out.printUserFinalProfit(DEALER_NAME, usersProfit.get(DEALER_NAME));
        for (Player player : players) {
            out.printUserFinalProfit(player.getName(), usersProfit.get(player.getName()));
        }
    }
}
