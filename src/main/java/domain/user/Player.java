/*
 * @(#)Player.java      0.4 2019.12.15
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
 * @version 0.4 2019.12.15
 */
public class Player {
    /**
     * 버스트(21을 초과)하는 상황인지 확인하기 위한 상수.
     */
    private static final int BUST_NUMBER = 22;

    /**
     * ACE를 1로 사용할 경우 추가로 더해야 할 숫자로 사용되는 상수.
     */
    private static final int ACE_USED_ONE = 0;

    /**
     * ACE를 11로 사용할 경우 추가로 더해야 할 숫자로 사용되는 상수.
     */
    private static final int ACE_USED_ELEVEN = 10;
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
     * @param name         Player 이름.
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
     * Player가 가지고 있는 모든 카드의 숫자 합을 구하는 메소드.
     * ACE인 경우 추가 연산 완료.
     *
     * @return 가지고 있는 모든 카드의 숫자 총합을 반환.
     */
    public int getTotalScore() {
        int totalScore = 0;

        for (Card card : cards) {
            totalScore += card.getSymbolScore();
        }
        return isAceMakeBust(totalScore);
    }

    /**
     * 카드가 ACE인지 찾고, 어떻게 사용할 것인지 추가로 계산하는 메소드.
     *
     * @param totalScore ACE를 1로 계산한 총합.
     * @return ACE의 사용 여부가 결정된 총합.
     */
    private int isAceMakeBust(int totalScore) {
        for (Card card : cards) {
            totalScore = isAce(totalScore, card);
        }
        return totalScore;
    }

    /**
     * 해당 카드가 ACE이면 어떻게 사용할 지 연산하는 메소드.
     *
     * @param totalScore 현재까지의 총합.
     * @param card ACE인지 확인하기 위한 카드.
     * @return ACE인 경우 추가 연산을 진행한 총합.
     */
    private int isAce(int totalScore, Card card) {
        if (card.isAceCard()) {
            totalScore += isMakeBust(totalScore);
        }
        return totalScore;
    }

    /**
     * ACE를 11로 사용했을 떄 버스트가 발생하는지 확인하여 연산하는 메소드.
     *
     * @param totalScore ACE가 1로 연산된 총합.
     * @return ACE를 1 또는 11로 사용할 지에 대한 추가 점수(1로 쓰면 0, 11로 쓰면 10).
     */
    private int isMakeBust(int totalScore) {
        if ((totalScore + ACE_USED_ELEVEN) >= BUST_NUMBER) {
            return ACE_USED_ONE;
        }
        return ACE_USED_ELEVEN;
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
