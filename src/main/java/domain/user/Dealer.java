package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    public Dealer() {
    }

    // TODO 추가 기능 구현
    public void printFirstCard() {
        super.checkCardsEmpty();
        System.out.println("딜러: " + super.getCards().get(0).getCardInfo());
    }
}
