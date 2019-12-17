/*
 * @(#)BlackJackGame.java       1.5 2019.12.17
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
 * @version 1.5 2019.12.17
 */
public class BlackJackGame {
    /**
     * Dealer와 관련된 정보를 출력할 때 Dealer의 이름으로 사용할 상수.
     */
    private static final String DEALER_NAME = "Dealer";

    /**
     * 처음에 deck에서 card를 2장씩 뽑을 때 사용할 상수.
     */
    private static final int NUMBER_OF_FIRST_DRAW = 2;

    /**
     * Player가 card를 더 받는다는 응답을 했는지 확인하기 위한 상수.
     */
    private static final String DRAW_ONE_MORE_CARD_REPLY = "y";

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
     * @param playerName   Player의 이름.
     * @param bettingMoney Player의 배팅 머니.
     */
    public void generateSelectedPlayer(String playerName, double bettingMoney) {
        players.add(new Player(playerName, bettingMoney));
    }

    /**
     * User 객체 List를 만들고 User(Dealer 또는 Player)들에게 처음 2장의 card를 뽑게 하는 메소드.
     */
    public void drawTwoCardsEachUsers() {
        integrateDealerAndPlayersIntoUsers();
        out.printFirstDrawTwoCards(StringUtil.joinPlayerNames(players));
        for (User user : users) {
            drawTwoCardsSelectedUser(user);
        }
    }

    /**
     * Dealer와 Player들을 한번에 관리하기 위해 User 객체 List에 추가하는 메소드.
     */
    private void integrateDealerAndPlayersIntoUsers() {
        users.add(dealer);
        users.addAll(players);
    }

    /**
     * User에게 2장의 card를 뽑도록 하는 메소드.
     */
    private void drawTwoCardsSelectedUser(User user) {
        for (int i = 0; i < NUMBER_OF_FIRST_DRAW; i++) {
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
    public void restartGameAccordingToRule() {
        printUsersCurrentCardsStatus();
        drawMoreCardsPlayers();
        drawMoreCardsDealerAccordingToRule();
        out.printNewLine();
    }

    /**
     * Dealer가 BlackJack이 아니라서 게임을 다시 진행할 경우, User들의 현재 card들을 출력하는 메소드.
     */
    private void printUsersCurrentCardsStatus() {
        for (User user : users) {
            user.printCurrentCardsStatus();
        }
        out.printNewLine();
    }

    /**
     * Player들에게 card를 더 뽑을지 물어보는 메소드.
     */
    private void drawMoreCardsPlayers() {
        for (Player player : players) {
            drawMoreCardsIfNotBust(player);
        }
        out.printNewLine();
    }

    /**
     * Player가 버스트(총점이 21을 초과)가 되면 버스트가 되었다는 메시지를 출력하고 더 이상 뽑지 못하도록 하는 메소드.
     *
     * @param player 카드를 뽑을 수 있는 지 확인할 Player.
     */
    private void drawMoreCardsIfNotBust(Player player) {
        if (player.isBust()) {
            out.printPlayerIsBust(player.getName());
            return;
        }
        drawMoreCardsSelectedPlayer(player);
    }

    /**
     * Player에게 card를 더 뽑을지 물어보고 y이면 card를 추가하고 다시 물어보는 메소드.
     *
     * @param player card를 뽑을 Player
     */
    private void drawMoreCardsSelectedPlayer(Player player) {
        out.printDrawOneMoreCardSelectedPlayer(player.getName());
        if (receiveDrawOneMoreCardReply().equals(DRAW_ONE_MORE_CARD_REPLY)) {
            player.addCard(deck.drawCard());
            player.printCurrentCardsStatus();
            drawMoreCardsIfNotBust(player);
        }
    }

    /**
     * Player에게 card를 추가로 받을지 여부를 입력받는 메소드.
     *
     * @return card를 추가로 받을지에 대한 응답(y 또는 n).
     */
    private String receiveDrawOneMoreCardReply() {
        try {
            String playerReply = in.receiveDrawOneMoreCardInput();

            Validator.isValidDrawOneMoreCardReply(playerReply);
            return playerReply;
        } catch (InputMismatchException e) {
            out.printInputRequestAgain();
            return receiveDrawOneMoreCardReply();
        }
    }

    /**
     * Dealer의 총점이 17이 안될 경우 규칙에 따라 계속 card를 뽑도록 하는 메소드.
     */
    private void drawMoreCardsDealerAccordingToRule() {
        if (dealer.isDealerDrawContinue()) {
            out.printDrawOneMoreCardDealerAccordingToRule();
            dealer.addCard(deck.drawCard());
            drawMoreCardsDealerAccordingToRule();
        }
    }

    /**
     * User들의 최종 card들을 출력하는 메소드.
     */
    public void printUsersFinalCardsStatus() {
        for (User user : users) {
            user.printFinalCardsStatus();
        }
    }

    /**
     * 블랙잭 게임 전체 User들의 수익 결과를 계산하여 출력하는 메소드.
     */
    public void printUsersFinalProfitResult() {
        ProfitCalculator profitCalculator = new ProfitCalculator(dealer, players);
        HashMap<String, Double> usersProfit = profitCalculator.calculateUsersFinalProfit();

        out.printFinalProfitNotice();
        out.printSelectedUserFinalProfit(DEALER_NAME, usersProfit.get(DEALER_NAME));
        for (Player player : players) {
            out.printSelectedUserFinalProfit(player.getName(), usersProfit.get(player.getName()));
        }
    }
}
