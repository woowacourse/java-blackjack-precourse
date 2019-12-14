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
 * @apiNote Dealer와 Player 클래스의 상위개념인 HumanInCasino 추상 클래스 입니다.
 * @since : 2019.12.14 토요일
 */
public abstract class HumanInCasino {

    private static final int ELEVEN = 11;
    private final List<Card> cards = new ArrayList<>();

    abstract void addCard(Card card);

    abstract ArrayList<Integer> getAllCardsScore();

    abstract int getCurrentScore();

    abstract int checkHowManyAceInCards();


}
