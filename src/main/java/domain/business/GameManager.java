/*
 * @(#)GameManager.java     0.2 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import domain.ui.Input;
import domain.ui.Output;

import java.util.InputMismatchException;
import java.util.List;

/**
 * 게임의 진행을 관리하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.2 2019.12.15
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
     * 카드 생성시 예외를 처리하기 위한 기본 생성자.
     *
     * @throws Exception 카드 생성에 예외가 발생할 경우 생기는 예외.
     */
    public GameManager() throws Exception {}

    /**
     * 블랙잭 게임을 진행할 BlackJackGame 인스턴스를 생성하고 Player 객체를 게임 안에 생성하고 게임을 시작하는 메소드.
     *
     * @throws Exception 카드 생성에 예외가 발생할 경우 생기는 예외.
     */
    public void run() throws Exception {
        blackJackGame = new BlackJackGame();
        putPlayerInBlackJackGame(receivePlayerName());
        blackJackGame.playBlackJackGame();
    }

    /**
     * 게임 참여자 이름을 입력받아 유효성을 검사 후 반환하는 메소드.
     *
     * @return Player 이름 List.
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
     * @param playerName Player 이름 List.
     * @return 해당 Player의 배팅 금액.
     */
    private double receivePlayerBettingMoney(String playerName) {
        double playerBettingMoney;

        try {
            out.printPlayerBettingMoneyRequest(playerName);
            playerBettingMoney = in.receivePlayerBettingMoneyInput();
            Validator.isValidPlayerBettingMoney(playerBettingMoney);
        } catch (InputMismatchException e) {
            return receivePlayerBettingMoney(playerName);
        }
        return playerBettingMoney;
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
