/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package domain.model.user;

import domain.model.card.Card;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 게임 딜러를 의미하는 객체입니다.
 * @since : 2019.12.17 화요일
 */
public class Dealer extends BlackJackParticipant {

    public Dealer() {}

    public Card getCard() {
        return super.getAllCards().get(0);
    }

}
