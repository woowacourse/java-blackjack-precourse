package domain.user;

import domain.card.Card;

import java.util.List;

/**
 * 딜러와 플레이어를 포괄하는 객체
 */
abstract public class Participant {
    String name;
    List<Card> cards;

    Participant(String name) {
        this.name = name;
    }

    abstract public void addCard(Card card);
}
