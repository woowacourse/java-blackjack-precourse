/*
 * class: View
 *
 * version: 1.0v
 *
 * date: 2019.12.17
 *
 * 이 프로그램의 저작권은 정은석에게 있습니다.
 * Copyright 2019.12.17
 */

package domain.output;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * UI를 담당하는 클레
 */
public class View {

    /**
     * 플레이어와 딜러의 카드 정보를 모두 보여주는 기능
     *
     * @param playerList 플레이어 리스트
     * @param dealer     딜러
     */
    public void viewPlayerAndDealer(List<Player> playerList, Dealer dealer) {
        System.out.println(dealer.toStringCard());
        for (Player player : playerList) {
            System.out.println(player.toStringCard());
        }
        System.out.println();
    }

    /**
     * 플레이어의 카드정보를 보여주는 기능
     *
     * @param player 플레이어 정보
     */
    public void viewPlayerCard(Player player) {
        System.out.println(player.toStringCard());
    }

    /**
     * 플레이어와 딜러에게 시작과 동시에 카드를 2장 지급했다는 상태메시지
     *
     * @param playerList 플레이어정보를 갖고있는 List
     * @return 상태메시지
     */
    public String startMessage(List<Player> playerList) {
        List<String> nameList = new ArrayList<>();

        for (Player player : playerList) {
            nameList.add(player.getName());
        }

        return "딜러와 " +
                String.join(",", nameList) +
                "에게 2장의 카드르 나누었습니다.";
    }
}
