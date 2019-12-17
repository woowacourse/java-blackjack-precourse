package application.domain.user;

import application.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User{
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String getFirstShuffleCardInfo() {
        return cards.get(0).toString();
    }

    // TODO 추가 기능 구현
}
