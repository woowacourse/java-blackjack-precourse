package domain.user;

import domain.card.Card;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Dealer{
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    private final String YES="y";

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
    public void showCards() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        for(Card card: cards){
            sb.append(card.toString());
        }
        System.out.println(String.join(",", sb));
    }

    @Override
    public void checkDrawAgain(Deck deck){
        if(getScoreSum()>BLACK_JACK_SCORE) return;

        System.out.println(this.name + "는 한장 더 받겠습니까? (예는 y, 아니오는 n)");
        Scanner sc= new Scanner(System.in);
        String answer=sc.nextLine();
        if(answer == YES){
            this.addCard(deck.draw());
            printAllCard();
        }
    }

    public void printAllCard(){
        for(Card card: cards){
            System.out.println(this.name+"카드:"+String.join(card.toString()));
        }
    }

    public String getName(){
        return this.name;
    }

    public double getMoney(){
        return this.bettingMoney;
    }
}
