package domain.user;

import domain.Game;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

import java.util.*;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    private final List<Card> cards = super.cards;

    public Dealer() {
    }

    // TODO 추가 기능 구현

    public void firstDecision() {
        int sum = getAceSum();

        printDecision(sum);
        if (sum <= Game.DRAW_FLAG) {
            Game.drawFromDeck(this, 1);
        }
    }

    public void printDecision(int sum) {
        if (sum <= Game.DRAW_FLAG) {
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
            return;
        }
        System.out.println("\n딜러는 17이상이라 카드를 받지 않았습니다.\n");
    }

    @Override
    public void printNameAndCards() {
        List<String> list = new ArrayList<>();
        String result;

        for (Card card : cards) {
            list.add(card.toString());
        }
        result = String.join(",", list);
        System.out.println("딜러카드: " + result);
    }

    @Override
    public void printNameAndCardsAndSum() {
        List<String> list = new ArrayList<>();
        String result;

        for (Card card : cards) {
            list.add(card.toString());
        }
        result = String.join(",", list);
        System.out.println("딜러카드: " + result + " - 결과: " + getAceSum());
    }
}
