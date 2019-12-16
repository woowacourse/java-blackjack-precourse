/*
 * @(#)Dealer.java      0.9 2019.12.16
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
 * 게임 딜러를 의미하는 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.9 2019.12.16
 */
public class Dealer {
    /**
     * Dealer와 관련된 정보를 출력할 때 사용할 상수.
     */
    private static final String DEALER_NAME = "딜러";

    /**
     * 첫번째 카드만 출력해야할 경우 사용할 상수.
     */
    private static final int FIRST_CARD = 0;

    /**
     * 블랙잭인지 확인하기 위한 카드의 범위를 정할 때 사용할 상수.
     */
    private static final int BLACK_JACK_CHECK_RANGE = 2;

    /**
     * 점수가 계속 카드를 뽑아야 하는 점수인지 판단하기 위한 상수.
     */
    private static final int DRAW_CONTINUE_SCORE = 16;

    /**
     * 딜러의 첫 두장의 카드가 블랙잭인지 확인하기 위한 상수.
     */
    private static final int BLACK_JACK = 21;

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
     * Dealer가 받은 카드를 저장할 Card 객체 List.
     */
    private final List<Card> cards = new ArrayList<>();

    /**
     * Dealer 클래스의 기본 생성자(이 이외의 생성자는 금지).
     */
    public Dealer() {}

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
     * 처음 2개의 카드를 받았을 때 첫번째 카드만 출력하는 메소드.
     */
    public void printDealerCurrentCardStatus() {
        Output out = new Output();
        out.printUserCurrentCardStatus(DEALER_NAME, cards.get(FIRST_CARD).toString());
    }

    /**
     * Dealer가 가지고 있는 모든 카드의 숫자 합을 구하는 메소드.
     *
     * @return 가지고 있는 모든 카드의 숫자 합을 반환.
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
     * @param card       ACE인지 확인하기 위한 카드.
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
     * Dealer가 총점이 17이 안될경우 규칙에 따라 계속 카드를 뽑도록 하는 메소드.
     *
     * @param deck 카드를 뽑을 덱.
     * @return 규칙에 따라 모든 카드를 뽑은 후 총점.
     */
    private int drawAccordingRule(Deck deck) {
        int totalScore = getTotalScore();
        Output out = new Output();

        if (totalScore <= DRAW_CONTINUE_SCORE) {
            out.printNewLine();
            out.printDealerCardUnderSeventeen();
            totalScore = drawUntilOverSixteen(totalScore, deck);
        }
        return totalScore;
    }

    /**
     * Dealer가 규칙에 따라 카드를 뽑고, 최종 결과를 출력하는 메소드.
     *
     * @param deck 카드를 뽑을 덱.
     */
    public void printFinalResult(Deck deck) {
        Output out = new Output();
        int totalScore = drawAccordingRule(deck);
        out.printUserFinalResult(DEALER_NAME, StringUtil.joinCardName(cards), totalScore);
    }

    /**
     * 총점이 16점을 초과할 때까지 카드를 뽑아 점수를 반환하는 메소드.
     *
     * @param totalScore 현재까지 총점.
     * @param deck       카드를 뽑을 덱.
     * @return 카드를 뽑아 16점을 초과한 총점.
     */
    private int drawUntilOverSixteen(int totalScore, Deck deck) {
        if (totalScore <= DRAW_CONTINUE_SCORE) {
            cards.add(deck.drawCard());
            totalScore = getTotalScore();
            return drawUntilOverSixteen(totalScore, deck);
        }
        return totalScore;
    }

    /**
     * 블랙잭인지 확인하기 위한 덱을 생성하는 메소드.
     *
     * @return 처음 2장의 카드만 저장된 Card List.
     */
    private List<Card> makeBlackJackCheckDeck() {
        List<Card> blackJackCheckDeck = new ArrayList<>();

        for (int i = 0; i < BLACK_JACK_CHECK_RANGE; i++) {
            blackJackCheckDeck.add(cards.get(i));
        }
        return blackJackCheckDeck;
    }

    /**
     * 블랙잭인지 확인할 경우에 사용되며, ACE이면 무조건 11을, 아닌 경우는 원래의 값을 반환하는 메소드.
     *
     * @param card ACE인지 확인할 카드.
     * @return ACE일 경우 11을, 아니면 원래 카드의 숫자를 반환.
     */
    private int convertAceToEleven(Card card) {
        if (card.isAceCard()) {
            return card.getSymbolScore() + ACE_USED_ELEVEN;
        }
        return card.getSymbolScore();
    }

    /**
     * Dealer의 첫 두장의 카드가 블랙잭인지 확인하는 메소드.
     *
     * @return 블랙잭인 경우 true 반환.
     */
    public boolean isBlackJack() {
        int totalScore = 0;
        List<Card> blackJackCheckDeck = makeBlackJackCheckDeck();

        for (Card card : blackJackCheckDeck) {
            totalScore += convertAceToEleven(card);
        }
        return (totalScore == BLACK_JACK);
    }
}
