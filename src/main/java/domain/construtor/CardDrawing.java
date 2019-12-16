/*
 * class: CardDrawing
 *
 * version: 1.0v
 *
 * date: 2019.12.16
 *
 * 이 프로그램의 저작권은 정은석에게 있습니다.
 * Copyright 2019.12.16
 */

package domain.construtor;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 카드를 드로우 하는 기능하는 클레스
 *
 * @author joseph415
 * @version 1.0 2019.12.16
 */
public class CardDrawing {
    static final List<Card> cards = CardFactory.create();
    Random randomIndex = new Random();

    /**
     * 시작할때 플레이어에게 2장을 지급하고 딜러가 2장을 드로우하는 기능
     *
     * @param playerList 플레이어정보를 갖고있는 List
     * @param dealer     딜러정보를 갖고있는 List
     */
    public void startShuffle(List<Player> playerList, Dealer dealer) {
        System.out.println(toString(playerList));

        dealer.addCard(cards.get(randomIndex.nextInt(52)));
        dealer.addCard(cards.get(randomIndex.nextInt(52)));

        playerList.forEach(player -> player.addCard(cards.get(randomIndex.nextInt(52))));
        playerList.forEach(player -> player.addCard(cards.get(randomIndex.nextInt(52))));

        toStringCardInfo(playerList,dealer);
    }

    public void drawCard(List<Player> playerList, Dealer dealer) {

    }

    /**
     * 플레이어와 딜러에게 시작과 동시에 카드를 2장 지급했다는 상태메시지
     *
     * @param playerList 플레이어정보를 갖고있는 List
     * @return 상태메시지
     */
    public String toString(List<Player> playerList) {
        List<String> nameList = new ArrayList<>();

        for (Player player : playerList) {
            nameList.add(player.getName());
        }

        return "딜러와 " +
                String.join(",", nameList) +
                "에게 2장의 카드르 나누었습니다.";
    }

    /**
     * 뽑은 카드가 뭔지 나타내는 상태메시지
     *
     * @param playerList 플레이어정보를 갖고있는 List
     */
    public void toStringCardInfo(List<Player> playerList,Dealer dealer) {
        System.out.println(dealer.toStringCard());

        for (Player player : playerList) {
            System.out.println(player.toStringCard());
        }
    }
}
