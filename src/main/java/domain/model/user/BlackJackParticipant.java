/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.user;

import domain.model.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote Dealer와 Player 클래스의 상위개념인 BlackJackParticipant 추상 클래스입니다.
 * @since : 2019.12.17 화요일
 */
public abstract class BlackJackParticipant {
    private static final String COMMA = ", ";
    private static final String A = "A";
    private static final String NOTHING = "";
    private static final int BLACKJACK = 21;
    private static final int BURST = 22;
    private static final int TEN = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getCardsInformation() { // 카드리스트를 이용해서 출력될 카드 정보를 String으로 만들어서 보내는 메서드
        ArrayList<String> cardInfoArrayList = new ArrayList<>();

        for (Card card : cards) {
            cardInfoArrayList.add(card.toString());
        }

        return String.join(COMMA, cardInfoArrayList);
    }

    public ArrayList<Integer> getAllCardsScore() {
        ArrayList<Integer> scoreList = new ArrayList<>();

        for (Card card : this.cards) {
            scoreList.add(card.getSymbolScore());
        }

        return scoreList;
    }

    public boolean isBlackJack() {
        List<Integer> scoreList = getAllCardsScore().subList(0, 2);
        return scoreList.stream().mapToInt(Integer::intValue).sum() == BLACKJACK;
    }

    public boolean isBurst() {
        return getCurrentScore() >= BURST;
    }

    public int getCurrentScore() {
        ArrayList<Integer> scoreList = getAllCardsScore();
        int currentScore = scoreList.stream().mapToInt(Integer::intValue).sum();

        if (currentScore >= BURST) {
            return (currentScore - TEN * checkHowManyAceInCards());
        }

        return currentScore;
    }

    public int checkHowManyAceInCards() {
        return (getCardsInformation().length() - getCardsInformation().replace(A, NOTHING).length());
    }

    public List<Card> getAllCards() {
        return cards;
    }

}
