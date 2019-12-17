/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.user;

import domain.model.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends BlackJackParticipant {

    public Dealer() {}

    public Card getCard() {
        return super.getAllCards().get(0);
    }

}
