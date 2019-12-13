/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package view;

import domain.model.card.Card;
import domain.model.user.Dealer;
import domain.model.user.Player;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 모든 출력을 담당하는 클래스입니다.
 * @since : 2019.12.12 목요일
 */
public class PrintController {
    private static final String ASKING_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_IS_EMPTY_ERROR = "오류 : 아무것도 입력하지 않으셨습니다.";
    private static final String INPUT_HAS_BLANK_ERROR = "오류 : 입력에 공백이 있습니다.";
    private static final String INPUT_IS_COMMA_ERROR = "오류 : 컴마만 입력이 되었습니다.";
    private static final String ASKING_BETTING_MONEY = "의 배팅 금액은?";
    private static final String INPUT_NUMBER_IS_SMALL_ERROR = "오류 : 1 이상의 정수만 입력해주세요.";
    private static final String DEALER_AND = "딜러와 ";
    private static final String HANDING_TWO_CARDS_INFORMATION = "에게 2장의 카드를 나누었습니다.";
    private static final String DEALER_IS = "딜러: ";
    private static final String CARD_IS = "카드: ";

    public static void askPlayerName() {
        System.out.println(ASKING_PLAYER_NAME);
    }

    public static void printInputEmptyError() {
        System.out.println(INPUT_IS_EMPTY_ERROR);
    }

    public static void printInputHasBlankError() {
        System.out.println(INPUT_HAS_BLANK_ERROR);
    }

    public static void printInputIsCommaError() {
        System.out.println(INPUT_IS_COMMA_ERROR);
    }

    public static void askBettingMoney(String playerName) {
        System.out.println(playerName + ASKING_BETTING_MONEY);
    }

    public static void printInputNumberIsSmallError() {
        System.out.println(INPUT_NUMBER_IS_SMALL_ERROR);
    }

    public static void printHandingTwoCardsInformation(String playersName) {
        System.out.println(DEALER_AND + playersName + HANDING_TWO_CARDS_INFORMATION);
    }

    public static void printDealerCardInformation(Dealer dealer) {
        System.out.println(DEALER_IS + dealer.getCard().toString());
    }

    public static void printPlayerCardInformation(Player player) {
        System.out.println(player.getName() + CARD_IS + player.getCardsInformation());
    }

}
