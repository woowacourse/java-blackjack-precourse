package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    /**
     * 게임 시작시 카드 출력 형식(1장만 출력)
     */
    public void showInitCard() {
        System.out.print("\n딜러 : " + cards.get(1));
    }

    /**
     * 게임 종료시 카드 출력 형식
     */
    public void showFinalCard() {
        System.out.print("\n\n딜러카드 : ");
        for (Card card : cards) {
            System.out.print(card + " ");
        }
    }

    public int getScore() {
        if (cards.toString().contains("A")) {
            return getScoreIncludeA();
        }
        return cards.stream().mapToInt(Card::getScore).sum();
    }

    public int getScoreIncludeA() {
        if (cards.stream().mapToInt(Card::getScore).sum() < 12) {
            return cards.stream().mapToInt(Card::getScore).sum() + 10;
        }
        return cards.stream().mapToInt(Card::getScore).sum();
    }
}
