/*
 * @(#)Player.java      0.3 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.user;

import domain.business.StringUtil;
import domain.card.Card;
import domain.ui.Output;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.3 2019.12.15
 */
public class Player {
    /**
     * Player의 이름을 저장할 변수.
     */
    private final String name;

    /**
     * Player의 배팅 금액을 저장할 변수.
     */
    private final double bettingMoney;

    /**
     * Player가 받은 카드를 저장할 Card 객체 List.
     */
    private final List<Card> cards = new ArrayList<>();

    /**
     * Player의 이름과 배팅 금액을 매개변수로 받는 Player 매개변수 생성자(이 이외의 생성자는 금지).
     *
     * @param name Player 이름.
     * @param bettingMoney Player 배팅 금액.
     */
    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    /**
     * Card List인 cards에 새로 받은 card를 추가하는 메소드.
     *
     * @param card 새로 받은 Card 객체.
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    /**
     * Player가 현재 가지고 있는 카드를 출력하는 메소드.
     */
    public void printPlayerCurrentCardStatus() {
        Output out = new Output();
        out.printUserCurrentCardStatus(name, StringUtil.joinCardName(cards));
    }

    /**
     * name getter
     *
     * @return Player 이름 반환.
     */
    public String getName() {
        return name;
    }
}
