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
        System.out.println(dealer.startCardOpen());
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

    public void viewDealerMoreDraw(boolean moreDraw ){
        if(moreDraw) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            return;
        }
        System.out.println("딜러는 17이상이라 카드를 더 받지 않았습니다.");
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

    /**
     * 게임이 종료될때 오픈하는 메세지
     *
     * @param playerList 플레이어정보를 갖고있는 List
     * @param dealer 딜러객체
     */
    public void resultCardMsg(List<Player> playerList,Dealer dealer){
        System.out.print(dealer.toStringCard());
        for (Player player : playerList) {
            System.out.print(player.toStringCard());
            System.out.println(player.calculateSymbol());
        }
    }

    /**
     * 게임 종료되고 최종수익 공개
     */
    public void resultMsg(Double dealerSum,List<Player> playerList,List<Double> playerMoneyList){
        System.out.println("###최종수익");
        System.out.println("딜러:"+dealerSum);

        for(int i=0;i<playerList.size();i++){
            System.out.println(playerList.get(i).getName()+playerMoneyList.get(i));
        }
    }
}
