/*
 * @(#)BlackJackGame.java       0.5 2019.12.15
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
 * @version 0.5 2019.12.15
 */
public class BlackJackGame {
    /**
     * 첫 턴에 덱에서 카드를 2개씩 뽑을 때 사용할 상수.
     */
    private static final int DRAW_TWICE = 2;

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
    }

    /**
     * Player 이름과 배팅 머니로 Player를 생성하여 List에 추가하는 메소드.
     *
     * @param userName     Player 이름.
     * @param bettingMoney 해당 Player의 배팅 머니.
     */
    public void generatePlayerInstance(String userName, double bettingMoney) {
        players.add(new Player(userName, bettingMoney));
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
        if (receivePlayerGetMoreCardReply().equals("y")) {
            player.addCard(deck.drawCard());
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
            return receivePlayerGetMoreCardReply();
        }
        return userReply;
    }
}
