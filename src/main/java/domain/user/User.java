package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class User implements Participant {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public User(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return name;
    }

    public String needMoreCard(Scanner sc) {
        System.out.println(name + ": 한 장의 카드를 더 받으시겠습니까? (예: y, 아니오: n)");
        return sc.nextLine();
    }

    @Override
    public int getNumberOfCards() {
        return cards.size();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public void showCards() {
        StringBuilder msg = new StringBuilder();
        msg.append(name + "카드: ");
        for (Card card : cards) {
            msg.append(card + ", ");
        }
        System.out.println(msg.substring(0, msg.length() - 2));
    }

    @Override
    public int calScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    @Override
    public double doBalancing(double result) {
        double settlement = result * bettingMoney;
        System.out.println(name + ": " + settlement);
        return settlement;
    }
}
