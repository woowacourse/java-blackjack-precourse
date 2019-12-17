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

    public Card getFirstCard(){
        return cards.get(0);
    }

    public String getCards(){
        int size = cards.size();
        StringBuilder msg = new StringBuilder();

        for(int idx = 0; idx < size; idx++){
            Card card = cards.get(idx);
            msg.append(card.getSymbol() + " " + card.getType() + ",");
        }
        return msg.toString();
    }

    public List<Card> getCardsList(){
        return cards;
    }

    public int getSumOfCards(){
        int sum = 0;
        int size = cards.size();

        for(Card card : cards){
            sum += card.getSymbol().getScore();
        }
        return sum;
    }
}
