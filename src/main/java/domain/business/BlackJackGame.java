/*
 * @(#)BlackJackGame.java       1.1 2019.12.16
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
import java.util.InputMismatchException;
import java.util.List;

/**
 * 블랙잭 게임을 진행하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 1.1 2019.12.16
 */
public class BlackJackGame {
    /**
     * Dealer와 관련된 정보를 출력할 때 이름으로 사용할 상수.
     */
    private static final String DEALER_NAME = "Dealer";

    /**
     * 처음 변수들을 0으로 초기화 하기 위한 상수.
     */
    private static final double INIT_TO_ZERO = 0;

    /**
     * 처음에 deck에서 card를 2개씩 뽑을 때 사용할 상수.
     */
    private static final int DRAW_TWICE = 2;

    /**
     * Player가 card를 더 받는 응답임을 확인하기 위한 상수.
     */
    private static final String GET_MORE_CARD = "y";

    /**
     * Dealer가 계속 card를 뽑아야 하는 점수인지 판단하기 위한 상수.
     */
    private static final int DRAW_CONTINUE_SCORE = 16;

    /**
     * 무승부일때 수익을 계산하기 위한 상수.
     */
    private static final double DRAW_EARNING_RATE = 1.;

    /**
     * 승리시 수익을 계산하기 위한 상수.
     */
    private static final double WIN_EARNING_RATE = 2.;

    /**
     * 블랙잭의 수익을 계산하기 위한 상수.
     */
    private static final double BLACK_JACK_EARNING_RATE = 2.5;

    /**
     * 입력과 관련된 기능을 담당할 Input 객체.
     */
    private Input in = new Input();

    /**
     * 출력과 관련된 기능을 담당할 Output 객체.
     */
    private Output out = new Output();

    /**
     * Dealer와 Player를 User로 함께 접근하기 위한 User List.
     */
    private List<User> users;

    /**
     * 생성된 Player들을 저장할 Player List.
     */
    private List<Player> players;

    /**
     * Dealer의 수익을 저장할 변수.
     */
    private double dealerProfit;

    /**
     * Player의 수익을 저장할 Double List.
     */
    private List<Double> playerProfits;

    /**
     * 게임에 참여하는 Dealer 객체.
     */
    private Dealer dealer;

    /**
     * card를 생성하고 담당할 Deck 객체.
     */
    private Deck deck;

    /**
     * 블랙잭 게임을 생성하는 생성자.
     */
    public BlackJackGame() {
        players = new ArrayList<>();
        playerProfits = new ArrayList<>();
        dealer = new Dealer();
        deck = new Deck();
        dealerProfit = INIT_TO_ZERO;
    }

    /**
     * Player의 이름과 배팅 머니로 Player를 생성하여 Player List에 추가하고, 배팅 금액을 딜러의 수익으로 들어가게 하는 메소드.
     *
     * @param userName     Player의 이름.
     * @param bettingMoney Player의 배팅 머니.
     */
    public void generatePlayerInstance(String userName, double bettingMoney) {
        players.add(new Player(userName, bettingMoney));
        dealerProfit += bettingMoney;
    }

    /**
     * 블랙잭 게임을 순차적으로 진행하기 위한 메소드.
     */
    public void playBlackJackGame() {
        setUpBlackJackGame();
        proceedWholeDrawProcessInBlackJackGame();
        printBlackJackGameFinalResult();
    }

    /**
     * 블랙잭 게임을 진행하는데 필요한 기본 셋업(User List 생성, playerProfit 초기화)을 진행하는 메소드.
     */
    private void setUpBlackJackGame() {
        combineDealerAndPlayerToUser();
        initPlayerProfits();
    }

    /**
     * Dealer와 Player를 한번에 관리하기 위한 User List를 생성하는 메소드.
     */
    private void combineDealerAndPlayerToUser() {
        users = new ArrayList<>();

        users.add(dealer);
        users.addAll(players);
    }

    /**
     * Player들의 수익을 -배팅 금액으로 초기화하는 메소드.
     */
    private void initPlayerProfits() {
        for (Player player : players) {
            playerProfits.add(-player.getBettingMoney());
        }
    }

    /**
     * 블랙잭 게임에서 카드를 뽑는 모든 과정을 진행하는 메소드.
     */
    private void proceedWholeDrawProcessInBlackJackGame() {
        drawTwoCardsUser();
        drawMoreCardPlayers();
        drawDealerAccordingRule();
    }

    /**
     * Dealer와 Player들이 처음 2장의 card를 뽑고 현재 card 상태를 출력하는 메소드.
     * 이때, Dealer는 처음 뽑은 1장의 card만 출력.
     */
    public void drawTwoCardsUser() {
        out.printDrawFirstTwoCards(StringUtil.joinPlayerName(players));
        for (User user : users) {
            drawTwoCardsUser(user);
            user.printCurrentCardStatus();
        }
        out.printNewLine();
    }

    /**
     * User(Dealer와 Player)에게 2장의 card를 뽑도록 하는 메소드.
     */
    private void drawTwoCardsUser(User user) {
        for (int i = 0; i < DRAW_TWICE; i++) {
            user.addCard(deck.drawCard());
        }
    }

    /**
     * Player들에게 card를 더 뽑을지 물어보는 메소드.
     */
    public void drawMoreCardPlayers() {
        for (Player player : players) {
            drawMoreCardOnePlayer(player);
        }
    }

    /**
     * Player에게 card를 더 뽑을지 물어보고 y이면 card를 추가하고 다시 물어보는 메소드.
     *
     * @param player card를 뽑을 Player
     * @return card를 더 이상 뽑지 않을 경우(n일 경우) false 반환.
     */
    private boolean drawMoreCardOnePlayer(Player player) {
        out.printPlayerGetOneMoreCard(player.getName());
        if (receivePlayerGetMoreCardReply().equals(GET_MORE_CARD)) {
            player.addCard(deck.drawCard());
            player.printCurrentCardStatus();
            return drawMoreCardOnePlayer(player);
        }
        return false;
    }

    /**
     * Player에게 card를 추가로 받을지 여부를 입력받는 메소드.
     *
     * @return card를 추가로 받을지에 대한 응답(y 또는 n).
     */
    private String receivePlayerGetMoreCardReply() {
        String userReply;

        try {
            userReply = in.receivePlayerGetMoreCardInput();
            Validator.isValidGetMoreCardReply(userReply);
        } catch (InputMismatchException e) {
            out.printInputRequestAgain();
            return receivePlayerGetMoreCardReply();
        }
        return userReply;
    }

    /**
     * Dealer의 총점이 17이 안될경우 규칙에 따라 계속 card를 뽑도록 하는 메소드.
     */
    private void drawDealerAccordingRule() {
        out.printNewLine();
        if (dealer.getScore() <= DRAW_CONTINUE_SCORE) {
            drawUntilOverSixteen();
            out.printNewLine();
        }
    }

    /**
     * Dealer 총점이 16점을 초과할 때까지 card를 뽑아 점수를 반환하는 메소드.
     */
    private void drawUntilOverSixteen() {
        if (dealer.getScore() <= DRAW_CONTINUE_SCORE) {
            out.printDealerCardLessThanSeventeen();
            dealer.addCard(deck.drawCard());
            drawUntilOverSixteen();
        }
    }

    /**
     * User의 최종 card 결과와 수익을 계산하여 출력하는 메소드.
     */
    private void printBlackJackGameFinalResult() {
        printFinalUserCardStatus();
        calculateFinalUserProfit();
        printFinalBlackJackGameResult();
    }

    /**
     * User(Dealer와 Player)들의 최종 결과를 출력하는 메소드.
     */
    private void printFinalUserCardStatus() {
        for (User user : users) {
            user.printFinalCardStatus();
        }
    }

    /**
     * Dealer와 Player들의 최종 수익을 계산하는 메소드.
     */
    private void calculateFinalUserProfit() {
        if (dealer.isBust()) {
            calculateDealerIsBust();
            return;
        }
        calculateDealerIsNotBust();
    }

    /**
     * Dealer가 버스트(총합이 21을 초과)일 경우 각 Player들의 수익을 계산하는 메소드.
     */
    private void calculateDealerIsBust() {
        for (Player player : players) {
            setPlayerProfitNotBust(player);
        }
    }

    /**
     * Dealer가 버스트(총합이 21을 초과)일 때, Player가 버스트인지, 블랙잭(처음 2장의 care의 총합이 21)인지, 승리인지에 따라 수익을 계산하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void setPlayerProfitNotBust(Player player) {
        if (player.isBust()) {
            return;
        }
        if (player.isBlackJack()) {
            setBlackJackPlayerProfit(player);
            return;
        }
        setWinnerPlayerProfit(player);
    }

    /**
     * Dealer가 버스트(총합이 21을 초과)가 아닌 경우, Dealer가 블랙잭(처음 2장의 care의 총합이 21)일 때와 아닐 떄를 구분하여 수익을 계산하는 메소드.
     */
    private void calculateDealerIsNotBust() {
        if (dealer.isBlackJack()) {
            calculateDealerIsBlackJack();
            return;
        }
        calculateProfit();
    }

    /**
     * Dealer가 블랙잭(처음 2장의 care의 총합이 21)일 경우 수익을 계산하는 메소드.
     */
    private void calculateDealerIsBlackJack() {
        for (Player player : players) {
            setDealerBlackJackPlayerProfit(player);
        }
    }

    /**
     * Dealer가 블랙잭(처음 2장의 care의 총합이 21)일때, Player가 블랙잭이면 무승부이므로 수익을 계산하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void setDealerBlackJackPlayerProfit(Player player) {
        if (player.isBlackJack()) {
            setDrawPlayerProfit(player);
        }
    }

    /**
     * Dealer가 블랙잭(처음 2장의 care의 총합이 21)이 아니면, 서로 점수를 비교하여 수익을 계산하는 메소드.
     */
    private void calculateProfit() {
        for (Player player : players) {
            setPlayerProfit(player);
        }
    }

    /**
     * Player가 버스트(총합이 21을 초과)인 경우, 블랙잭(처음 2장의 care의 총합이 21)인 경우와 아닌 경우를 나눠서 수익을 계산하는 메소드.
     */
    private void setPlayerProfit(Player player) {
        if (player.isBust()) {
            return;
        }
        if (player.isBlackJack()) {
            setBlackJackPlayerProfit(player);
            return;
        }
        setPlayerWinOrDrawProfit(player);
    }

    /**
     * Player가 승리인지 무승부인지 확인해서 수익을 계산하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void setPlayerWinOrDrawProfit(Player player) {
        int playerScore = player.getScore();
        if (playerScore > dealer.getScore()) {
            setWinnerPlayerProfit(player);
            return;
        }
        if (playerScore == dealer.getScore()) {
            setDrawPlayerProfit(player);
        }
    }

    /**
     * Player가 블랙잭(처음 2장의 care의 총합이 21)에 해당하는 수익을 얻을 경우를 계산하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void setBlackJackPlayerProfit(Player player) {
        playerProfits.set(players.indexOf(player),
                playerProfits.get(players.indexOf(player)) + player.getBettingMoney() * BLACK_JACK_EARNING_RATE);
        dealerProfit -= (player.getBettingMoney() * BLACK_JACK_EARNING_RATE);
    }

    /**
     * Player가 승리에 해당하는 수익을 얻을 경우를 계산하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void setWinnerPlayerProfit(Player player) {
        playerProfits.set(players.indexOf(player),
                playerProfits.get(players.indexOf(player)) + player.getBettingMoney() * WIN_EARNING_RATE);
        dealerProfit -= (player.getBettingMoney() * WIN_EARNING_RATE);
    }

    /**
     * Player가 무승부에 해당하는 수익을 얻을 경우를 계산하는 메소드.
     *
     * @param player 수익을 계산할 Player.
     */
    private void setDrawPlayerProfit(Player player) {
        playerProfits.set(players.indexOf(player),
                playerProfits.get(players.indexOf(player)) + player.getBettingMoney() * DRAW_EARNING_RATE);
        dealerProfit -= (player.getBettingMoney() * DRAW_EARNING_RATE);
    }

    /**
     * 블랙잭 게임 전체의 수익 결과를 출력하는 메소드.
     */
    public void printFinalBlackJackGameResult() {
        out.printFinalProfitNotice();
        out.printUserFinalProfit(DEALER_NAME, dealerProfit);
        for (int i = 0; i < players.size(); i++) {
            out.printUserFinalProfit(players.get(i).getName(), playerProfits.get(i));
        }
    }
}
