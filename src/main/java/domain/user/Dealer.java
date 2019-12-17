/*
 * @(#)Dealer.java      1.2 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.user;

import domain.business.StringUtil;
import domain.ui.Output;

/**
 * 게임 딜러를 의미하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 1.2 2019.12.17
 */
public class Dealer extends User {
    /**
     * Dealer와 관련된 정보를 출력할 때 이름으로 사용할 상수.
     */
    private static final String DEALER_NAME = "Dealer";

    /**
     * Dealer는 처음 2장의 카드를 받았을 때 1장의 카드만 출력해야 하므로 그때 사용할 상수.
     */
    private static final int FIRST_CARD = 0;

    /**
     * Dealer가 계속 card를 뽑아야 하는 점수인지 판단하기 위한 상수.
     */
    private static final int DRAW_CONTINUE_SCORE = 16;

    /**
     * Dealer 클래스의 기본 생성자.
     */
    public Dealer() {}

    /**
     * 처음 2장의 card를 받았을 때 첫번째 card만 출력하는 메소드.
     */
    @Override
    public void printCurrentCardStatus() {
        Output out = new Output();
        out.printUserCurrentCardStatus(DEALER_NAME, cards.get(FIRST_CARD).toString());
    }

    /**
     * Dealer의 최종 결과를 출력하는 메소드.
     */
    @Override
    public void printFinalCardStatus() {
        Output out = new Output();
        out.printUserFinalResult(DEALER_NAME, StringUtil.joinCardsName(cards), getScore());
    }

    /**
     * Dealer가 계속 카드를 뽑아야 하는지 여부를 확인하는 메소드.
     *
     * @return 총점이 16점 이하이면 true 반환.
     */
    public boolean isDrawContinue() {
        return (getScore() <= DRAW_CONTINUE_SCORE);
    }
}
