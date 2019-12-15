/*
 * @(#)Output.java      0.2 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.ui;

/**
 * 출력과 관련된 기능을 담당할 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.2 2019.12.15
 */
public class Output {
    /**
     * print나 printf를 사용할 때 개행을 하기 위한 문자열 상수.
     */
    private static final String NEW_LINE_STRING = "%n";
    /**
     * 게임 참여자 이름 입력 요청때 출력할 메시지 상수.
     */
    private static final String PLAYER_NAME_INPUT_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표(,) 기준으로 분리";

    /**
     * Player의 베팅 금액 입력 요청때 출력할 메시지 상수.
     */
    private static final String PLAYER_BETTING_MONEY_REQUEST_MESSAGE = "의 베팅 금액은?";

    /**
     * Dealer와 Player에게 2장의 카드를 나누어 줄때 출력할 메시지 상수.
     */
    private static final String HAND_OUT_TWO_CARDS_MESSAGE = "딜러와 %s에게 2장의 카드를 나누었습니다.";

    /**
     * User의 카드를 출력할 때 연결해줄 메시지 상수.
     */
    private static final String USER_CARD_STATUS_MESSAGE = "%s카드: %s";

    /**
     * Player가 한장의 카드를 더 받을지 여부 요청때 출력할 메시지 상수.
     */
    private static final String PLAYER_GET_ONE_MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니요는 n)";

    /**
     * Dealer 카드가 16이하일 경우 출력할 메시지 상수.
     */
    private static final String DEALER_CARD_UNDER_SEVENTEEN_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다";

    /**
     * User들의 최종 점수를 출력할 메시지 상수.
     */
    private static final String USER_SCORE_MESSAGE = " - 결과: %d";

    /**
     * 최종 수익 공지를 출력할 메시지 상수.
     */
    private static final String FINAL_PROFIT_MESSAGE = "## 최종 수익";

    /**
     * User의 최종 수익을 출력할 메시지 상수.
     */
    private static final String USER_PROFIT_MESSAGE = "%s: %d";

    /**
     * 게임 참여자 이름 입력을 요청을 출력하는 메소드.
     */
    public void printPlayerNameInputRequest() {
        System.out.println(PLAYER_NAME_INPUT_REQUEST_MESSAGE);
    }

    /**
     * 해당 Player의 베팅 머니 입력을 요청을 출력하는 메소드.
     *
     * @param playerName 베팅 머니를 입력할 플레이어 이름.
     */
    public void printPlayerBettingMoneyRequest(String playerName) {
        System.out.println(playerName + PLAYER_BETTING_MONEY_REQUEST_MESSAGE);
    }

    /**
     * Dealer와 Player들에게 2장의 카드를 나누었다고 출력하는 메소드.
     *
     * @param playerNames 카드를 받을 Player들의 이름.
     */
    public void printHandOutTwoCards(String playerNames) {
        System.out.printf(HAND_OUT_TWO_CARDS_MESSAGE + NEW_LINE_STRING, playerNames);
    }

    /**
     * User의 현재 카드 상태를 출력하는 메소드.
     *
     * @param userName   User의 이름(Dealer와 Player).
     * @param cardStatus 해당 유저의 현재 카드 상태(Dealer는 HandOutTwoCards에선 1장만 공개).
     */
    public void printUserCurrentCardStatus(String userName, String cardStatus) {
        System.out.printf(USER_CARD_STATUS_MESSAGE + NEW_LINE_STRING, userName, cardStatus);
    }

    /**
     * Player가 한장의 카드를 더 받을지 여부 요청을 출력하는 메소드.
     *
     * @param playerName 카드를 받을 Player의 이름.
     */
    public void printPlayerGetOneMoreCard(String playerName) {
        System.out.println(playerName + PLAYER_GET_ONE_MORE_CARD_MESSAGE);
    }

    /**
     * Dealer 카드의 합이 16이하일 경우 출력할 메소드.
     */
    public void printDealerCardUnderSeventeen() {
        System.out.println(DEALER_CARD_UNDER_SEVENTEEN_MESSAGE);
    }

    /**
     * 카드 배분이 모두 끝나고 User의 카드 상태와 결과를 출력하는 메소드.
     *
     * @param userName   User의 이름.
     * @param cardStatus 해당 User의 카드 상태(Dealer는 게임이 끝날 땐 모든 카드 공개).
     * @param userScore  해당 User의 점수.
     */
    public void printUserFinalResult(String userName, String cardStatus, int userScore) {
        System.out.printf(USER_CARD_STATUS_MESSAGE + USER_SCORE_MESSAGE + NEW_LINE_STRING,
                userName, cardStatus, userScore);
    }

    /**
     * 최종 수익 공지를 출력하는 메소드.
     */
    public void printFinalProfitNotice() {
        System.out.println(FINAL_PROFIT_MESSAGE);
    }

    /**
     * User의 최종 수익을 출력하는 메소드.
     *
     * @param userName User의 이름.
     * @param profit   해당 User의 최종 수익.
     */
    public void printUserFinalProfit(String userName, int profit) {
        System.out.printf(USER_PROFIT_MESSAGE + NEW_LINE_STRING, userName, profit);
    }
}
