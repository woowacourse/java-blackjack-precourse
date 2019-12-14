/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.user;

import domain.model.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends  HumanInCasino{
    private static final int ELEVEN = 11;
    private static final String COMMA = ", ";
    private static final String A = "A";
    private static final String NOTHING = "";

    public Dealer() {}
    private final List<Card> cards = new ArrayList<>();

  public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public Card getCard() {
        return cards.get(0);
    }

    public List<Card> getAllCards() {
        return this.cards;
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
        for (Card card : cards) {
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

}
