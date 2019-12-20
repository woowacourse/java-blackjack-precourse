package domain.user;

import domain.card.Card;
import domain.game.Judgement;

import java.util.Scanner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Participant {
    private final String name;
    private final double bettingMoney;

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
    public void addCard(Card card) {
        super.addCard(card);
        if (cards.size() >= Judgement.CONDITION_INIT_CARDS) {
            showCards(name);
        }
    }

    @Override
    public double doBalancing(double profitPercent) {
        double settlement = profitPercent * bettingMoney;
        System.out.println(name + ": " + settlement);
        return settlement;
    }
}
