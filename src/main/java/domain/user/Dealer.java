package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public void gameStart(Card card){
        for(int i=0;i<2;i++){
            addCard(card);
        }
    }

    public void showCard(){
        System.out.println("딜러 : "+cards.get(1));
    }
}
