package domain.user;

import domain.card.Card;
import domain.game.Blackjack;
import domain.game.Judgement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player implements Participant {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return name;
    }

    public String needMoreCard(Scanner sc) {
        System.out.println("\n" + name + ": 한 장의 카드를 더 받으시겠습니까? (예: y, 아니오: n)");
        return sc.nextLine();
    }


    @Override
    public boolean withInitCards() {
        return cards.size() == Judgement.CONDITION_INIT_CARDS;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
        if (cards.size() >= Judgement.CONDITION_INIT_CARDS) {
            showCards();
        }
    }

    @Override
    public void showCards() {
        StringBuilder msg = new StringBuilder();
        msg.append("\n" + name + " 카드: ");
        for (Card card : cards) {
            msg.append(card + ", ");
        }
        System.out.print(msg.substring(0, msg.length() - 2));
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
    public void showOutcome() {
        showCards();
        System.out.print(" - 결과: " + calScore() + "점");
    }

    @Override
    public double doBalancing(double profitPercent) {
        double settlement = profitPercent * bettingMoney;
        System.out.println(name + ": " + settlement);
        return settlement;
    }
}
