package domain.user;

import domain.card.Card;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();
    protected final int END_DRAW=17;
    protected final int BLACK_JACK_SCORE=21;
    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현
    public void showCards() {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러");

        for(Card card: cards){
            sb.append(card.toString());
        }

        System.out.println(String.join(",", sb));
    }

    public void checkDrawAgain(Deck deck){
        while(getScoreSum()<END_DRAW){
            System.out.println("딜러는 카드가 16장 이하라 한장의 카드를 더 받았습니다.");
            this.addCard(deck.draw());
        }
        if(getScoreSum()>BLACK_JACK_SCORE){

        }
    }

    public int getScoreSum(){
        int Scoresum = 0;
        for(Card card: cards) {
            Scoresum += card.getSymbol();
        }
        return Scoresum;
    }

    public List<Card> getCards(){
        return this.cards;
    }
}