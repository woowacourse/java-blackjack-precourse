package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Participants {
    private final String name;
    private final double bettingMoney;
//    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

//    public void addCard(Card card) {
//        cards.add(card);
//    }

    // TODO 추가 기능 구현

    public String getName() {
        return this.name;
    }
//    public List<Card> getCards() {
//        return this.cards;
//    }

    public double getBettingMoney() {
        return this.bettingMoney;
    }


    public void decisionMaking() {
        System.out.println(this.getName() + "은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        Scanner sc1 = new Scanner(System.in);
        String goOrStop = sc1.nextLine();
        if (goOrStop.equals("y")) {
            this.cardDistribution();
            System.out.println(this.getName() + "카드: " + this.cardListing());
            this.decisionMaking();
            return;
        }
        System.out.println(this.getName() + "카드: " + this.cardListing());
    }

}
