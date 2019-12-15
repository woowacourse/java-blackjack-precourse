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
 * @apiNote Dealer와 Player 클래스의 상위개념인 BlackJackParticipant 추상 클래스 입니다.
 * @since : 2019.12.14 토요일
 */
public abstract class BlackJackParticipant {
    private static final String COMMA = ", ";
    private static final String A = "A";
    private static final String NOTHING = "";

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

    public int getCurrentScore() {
        ArrayList<Integer> scoreList = getAllCardsScore();
        return scoreList.stream().mapToInt(Integer::intValue).sum();
    }

    public int checkHowManyAceInCards() {
        return getCardsInformation().length() - getCardsInformation().replace(A, NOTHING).length();
    }

    public List<Card> getAllCards() {
        return cards;
    }

}
