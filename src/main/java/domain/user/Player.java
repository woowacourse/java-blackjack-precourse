/*
 * @(#)Player.java      1.2 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.user;

import domain.business.StringUtil;

/**
 * 게임 참여자를 의미하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 1.2 2019.12.17
 */
public class Player extends User {
    /**
     * Player의 이름을 저장할 변수.
     */
    private final String name;

    /**
     * Player의 배팅 금액을 저장할 변수.
     */
    private final double bettingMoney;

    /**
     * Player의 이름과 배팅 금액을 매개변수로 받는 Player 매개변수 생성자.
     *
     * @param name         Player의 이름.
     * @param bettingMoney Player의 배팅 금액.
     */
    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    /**
     * Player의 현재 card를 출력하는 메소드.
     */
    @Override
    public void printCurrentCardStatus() {
        out.printUserCurrentCardStatus(name, StringUtil.joinCardsName(cards));
    }

    /**
     * Player의 최종 card와 총점을 출력하는 메소드.
     */
    @Override
    public void printFinalCardStatus() {
        out.printUserFinalResult(name, StringUtil.joinCardsName(cards), getScore());
    }

    /**
     * name getter
     *
     * @return Player의 이름 반환.
     */
    public String getName() {
        return name;
    }

    /**
     * bettingMoney getter.
     *
     * @return Player의 배팅 머니 반환.
     */
    public double getBettingMoney() {
        return bettingMoney;
    }
}
