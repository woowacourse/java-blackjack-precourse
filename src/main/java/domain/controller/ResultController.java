/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.user.Dealer;
import domain.model.user.Player;

import java.util.ArrayList;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 플레이어와 딜러의 점수를 계산하고 버스트, 블랙잭, 최종결과를 계산하고 처리하는 클래스입니다.
 * @since : 2019.12.14 토요일
 */
public class ResultController {
    private static final int BLACK_JACK_NUMBER = 21;
    private static final int BURST_NUMBER = 21;

    public boolean checkInitialResult(ArrayList<Player> playerList, Dealer dealer) {
        boolean isBlackJack = checkInitialBlackJack(playerList, dealer);
        // TODO 여기서 최종 수익계산 하는 메서드로 + HumanInCasino에 수익 필드 넣기.
        return isBlackJack;
    }

    public boolean checkBlackJackOrNot(int inputScore) {
        return inputScore == BLACK_JACK_NUMBER;
    }

    public boolean checkInitialBlackJack(ArrayList<Player> playerList, Dealer dealer) {
        boolean ifDealerBlackJack = checkBlackJackOrNot(dealer.getCurrentScore());
        boolean ifSomePlayerBlackJack = false;

        for (Player player : playerList) {
            ifSomePlayerBlackJack = checkBlackJackOrNot(player.getCurrentScore()) || ifSomePlayerBlackJack;
        }

        return ifDealerBlackJack || ifSomePlayerBlackJack;
    }

    public boolean checkBurstOrNot(int inputScore) {
        if (inputScore <= BURST_NUMBER) {
            return false;
        }

        // TODO 여기에 버스트시 ACE체크하는 것 넣기.
        return true;

    }
}