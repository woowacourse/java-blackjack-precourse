/*
 * @(#)BlackJackGame.java       0.2 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * 블랙잭 게임을 진행하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.2 2019.12.15
 */
public class BlackJackGame {
    /**
     * 생성된 Player 인스턴스들을 담을 Player 인스턴스 List.
     */
    private List<Player> players;

    /**
     * 게임에서 필요한 Dealer 인스턴스.
     */
    private Dealer dealer;

    /**
     * 블랙잭 게임을 생성하면 Player List와 Dealer를 생성하는 생성자.
     */
    public BlackJackGame() {
        players = new ArrayList<>();
        dealer = new Dealer();
    }

    /**
     * Player 이름과 배팅 머니로 Player를 생성하여 List에 추가하는 메소드.
     *
     * @param userName Player 이름.
     * @param bettingMoney 해당 Player의 배팅 머니.
     */
    public void generatePlayerInstance(String userName, double bettingMoney) {
        players.add(new Player(userName, bettingMoney));
    }
}
