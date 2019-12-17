/*
 * @(#)GameManager.java     0.6 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import domain.ui.Input;
import domain.ui.Output;

import java.util.InputMismatchException;
import java.util.List;

/**
 * 게임의 진행을 관리하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.6 2019.12.17
 */
public class GameManager {
    /**
     * 입력과 관련된 기능을 담당할 Input 객체.
     */
    private Input in = new Input();

    /**
     * 출력과 관련된 기능을 담당할 Output 객체.
     */
    private Output out = new Output();

    /**
     * 블랙잭 게임을 진행할 BlackJackGame 객체.
     */
    private BlackJackGame blackJackGame;

    /**
     * 블랙잭 게임을 진행할 BlackJackGame 객체를 생성하고 게임을 시작하는 메소드.
     */
    public void run() {
        blackJackGame = new BlackJackGame();

        generatePlayersInBlackJackGame(receivePlayerNames());
        blackJackGame.drawTwoCardsEachUsers();
        if (!blackJackGame.isDealerBlackJack()) {
            blackJackGame.restartGameAccordingToRule();
        }
        blackJackGame.printUsersFinalCardsStatus();
        blackJackGame.printUsersFinalProfitResult();
    }

    /**
     * 게임 참여자 이름을 입력받아 Player들의 이름으로 나누고, 유효성을 검사하여 유효하다면 Player들의 이름을 반환하는 메소드.
     *
     * @return Player들의 이름.
     */
    private List<String> receivePlayerNames() {
        try {
            out.printPlayerNamesInputRequest();
            List<String> playerNames = StringUtil.processPlayerNames(in.receivePlayerNamesInput());

            Validator.isValidPlayerNames(playerNames);
            return playerNames;
        } catch (InputMismatchException e) {
            return receivePlayerNames();
        }
    }

    /**
     * Player들의 배팅 금액을 각각 입력받아 해당 플레이어를 BlackJackGame에 생성하는 메소드.
     *
     * @param playerNames Player 이름.
     */
    private void generatePlayersInBlackJackGame(List<String> playerNames) {
        for (String playerName : playerNames) {
            blackJackGame.generateSelectedPlayer(playerName, receiveBettingMoney(playerName));
        }
    }

    /**
     * Player의 배팅 금액을 입력받아 유효성을 검사 후 반환하는 메소드.
     *
     * @param playerName Player의 이름.
     * @return Player의 배팅 금액.
     */
    private double receiveBettingMoney(String playerName) {
        try {
            out.printBettingMoneyInputRequest(playerName);
            double bettingMoney = in.receiveBettingMoneyInput();

            Validator.isValidBettingMoney(bettingMoney);
            return bettingMoney;
        } catch (InputMismatchException e) {
            out.printBettingMoneyInputException();
            return receiveBettingMoney(playerName);
        }
    }
}
