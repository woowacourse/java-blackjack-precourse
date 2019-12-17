package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Participants {
//    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

//    public void addCard(Card card) {
//        cards.add(card);
//    }

    // TODO 추가 기능 구현
//    public List<Card> getCards() {return this.cards;}


    public void additionalPick() {
        if (this.getTotal() > 17) {
            System.out.println("딜러는 17이상이라 카드를 받지 않습니다.");
        }
        while (this.getTotal() <= 16) {
            this.cardDistribution();
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }
}
