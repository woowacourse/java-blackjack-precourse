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
import domain.input.InputDecision;
import domain.output.View;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 카드를 드로우 하는 기능하는 클레스
 *
 * @author joseph415
 * @version 1.0 2019.12.16
 */
public class CardDrawing {
    static final List<Card> cards = CardFactory.create();
    Scanner sc = new Scanner(System.in);
    Random randomIndex = new Random();
    InputDecision decision = new InputDecision();
    View view = new View();

    /**
     * 시작할때 플레이어에게 2장을 지급하고 딜러가 2장을 드로우하는 기능
     *
     * @param playerList 플레이어정보를 갖고있는 List
     * @param dealer     딜러정보를 갖고있는 List
     */
    public void startShuffle(List<Player> playerList, Dealer dealer) {
        view.startMessage(playerList);

        dealer.addCard(cards.get(randomIndex.nextInt(52)));
        dealer.addCard(cards.get(randomIndex.nextInt(52)));

        playerList.forEach(player -> player.addCard(cards.get(randomIndex.nextInt(52))));
        playerList.forEach(player -> player.addCard(cards.get(randomIndex.nextInt(52))));

        view.viewPlayerAndDealer(playerList, dealer);
    }

    /**
     * 플레이어모두가 카드를 뽑는 기능
     *
     * @param playerList 플레이어 리스트
     */
    public void drawAllPlayer(List<Player> playerList) {
        for (Player player : playerList) {
            drawPlayerCard(player);
        }
    }

    /**
     * 플레이어가 카드를 뽑을지 결정하는 기능
     *
     * @param player 플레이어 정보
     */
    public void drawPlayerCard(Player player) {
        while (decision.decideDraw(player)) {
            player.addCard(cards.get(randomIndex.nextInt(52)));
            view.viewPlayerCard(player);
        }
    }
}
