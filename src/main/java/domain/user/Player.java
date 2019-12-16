/*
 * @(#)Player.java      1.0 2019.12.16
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.user;

import domain.business.StringUtil;
import domain.ui.Output;

/**
 * 게임 참여자를 의미하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 1.0 2019.12.16
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
     * Player가 현재 가지고 있는 card를 이름과 함께 출력하는 메소드.
     */
    @Override
    public void printCurrentCardStatus() {
        Output out = new Output();
        out.printUserCurrentCardStatus(name, StringUtil.joinCardName(cards));
    }

    /**
     * Player가 최종으로 가지고 있는 card와 이름, 총점을 함께 출력하는 메소드.
     */
    @Override
    public void printFinalCardStatus() {
        Output out = new Output();
        out.printUserFinalResult(name, StringUtil.joinCardName(cards), getScore());
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
