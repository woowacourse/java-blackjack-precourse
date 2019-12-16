/*
 * @(#)GameManager.java     0.3 2019.12.16
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
 * @version 0.3 2019.12.16
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
     * BlackJackGame을 진행할 객체.
     */
    private BlackJackGame blackJackGame;

    /**
     * 블랙잭 게임을 진행할 BlackJackGame 인스턴스를 생성하고 Player 객체를 게임 안에 생성하고 게임을 시작하는 메소드.
     */
    public void run() {
        blackJackGame = new BlackJackGame();
        putPlayerInBlackJackGame(receivePlayerName());
        blackJackGame.playBlackJackGame();
    }

    /**
     * 게임 참여자 이름을 입력받아 유효성을 검사 후 반환하는 메소드.
     *
     * @return Player들의 이름.
     */
    private List<String> receivePlayerName() {
        List<String> playerNames;

        try {
            out.printPlayerNameInputRequest();
            playerNames = StringUtil.processPlayerName(in.receivePlayerNameInput());
            Validator.isValidPlayerName(playerNames);
        } catch (InputMismatchException e) {
            return receivePlayerName();
        }
        return playerNames;
    }

    /**
     * Player의 배팅 금액을 입력받아 유효성을 검사 후 반환하는 메소드.
     *
     * @param playerName Player들의 이름.
     * @return Player의 배팅 금액.
     */
    private double receivePlayerBettingMoney(String playerName) {
        try {
            out.printPlayerBettingMoneyRequest(playerName);
            double playerBettingMoney = in.receivePlayerBettingMoneyInput();
            Validator.isValidPlayerBettingMoney(playerBettingMoney);
            return playerBettingMoney;
        } catch (InputMismatchException e) {
            out.printBettingMoneyUnderMinNumberError();
            out.printInputRequestAgain();
            return receivePlayerBettingMoney(playerName);
        }
    }

    /**
     * Player 각각 배팅 금액을 입력받아 해당 플레이어를 BlackJackGame에 참가시키는 메소드.
     *
     * @param playerNames Player 이름.
     */
    private void putPlayerInBlackJackGame(List<String> playerNames) {
        for (String playerName : playerNames) {
            blackJackGame.generatePlayerInstance(playerName, receivePlayerBettingMoney(playerName));
        }
    }
}
