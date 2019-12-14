package domain.user;

import domain.card.Card;

/**
 * 딜러와 플레이어를 포괄하는 객체
 */
abstract public class Participant {
    abstract public void addCard(Card card);
}
