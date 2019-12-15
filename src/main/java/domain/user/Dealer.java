package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void printCards() {
        System.out.println("딜러: " + printCardValue());
    }

    @Override
    public String printCardValue() {
        ArrayList<String> CardValue = getCardValue();
        return String.join(", ", CardValue.get(0));
    }

    // TODO 추가 기능 구현
}
