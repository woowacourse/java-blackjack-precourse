/*
 * @(#)Output.java      1.0 2019.12.16
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.ui;

/**
 * 출력과 관련된 기능을 담당할 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 1.0 2019.12.16
 */
public class Output {
    /**
     * 게임 참여자들의 이름 입력 요청때 출력할 메시지 상수.
     */
    private static final String PLAYER_NAME_INPUT_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표(,) 기준으로 분리)";

    /**
     * 게임 참여자들의 이름의 길이에 예외가 발생할 경우 출력할 메시지 상수.
     */
    private static final String PLAYER_NAME_LENGTH_EXCEPTION_MESSAGE = "게임에 참여할 사람의 이름은 1자 이상 입력하셔야 합니다.";

    /**
     * 게임 참여자들의 이름의 형식에 예외가 발생할 경우 출력할 메시지 상수.
     */
    private static final String PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE = "게임에 참여할 사람의 이름은 대소문자만 입력 가능합니다.";

    /**
     * 게임 참여자들의 이름에 중복이 존재할 경우 출력할 메시지 상수.
     */
    private static final String PLAYER_NAME_DUPLICATE_EXCEPTION_MESSAGE = "게임에 참여할 사람의 이름에 중복이 존재합니다.";

    /**
     * 사용자에게 재입력을 요청할 때 출력할 메시지 상수.
     */
    private static final String INPUT_REQUEST_AGAIN_MESSAGE = " 다시 입력해주세요.";

    /**
     * Player의 베팅 금액 입력 요청때 출력할 메시지 상수.
     */
    private static final String PLAYER_BETTING_MONEY_REQUEST_MESSAGE = "의 배팅 금액은?";

    /**
     * 배팅 금액이 최소 금액보다 작은 경우 출력할 메시지 상수.
     */
    private static final String BETTING_MONEY_UNDER_MIN_NUMBER_EXCEPTION_MESSAGE = "배팅 금액은 0보다 큰 수여야 합니다.";

    /**
     * Dealer와 Player들에게 2장의 card를 나누어 줄 때 출력할 메시지 상수.
     */
    private static final String HAND_OUT_TWO_CARDS_MESSAGE = "딜러와 %s에게 2장의 카드를 나누었습니다.";

    /**
     * Dealer와 Player들의 card를 출력할 메시지 상수.
     */
    private static final String USER_CARD_STATUS_MESSAGE = "%s 카드: %s";

    /**
     * Player가 한장의 card를 더 받을지 여부 요청할 때 출력할 메시지 상수.
     */
    private static final String PLAYER_GET_ONE_MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니요는 n)";

    /**
     * Player의 card를 더 받을지 여부 입력의 형식이 틀릴 경우 출력할 메시지 상수.
     */
    private static final String PLAYER_GET_MORE_CARD_REPLY_EXCEPTION_MESSAGE =
            "카드를 더 받을 경우 y, 더 이상 받지 않을 경우는 n, 이 두 가지 입력만 가능합니다.";

    /**
     * Dealer의 총점이 16이하일 경우 출력할 메시지 상수.
     */
    private static final String DEALER_CARD_LESS_THAN_SEVENTEEN_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다";

    /**
     * Dealer와 Player의 최종 점수를 출력할 메시지 상수.
     */
    private static final String USER_SCORE_MESSAGE = " - 결과: %d";

    /**
     * 최종 수익 공지를 출력할 메시지 상수.
     */
    private static final String FINAL_PROFIT_MESSAGE = "## 최종 수익";

    /**
     * Dealer와 Player의 최종 수익을 출력할 메시지 상수.
     */
    private static final String USER_PROFIT_MESSAGE = "%s: %.1f";

    /**
     * 게임 참여자들의 이름 입력을 요청을 출력하는 메소드.
     */
    public void printPlayerNameInputRequest() {
        System.out.println(PLAYER_NAME_INPUT_REQUEST_MESSAGE);
    }

    /**
     * 게임 참여자들의 이름의 길이가 유효하지 않음을 출력하는 메소드.
     */
    public void printPlayerNameLengthError() {
        System.out.print(PLAYER_NAME_LENGTH_EXCEPTION_MESSAGE);
    }

    /**
     * 게임 참여자들의 이름의 형식이 유효하지 않음을 출력하는 메소드.
     */
    public void printPlayerNameFormatError() {
        System.out.print(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }

    /**
     * 게임 참여자들의 이름에 중복이 존재함을 출력하는 메소드.
     */
    public void printPlayerNameDuplicateError() {
        System.out.print(PLAYER_NAME_DUPLICATE_EXCEPTION_MESSAGE);
    }

    /**
     * 게임 참여자 이름 재입력 요청을 출력하는 메소드.
     */
    public void printInputRequestAgain() {
        System.out.println(INPUT_REQUEST_AGAIN_MESSAGE);
    }

    /**
     * 해당 Player의 배팅 머니 입력을 요청을 출력하는 메소드.
     *
     * @param playerName 베팅 머니를 입력할 Player 이름.
     */
    public void printPlayerBettingMoneyRequest(String playerName) {
        System.out.println(playerName + PLAYER_BETTING_MONEY_REQUEST_MESSAGE);
    }

    /**
     * 베팅 머니가 최소 배팅 금액보다 작음을 출력하는 메소드.
     */
    public void printBettingMoneyUnderMinNumberError() {
        System.out.print(BETTING_MONEY_UNDER_MIN_NUMBER_EXCEPTION_MESSAGE);
    }

    /**
     * Dealer와 Player들에게 2장의 card를 나누어 주었다고 출력하는 메소드.
     *
     * @param playerNames 카드를 받을 Player들의 이름.
     */
    public void printHandOutTwoCards(String playerNames) {
        printNewLine();
        System.out.printf(HAND_OUT_TWO_CARDS_MESSAGE, playerNames);
        printNewLine();
    }

    /**
     * Dealer 또는 Player의 현재 card 상태를 출력하는 메소드.
     *
     * @param userName   Dealer 또는 Player의 이름.
     * @param cardStatus 현재 card 상태(Dealer는 HandOutTwoCards에서는 1장만 공개).
     */
    public void printUserCurrentCardStatus(String userName, String cardStatus) {
        System.out.printf(USER_CARD_STATUS_MESSAGE, userName, cardStatus);
        printNewLine();
    }

    /**
     * Player가 한장의 card를 더 받을지 여부 요청을 출력하는 메소드.
     *
     * @param playerName card를 받을 Player의 이름.
     */
    public void printPlayerGetOneMoreCard(String playerName) {
        System.out.println(playerName + PLAYER_GET_ONE_MORE_CARD_MESSAGE);
    }

    /**
     * Player의 한장의 card를 더 받을지 여부 입력이 유효하지 않을 경우 출력하는 메소드.
     */
    public void printPlayerGetMoreCardReplyError() {
        System.out.print(PLAYER_GET_MORE_CARD_REPLY_EXCEPTION_MESSAGE);
    }

    /**
     * Dealer의 총점이 17점 미만일 경우 출력할 메소드.
     */
    public void printDealerCardLessThanSeventeen() {
        System.out.println(DEALER_CARD_LESS_THAN_SEVENTEEN_MESSAGE);
    }

    /**
     * 게임이 모두 끝나고 Dealer 또는 Player의 card 상태와 결과를 출력하는 메소드.
     *
     * @param userName   Dealer 또는 Player의 이름.
     * @param cardStatus Dealer 또는 Player의 card 상태(Dealer는 게임이 끝날 땐 모든 card 공개).
     * @param userScore  Dealer 또는 Player의 최종 점수.
     */
    public void printUserFinalResult(String userName, String cardStatus, int userScore) {
        System.out.printf(USER_CARD_STATUS_MESSAGE + USER_SCORE_MESSAGE, userName, cardStatus, userScore);
        printNewLine();
    }

    /**
     * 최종 수익 공지를 출력하는 메소드.
     */
    public void printFinalProfitNotice() {
        printNewLine();
        System.out.println(FINAL_PROFIT_MESSAGE);
    }

    /**
     * Dealer 또는 Player의 최종 수익을 출력하는 메소드.
     *
     * @param userName Dealer 또는 Player의 이름.
     * @param profit   Dealer 또는 Player의 최종 수익.
     */
    public void printUserFinalProfit(String userName, double profit) {
        System.out.printf(USER_PROFIT_MESSAGE, userName, profit);
        printNewLine();
    }

    /**
     * 개행을 위한 메소드.
     */
    public void printNewLine() {
        System.out.println(" ");
    }
}
