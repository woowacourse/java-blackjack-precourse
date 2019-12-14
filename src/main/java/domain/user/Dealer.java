package domain.user;

import domain.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    public Dealer() {}

    public Card showFirstCard() {
        return super.cards.get(0);
    }
}
