package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Dealer {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현
    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public void showCard(){
        System.out.print(name+" : ");
        for (Card card :this.cards){
            System.out.print(card+" ");
        }
        System.out.println("");
    }

}
