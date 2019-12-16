/*
 * @(#)BlackJackGame.java       0.9 2019.12.16
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import domain.card.Deck;
import domain.ui.Input;
import domain.ui.Output;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * 블랙잭 게임을 진행하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.9 2019.12.16
 */
public class BlackJackGame {
    /**
     * Dealer와 관련된 정보를 출력할 때 사용할 상수.
     */
    private static final String DEALER_NAME = "딜러";

    /**
     * 첫 턴에 덱에서 카드를 2개씩 뽑을 때 사용할 상수.
     */
    private static final int DRAW_TWICE = 2;

    /**
     * 버스트(21을 초과)하는 상황인지 확인하기 위한 상수.
     */
    private static final int BUST_NUMBER = 22;

    /**
     * 블랙잭의 수익을 계산하기 위한 상수.
     */
    private static final double BLACK_JACK_EARNING_RATE = 2.5;

    /**
     * 승리시 수익을 계산하기 위한 상수.
     */
    private static final double WIN_EARNING_RATE = 2.;

    /**
     * 무승부일때 수익을 계산하기 위한 상수.
     */
    private static final double DRAW_EARNING_RATE = 1.;

    /**
     * 입력과 관련되 기능을 담당할 Input 인스턴스.
     */
    private Input in = new Input();

    /**
     * 출력과 관련된 기능을 담당할 Output 인스턴스.
     */
    private Output out = new Output();

    /**
     * 생성된 Player 인스턴스들을 담을 Player 인스턴스 List.
     */
    private List<Player> players;

    /**
     * Player의 점수를 저장할 Integer List.
     */
    private List<Integer> playerScores;

    /**
     * Player의 수익을 저장할 Double List.
     */
    private List<Double> playerProfits;

    /**
     * Dealer의 수익을 저장할 변수.
     */
    private double dealerProfit;

    /**
     * 게임에서 필요한 Dealer 인스턴스.
     */
    private Dealer dealer;

    /**
     * 카드를 생성하고 담당할 Deck 인스턴스.
     */
    private Deck deck;

    /**
     * 블랙잭 게임을 생성하면 Player List와 Dealer를 생성하는 생성자.
     */
    public BlackJackGame() throws Exception {
        players = new ArrayList<>();
        dealer = new Dealer();
        deck = new Deck();
        dealerProfit = 0;
    }

    /**
     * Player 이름과 배팅 머니로 Player를 생성하여 List에 추가하고, 전체 배팅 금액 합계와 딜러의 수익에도 추가하는 메소드.
     *
     * @param userName     Player 이름.
     * @param bettingMoney 해당 Player의 배팅 머니.
     */
    public void generatePlayerInstance(String userName, double bettingMoney) {
        players.add(new Player(userName, bettingMoney));
        dealerProfit += bettingMoney;
    }

    /**
     * 블랙잭 게임을 순차적으로 진행하기 위한 메소드.
     */
    public void playBlackJackGame() {
        drawTwoCardsDealerAndPlayer();
        drawMoreCardPlayer();
        out.printNewLine();
        dealer.printFinalResult(deck);
        printFinalResultPlayer();
        initPlayerInformation();
        calculateFinalProfit();
        printFinalProfit();
    }

    /**
     * Dealer와 Player가 첫 턴에 2장의 카드를 뽑고 현재 카드 상태를 출력하는 메소드.
     */
    public void drawTwoCardsDealerAndPlayer() {
        out.printHandOutTwoCards(StringUtil.joinPlayerName(players));
        drawTwoCardsDealer();
        dealer.printDealerCurrentCardStatus();
        for (Player player : players) {
            drawTwoCardsPlayer(player);
            player.printPlayerCurrentCardStatus();
        }
    }

    /**
     * Dealer에게 2장의 카드를 뽑도록 하는 메소드.
     */
    private void drawTwoCardsDealer() {
        for (int i = 0; i < DRAW_TWICE; i++) {
            dealer.addCard(deck.drawCard());
        }
    }

    /**
     * Player에게 2장의 카드를 뽑도록 하는 메소드.
     *
     * @param player Player 한명.
     */
    private void drawTwoCardsPlayer(Player player) {
        for (int i = 0; i < DRAW_TWICE; i++) {
            player.addCard(deck.drawCard());
        }
    }

    /**
     * 모든 Player에게 카드를 더 뽑을지 물어보는 메소드.
     */
    public void drawMoreCardPlayer() {
        for (Player player : players) {
            drawMoreCardOnePlayer(player);
        }
    }

    /**
     * Player에게 카드를 더 뽑을지 물어보고 y이면 카드를 추가하고 다시 물어보는 메소드.
     *
     * @param player 카드를 뽑을 Player
     * @return 카드를 더 이상 뽑지 않을 경우(n일 경우) false 반환.
     */
    private boolean drawMoreCardOnePlayer(Player player) {
        out.printPlayerGetOneMoreCard(player.getName());
        if (receivePlayerGetMoreCardReply().equals("y")) {
            player.addCard(deck.drawCard());
            player.printPlayerCurrentCardStatus();
            return drawMoreCardOnePlayer(player);
        }
        return false;
    }

    /**
     * Player에게 카드를 추가로 받을지 여부를 입력받는 메소드.
     *
     * @return 카드를 추가로 받을지에 대한 응답(y 또는 n).
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
     * Player의 최종 결과를 출력하는 메소드.
     */
    private void printFinalResultPlayer() {
        for (Player player : players) {
            player.printFinalResult();
        }
    }

    /**
     * Player의 수익과 총점을 미리 초기화해두는 메소드.
     */
    private void initPlayerInformation() {
        initPlayerProfit();
        getPlayerTotalScores();
    }

    /**
     * Player들의 수익을 -배팅금액으로 초기화하는 메소드.
     */
    private void initPlayerProfit() {
        playerProfits = new ArrayList<>();
        for (Player player : players) {
            playerProfits.add(-player.getBettingMoney());
        }
    }

    /**
     * Player들의 점수를 List에 저장하는 메소드.
     */
    private void getPlayerTotalScores() {
        playerScores = new ArrayList<>();

        for (Player player : players) {
            int playerTotalScore = player.getTotalScore();
            playerScores.add(playerTotalScore);
        }
    }

    /**
     * 최종 수익을 계산하는 메소드.
     */
    private void calculateFinalProfit() {
        int dealerScore = dealer.getTotalScore();
        if (isBust(dealerScore)) {
            calculateDealerIsBust();
            return;
        }
        calculateDealerIsNotBust(dealerScore);
    }

    /**
     * Dealer가 버스트일 경우 각 Player별 수익을 계산하는 메소드.
     */
    private void calculateDealerIsBust() {
        for (int i = 0; i < players.size(); i++) {
            setPlayerProfitNotBust(i);
        }
    }

    /**
     * Dealer가 버스트일때, Player가 버스트만 아니면 승리하고 버스트이면 패배하므로 해당 수익을 계산하는 메소드.
     *
     * @param playerIndex 수익을 계산할 Player의 index.
     */
    private void setPlayerProfitNotBust(int playerIndex) {
        if (isBust(playerScores.get(playerIndex))) {
            return;
        }
        if (players.get(playerIndex).isBlackJack()) {
            setBlackJackPlayerProfit(playerIndex);
            return;
        }
        setWinnerPlayerProfit(playerIndex);
    }

    /**
     * Dealer가 버스트가 아닌 경우, Dealer가 블랙잭일때와, 블랙잭이 아닐 떄를 구분하여 수익을 계산하는 메소드.
     *
     * @param dealerScore Dealer의 점수.
     */
    private void calculateDealerIsNotBust(int dealerScore) {
        if (dealer.isBlackJack()) {
            calculateDealerIsBlackJack();
            return;
        }
        calculateProfit(dealerScore);
    }

    /**
     * Dealer가 블랙잭일 경우 수익을 계산하는 메소드.
     */
    private void calculateDealerIsBlackJack() {
        for (int i = 0; i < players.size(); i++) {
            setPlayerProfit(i);
        }
    }

    /**
     * Dealer가 블랙잭일때, Player가 블랙잭이면 무승부이므로 수익을 계산하는 메소드.
     *
     * @param playerIndex 수익을 계산할 Player의 index.
     */
    private void setPlayerProfit(int playerIndex) {
        if (players.get(playerIndex).isBlackJack()) {
            setDrawPlayerProfit(playerIndex);
        }
    }

    /**
     * Dealer가 블랙잭이 아니면, 서로 점수를 비교하여 수익을 계산하는 메소드.
     *
     * @param dealerScore Dealer의 점수.
     */
    private void calculateProfit(int dealerScore) {
        for (int i = 0; i < players.size(); i++) {
            setPlayerProfit(i, dealerScore);
        }
    }

    /**
     * Player가 버스트이면 패배하고, 점수가 더 높으면 승리, 같으면 무승부로 수익을 계산하는 메소드.
     *
     * @param playerIndex 수익을 계산할 Player의 Index.
     * @param dealerScore Dealer의 점수.
     */
    private void setPlayerProfit(int playerIndex, int dealerScore) {
        if (isBust(playerScores.get(playerIndex))) {
            return;
        }
        if (players.get(playerIndex).isBlackJack()) {
            setBlackJackPlayerProfit(playerIndex);
            return;
        }
        setPlayerWinOrDrawProfit(playerIndex, dealerScore);
    }

    private void setPlayerWinOrDrawProfit(int playerIndex, int dealerScore) {
        if (playerScores.get(playerIndex) > dealerScore) {
            setWinnerPlayerProfit(playerIndex);
            return;
        }
        if (playerScores.get(playerIndex) == dealerScore) {
            setDrawPlayerProfit(playerIndex);
        }
    }

    private void setBlackJackPlayerProfit(int playerIndex) {
        playerProfits.set(playerIndex, players.get(playerIndex).getBettingMoney() * BLACK_JACK_EARNING_RATE);
        dealerProfit -= (players.get(playerIndex).getBettingMoney() * BLACK_JACK_EARNING_RATE);
    }

    /**
     * 승리일 경우 Player의 수익을 설정하는 메소드.
     *
     * @param playerIndex 수익을 설정할 Player의 index.
     */
    private void setWinnerPlayerProfit(int playerIndex) {
        playerProfits.set(playerIndex, players.get(playerIndex).getBettingMoney() * WIN_EARNING_RATE);
        dealerProfit -= (players.get(playerIndex).getBettingMoney() * WIN_EARNING_RATE);
    }

    /**
     * 무승부일 경우 Player의 수익을 설정하는 메소드.
     *
     * @param playerIndex 수익을 설정할 Player의 index.
     */
    private void setDrawPlayerProfit(int playerIndex) {
        playerProfits.set(playerIndex, players.get(playerIndex).getBettingMoney() * DRAW_EARNING_RATE);
        dealerProfit -= (players.get(playerIndex).getBettingMoney() * DRAW_EARNING_RATE);
    }

    /**
     * 해당 점수가 버스트인지 확인하는 메소드.
     *
     * @param userScore 버스트인지 확인할 점수.
     * @return 버스트이면 true 반환.
     */
    private boolean isBust(int userScore) {
        return (userScore >= BUST_NUMBER);
    }

    /**
     * 블랙잭 게임 전체의 수익 결과를 출력하는 메소드.
     */
    public void printFinalProfit() {
        out.printFinalProfitNotice();
        out.printUserFinalProfit(DEALER_NAME, dealerProfit);
        for (int i = 0; i < players.size(); i++) {
            out.printUserFinalProfit(players.get(i).getName(), playerProfits.get(i));
        }
    }
}
