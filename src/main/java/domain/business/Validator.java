/*
 * @(#)Validator.java       1.0 2019.12.17
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
 * 입력의 유효성 검증을 담당하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 1.0 2019.12.17
 */
public class Validator {
    /**
     * Dealer와 관련된 정보를 출력할 때 Dealer의 이름으로 사용할 상수.
     */
    private static final String DEALER_NAME = "Dealer";

    /**
     * Player의 이름이 영문자로만 이루어져 있는지 확인하기 위한 regex 문자열 상수.
     */
    private static final String PLAYER_NAME_FORMAT = "^[a-zA-Z]*$";

    /**
     * Player의 배팅 금액으로 가능한 최소 금액을 확인하기 위한 상수.
     */
    private static final double MIN_BETTING_MONEY = 0;

    /**
     * Player의 이름의 길이가 유효한지 확인하기 위한 상수.
     */
    private static final int MIN_PLAYER_NAME_LENGTH = 0;

    /**
     * Player가 card를 더 받는 응답을 했는지 확인하기 위한 상수.
     */
    private static final String GET_MORE_CARD = "y";

    /**
     * Player가 card를 더 이상 받지 않겠다는 응답을 했는지 확인하기 위한 상수.
     */
    private static final String STOP_GET_MORE_CARD = "n";

    /**
     * 예외 발생시 출력을 담당할 Output 객체.
     */
    private static Output out = new Output();

    /**
     * Player들의 이름이 유효한지(길이, 형식, 중복) 확인하고　예외를　발생시키는 메소드.
     *
     * @param playerNames Player들의 이름.
     * @throws InputMismatchException Player들의 이름 중 유효하지 않은 이름이 존재할 시 발생하는 예외.
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
     * Player들의 이름의 길이가 유효한지(길이가 1이상) 확인하여　예외를　발생시키는 메소드.
     *
     * @param playerNames Player들의 이름.
     * @throws InputMismatchException Player들의 이름 중 길이가 유효하지 않은 이름이 존재할 시 발생하는 예외.
     */
    private static void isValidPlayerNameLength(List<String> playerNames) throws InputMismatchException {
        for (String playerName : playerNames) {
            isValidOnePlayerNameLength(playerName);
        }
    }

    /**
     * Player의 이름의 길이가 유효한지 확인하여　예외를　발생시키고 메시지를 출력하는 메소드.
     *
     * @param playerName Player의 이름.
     * @throws InputMismatchException Player의 이름의 길이가 유효하지 않을 경우 발생하는 예외.
     */
    private static void isValidOnePlayerNameLength(String playerName) throws InputMismatchException {
        if (playerName.length() == MIN_PLAYER_NAME_LENGTH) {
            out.printPlayerNameLengthException();
            throw new InputMismatchException();
        }
    }

    /**
     * Player들의 이름의 형식이 유효한지(영문자로 구성) 확인하고　예외를　발생시키는 메소드.
     *
     * @param playerNames Player들의 이름.
     * @throws InputMismatchException Player들의 이름 중 형식이 유효하지 않은 이름이 존재할 시 발생하는 예외.
     */
    private static void isValidPlayerNameFormat(List<String> playerNames) throws InputMismatchException {
        for (String playerName : playerNames) {
            isValidOnePlayerNameFormat(playerName);
        }
    }

    /**
     * Player의 이름의 형식이 유효한지 확인하여　예외를　발생시키고 메시지를 출력하는 메소드.
     *
     * @param playerName Player의 이름.
     * @throws InputMismatchException Player의 이름의 형식이 유효하지 않을 경우 발생하는 예외.
     */
    private static void isValidOnePlayerNameFormat(String playerName) throws InputMismatchException {
        if (!Pattern.matches(PLAYER_NAME_FORMAT, playerName)) {
            out.printPlayerNameFormatException();
            throw new InputMismatchException();
        }
    }

    /**
     * Player들의 이름 중에 중복이 존재하는지, Dealer 또는 dealer라는 이름을 사용하는지 확인하여　예외를　발생시키고 메시지를 출력하는 메소드.
     *
     * @param playerNames Player들의 이름.
     * @throws InputMismatchException Player들의 이름 중에 중복 또는 Dealer 또는 dealer라는 이름이 존재할 경우 발생하는 예외.
     */
    private static void isNoDuplicatePlayerName(List<String> playerNames) throws InputMismatchException {
        HashSet<String> noDuplicatePlayerNames = new HashSet<>(playerNames);

        if (noDuplicatePlayerNames.size() != playerNames.size()) {
            out.printPlayerNameDuplicateException();
            throw new InputMismatchException();
        }
        if (isDealerNameIncluded(playerNames)) {
            out.printPlayerNameDuplicateWithDealerException();
            throw new InputMismatchException();
        }
    }

    /**
     * Player들의 이름　중에 Dealer 또는 dealer라는 이름이 존재하는 지 확인하고　예외를　발생시키는 메소드.
     *
     * @param playerNames Player들의 이름.
     * @return Dealer 또는 dealer라는 이름이 존재하면 true 반환.
     * @throws InputMismatchException Player들의 이름중에 Dealer 또는 dealer라는 이름이 존재하면 발생하는 예외.
     */
    private static boolean isDealerNameIncluded(List<String> playerNames) throws InputMismatchException {
        return (playerNames.contains(DEALER_NAME) || playerNames.contains(DEALER_NAME.toLowerCase()));
    }

    /**
     * Player의 배팅 금액이 유효한지 확인하고　예외를　발생시키는 메소드.
     *
     * @param bettingMoney Player의 배팅 금액.
     * @throws InputMismatchException 배팅 금액이 유효하지 않은 경우 발생하는 예외.
     */
    public static void isValidBettingMoney(double bettingMoney) throws InputMismatchException {
        try {
            isValidBettingMoneyAmount(bettingMoney);
        } catch (InputMismatchException e) {
            throw new InputMismatchException();
        }
    }

    /**
     * 배팅 금액이 최소 금액보다 높은지 확인하여　예외를　발생시키고 메시지를 출력하는 메소드.
     *
     * @param bettingMoney Player의 배팅 금액.
     * @throws InputMismatchException 배팅 금액이 최소 금액보다 작다면 발생하는 예외.
     */
    private static void isValidBettingMoneyAmount(double bettingMoney) throws InputMismatchException {
        if (!(bettingMoney > MIN_BETTING_MONEY)) {
            throw new InputMismatchException();
        }
    }

    /**
     * card를 더 받겠다는 입력 형식이 유효한지 확인하여　예외를　발생시키고　메시지를　출력하는 메소드.
     *
     * @param userInput card를 더 받겠다는 입력.
     * @throws InputMismatchException 입력이 y 또는 n이 아닐 경우 발생하는 예외.
     */
    public static void isValidGetMoreCardReply(String userInput) throws InputMismatchException {
        if (!((userInput.equals(GET_MORE_CARD)) || (userInput.equals(STOP_GET_MORE_CARD)))) {
            out.printPlayerGetMoreCardReplyException();
            throw new InputMismatchException();
        }
    }
}
