/*
 * @(#)Dealer.java      1.0 2019.12.16
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.user;

import domain.business.StringUtil;
import domain.card.Card;
import domain.card.Deck;
import domain.ui.Output;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 1.0 2019.12.16
 */
public class Dealer {
    /**
     * Dealer와 관련된 정보를 출력할 때 이름으로 사용할 상수.
     */
    private static final String DEALER_NAME = "Dealer";

    /**
     * 처음 Player의 score를 0으로 초기화 하기 위한 상수.
     */
    private static final int INIT_SCORE_TO_ZERO = 0;

    /**
     * ACE card가 아닐 경우 추가 점수를 줄 수 없도록 하기 위해 사용할 상수.
     */
    private static final int NOT_ACE_CARD = 0;

    /**
     * Dealer는 처음 2장의 카드를 받았을 때 1장의 카드만 출력해야 하므로 그때 사용할 상수.
     */
    private static final int FIRST_CARD = 0;

    /**
     * ACE card를 1로 사용할 경우, 추가로 점수에 더해야 할 값으로 사용할 상수.
     */
    private static final int ACE_USED_ONE = 0;

    /**
     * ACE card를 11로 사용할 경우, 추가로 점수에 더해야 할 값으로 사용할 상수.
     */
    private static final int ACE_USED_ELEVEN = 10;

    /**
     * 블랙잭(처음 2장의 care의 총합이 21)인지 확인하기 위한 card List를 만들 때(처음 2장의 card) 사용할 상수.
     */
    private static final int BLACK_JACK_CHECK_CARD_RANGE = 2;

    /**
     * 블랙잭(처음 2장의 care의 총합이 21)인지 확인하기 위해 사용할 상수.
     */
    private static final int BLACK_JACK = 21;

    /**
     * 버스트(총합이 21을 초과)하는 상황인지 확인하기 위한 상수.
     */
    private static final int BUST_NUMBER = 22;

    /**
     * Dealer가 받은 card를 저장할 card List.
     */
    private final List<Card> cards = new ArrayList<>();

    /**
     * Dealer 클래스의 기본 생성자.
     */
    public Dealer() {}

    /**
     * cards에 새로 받은 card를 추가하는 메소드.
     *
     * @param card 새로 받은 card.
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    /**
     * 처음 2장의 card를 받았을 때 첫번째 card만 출력하는 메소드.
     */
    public void printDealerCurrentCardStatus() {
        Output out = new Output();
        out.printUserCurrentCardStatus(DEALER_NAME, cards.get(FIRST_CARD).toString());
    }

    /**
     * Dealer가 규칙에 따라 카드를 뽑고, 최종 결과를 출력하는 메소드.
     */
    public void printFinalResult() {
        Output out = new Output();
        out.printUserFinalResult(DEALER_NAME, StringUtil.joinCardName(cards), getScore());
    }

    /**
     * Dealer가 가지고 있는 모든 card의 숫자 합(총점)을 구하는 메소드.
     * ACE card가 존재할 경우를 확인하여 추가 연산 진행.
     *
     * @return 가지고 있는 모든 card의 숫자 총합을 반환(총점).
     */
    public int getScore() {
        int score = INIT_SCORE_TO_ZERO;

        for (Card card : cards) {
            score += card.getSymbolScore();
        }
        return calculateIfAceExist(score);
    }

    /**
     * ACE card가 존재하는지 찾고, Dealer에게 유리하게 계산하는 메소드.
     *
     * @param score ACE card가 1로 계산된 총합.
     * @return ACE card의 사용 여부가 결정된 총합.
     */
    private int calculateIfAceExist(int score) {
        for (Card card : cards) {
            score += addAdditionalIfAceCard(score, card);
        }
        return score;
    }

    /**
     * ACE card이면 더해야할 추가 점수를, 일반 카드면 0을 반환하는 메소드.
     *
     * @param totalScore 현재까지의 총합.
     * @param card       ACE card인지 확인할 card.
     * @return ACE card인 경우 상황에 따른 추가 점수, ACE card가 아니면 0 반환.
     */
    private int addAdditionalIfAceCard(int totalScore, Card card) {
        if (card.isAceCard()) {
            return useAceOneOrEleven(totalScore);
        }
        return NOT_ACE_CARD;
    }

    /**
     * ACE card를 11로 사용했을 떄 버스트(총점이 21초과)가 발생하는지 확인하여 연산하는 메소드.
     *
     * @param totalScore ACE card가 1로 연산된 총합.
     * @return ACE card를 1 또는 11로 사용할 지에 대한 추가 점수(1로 쓰면 0, 11로 쓰면 10).
     */
    private int useAceOneOrEleven(int totalScore) {
        if ((totalScore + ACE_USED_ELEVEN) >= BUST_NUMBER) {
            return ACE_USED_ONE;
        }
        return ACE_USED_ELEVEN;
    }

    /**
     * Dealer의 첫 2장의 card가 블랙잭(처음 2장의 care의 총합이 21)인지 확인하는 메소드.
     *
     * @return 블랙잭(처음 2장의 care의 총합이 21)인 경우 true 반환.
     */
    public boolean isBlackJack() {
        int score = INIT_SCORE_TO_ZERO;
        List<Card> blackJackCheckDeck = makeBlackJackCheckDeck();

        for (Card card : blackJackCheckDeck) {
            score += checkAceAndGetSymbolScore(card);
        }
        return (score == BLACK_JACK);
    }

    /**
     * 블랙잭(처음 2장의 care의 총합이 21)인지 확인하기 위한 deck을 생성하는 메소드.
     *
     * @return 처음 2장의 카드만 저장된 card List.
     */
    private List<Card> makeBlackJackCheckDeck() {
        List<Card> blackJackCheckDeck = new ArrayList<>();

        for (int i = 0; i < BLACK_JACK_CHECK_CARD_RANGE; i++) {
            blackJackCheckDeck.add(cards.get(i));
        }
        return blackJackCheckDeck;
    }

    /**
     * 블랙잭(처음 2장의 care의 총합이 21)인지 확인할 경우에 사용되며, ACE card이면 무조건 11을, 아닌 경우는 원래의 값을 반환하는 메소드.
     *
     * @param card ACE card인지 확인할 카드.
     * @return ACE card일 경우엔 11을, 아니면 원래 카드의 숫자를 반환.
     */
    private int checkAceAndGetSymbolScore(Card card) {
        if (card.isAceCard()) {
            return card.getSymbolScore() + ACE_USED_ELEVEN;
        }
        return card.getSymbolScore();
    }

    /**
     * 총점이 버스트(총점이 21초과)인지 확인하는 메소드.
     *
     * @return 버스트이면 true 반환.
     */
    public boolean isBust() {
        return (getScore() >= BUST_NUMBER);
    }
}
