package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Person {
    public Dealer() {}
    
    @Override
    public void addCard(Card card) {
        super.addCard(card);
    }

    // TODO 추가 기능 구현
}
