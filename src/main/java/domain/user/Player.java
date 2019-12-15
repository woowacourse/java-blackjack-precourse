package domain.user;

import domain.card.Card;
import domain.function.CardScoreCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends CardScoreCalculator{
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    private String getAllCardNames() {
        List<String> allCardNames = new ArrayList<>();
        for (Card card : cards) {
            allCardNames.add(card.getName());
        }
        return String.join(", ", allCardNames);
    }

    public void printAllCards() {
        String cardNames = getAllCardNames();
        System.out.print(String.format("%s 카드: %s", name, cardNames));
    }

    public int getProfit(double profitRate) {
        return (int) (bettingMoney * profitRate);
    }

}
