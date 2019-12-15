/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.controller;

import domain.model.user.Dealer;
import domain.model.user.Player;
import view.PrintController;

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
        boolean ifDealerBlackJack = dealer.isBlackJack();
        boolean ifSomePlayerBlackJack = false;

        for (Player player : playerList) {
            ifSomePlayerBlackJack = player.isBlackJack() || ifSomePlayerBlackJack;
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

    public void startBlackJackProcedure(ArrayList<Player> playerList, Dealer dealer) {
        if (checkDealerBlackJackOrNot(dealer) && checkPlayerBlackJackOrNot(playerList)) {
            // 둘 다 블랙잭이 있는 경우 -> 어떤 플레이어인지 보고 해당 플레이어는 잃지 않고 블랙잭 아닌 플레이어만 돈 잃기.
            controlFinalResult(playerList, dealer);
            // 돈계산으로 넘어가야함.
            System.out.println("둘다!");
            return ;
        }
        if (checkDealerBlackJackOrNot(dealer)) {
            // 딜러만 블랙잭인 경우 -> 다 잃고 경기 종료
            controlFinalResult(playerList, dealer);
            // 돈계산으로 넘어가야함.
            System.out.println("딜러만!");
            return ;
        }
        if (checkPlayerBlackJackOrNot(playerList)) {
            // 플레이어만 블랙잭인 경우
            // 누군지 체크해서 다음더 뽑기 진행 안시키기
            // 돈 계산 시키기.
            System.out.println("플레이어만!");
            return ;
        }
    }

    public boolean checkDealerBlackJackOrNot(Dealer dealer) {
        return dealer.isBlackJack();
    }

    public boolean checkPlayerBlackJackOrNot(ArrayList<Player> playerList) {
        boolean ifSomePlayerBlackJack = false;

        for (Player player : playerList) {
            ifSomePlayerBlackJack = player.isBlackJack() || ifSomePlayerBlackJack;
        }

        return ifSomePlayerBlackJack;
    }

    public void controlFinalResult(ArrayList<Player> playerList, Dealer dealer) {
        PrintController.printDealerCardFinalInformation(dealer);
        for (Player player : playerList) {
            PrintController.printPlayerCardFinalInformation(player);
        }
    }
}
