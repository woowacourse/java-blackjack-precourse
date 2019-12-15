/*
 * @(#)Validator.java       0.4 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import domain.ui.Output;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 입력의 유효성 검증을 담당하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.4 2019.12.15
 */
public class Validator {
    /**
     * Player 이름이 영문자로만 이루어져 있는지 확인하기 위한 regex 문자열 상수.
     */
    private static final String PLAYER_NAME_FORMAT = "^[a-zA-Z]*$";

    /**
     * Player BettingMoney의 가능한 최소 금액을 확인하기 위한 상수.
     */
    private static final double MIN_BETTING_MONEY = 0;

    /**
     * 예외 발생시 출력을 담당할 Output 객체.
     */
    private static Output out = new Output();

    /**
     * Player 이름이 유효한지(길이, 형식, 중복) 확인하는 메소드.
     *
     * @param playerNames Player 이름.
     * @throws InputMismatchException Player 이름 중 유효하지 않은 이름이 존재할 시 발생하는 예외.
     */
    public static void isValidPlayerName(List<String> playerNames) throws InputMismatchException {
        try {
            isValidPlayerNameLength(playerNames);
            isValidPlayerNameFormat(playerNames);
            isNoDuplicatePlayerName(playerNames);
        } catch (InputMismatchException e) {
            out.printInputRequestAgain();
            throw new InputMismatchException();
        }
    }

    /**
     * Player 이름의 길이가 유효한지(길이가 1이상) 확인하는 메소드.
     *
     * @param playerNames Player 이름.
     * @throws InputMismatchException Player 이름 중 길이가 유효하지 않은 이름이 존재할 시 발생하는 예외.
     */
    private static void isValidPlayerNameLength(List<String> playerNames) throws InputMismatchException {
        for (String playerName : playerNames) {
            isValidOnePlayerNameLength(playerName);
        }
    }

    /**
     * Player 한명의 이름의 길이가 유효한지 확인하고 메시지를 출력하는 메소드.
     *
     * @param playerName Player 이름(한명).
     * @throws InputMismatchException Player 이름의 길이가 유효하지 않을 경우 발생하는 예외.
     */
    private static void isValidOnePlayerNameLength(String playerName) throws InputMismatchException {
        if (playerName.length() < 1) {
            out.printPlayerNameLengthError();
            throw new InputMismatchException();
        }
    }

    /**
     * Player 이름의 형식이 유효한지(영문자로 구성) 확인하는 메소드.
     *
     * @param playerNames Player 이름.
     * @throws InputMismatchException Player 이름 중 형식이 유효하지 않은 이름이 존재할 시 발생하는 예외.
     */
    private static void isValidPlayerNameFormat(List<String> playerNames) throws InputMismatchException {
        for (String playerName : playerNames) {
            isValidOnePlayerNameFormat(playerName);
        }
    }

    /**
     * Player 한명의 이름의 형식이 유효한지 확인하고 메시지를 출력하는 메소드.
     *
     * @param playerName Player 이름(한명).
     * @throws InputMismatchException Player 이름의 형식이 유효하지 않을 경우 발생하는 예외.
     */
    private static void isValidOnePlayerNameFormat(String playerName) throws InputMismatchException {
        if (!Pattern.matches(PLAYER_NAME_FORMAT, playerName)) {
            out.printPlayerNameFormatError();
            throw new InputMismatchException();
        }
    }

    /**
     * Player 이름 중에 중복이 존재하는지 확인하고 메시지를 출력하는 메소드.
     *
     * @param playerNames Player 이름.
     * @throws InputMismatchException Player 이름 중에 중복이 존재할 경우 발생하는 예외.
     */
    private static void isNoDuplicatePlayerName(List<String> playerNames) throws InputMismatchException {
        HashSet<String> noDuplicatePlayerNames = new HashSet<>(playerNames);
        if (noDuplicatePlayerNames.size() != playerNames.size()) {
            out.printPlayerNameDuplicateError();
            throw new InputMismatchException();
        }
    }

    /**
     * Player의 배팅 금액이 유효한지 확인하는 메소드.
     *
     * @param bettingMoney Player의 배팅 금액.
     * @throws InputMismatchException bettingMoney가 유효하지 않은 경우 발생하는 예외.
     */
    public static void isValidPlayerBettingMoney(double bettingMoney) throws InputMismatchException {
        try {
            isValidBettingMoney(bettingMoney);
        } catch (InputMismatchException e) {
            out.printInputRequestAgain();
            throw new InputMismatchException();
        }
    }

    /**
     * 배팅 금액이 최소 금액보다 높은지 확인하고 메시지를 출력하는 메소드.
     *
     * @param bettingMoney Player의 배팅 금액.
     * @throws InputMismatchException bettingMoney가 최소 금액보다 작다면 발생하는 예외.
     */
    private static void isValidBettingMoney(double bettingMoney) throws InputMismatchException {
        if (!(bettingMoney > MIN_BETTING_MONEY)) {
            out.printBettingMoneyUnderMinNumberError();
            throw new InputMismatchException();
        }
    }
}
