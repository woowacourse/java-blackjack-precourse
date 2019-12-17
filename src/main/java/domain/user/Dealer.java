/*
 * Copyright (c) 2019 by SorinJin
 * All rights reserved.
 *
 * Dealer.java
 * 게임 딜러를 의미하는 객체
 *
 * @author      Sorin Jin
 * @version     1.0
 * @date        16 Dec 2019
 *
 */

package domain.user;

import domain.card.Deck;
import util.Message;

public class Dealer extends User {
    public Dealer() {}

    @Override
    public void addCardIfWant(Deck deck) {
        while (getScore() < 17) {
            System.out.println(Message.MESSAGE_DEALER_GET_CARD.getMessage());
            addCard(deck);
            System.out.println(toStringCards());
        }
        System.out.println(Message.MESSAGE_DEALER_DONT_GET_CARD.getMessage());
    }

    @Override
    public String toStringCards() {
        return "딜러:"+super.toStringCards();
    }
}
