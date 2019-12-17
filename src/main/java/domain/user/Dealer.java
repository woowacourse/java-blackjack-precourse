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

    public int getCardSum(){
        int sum = 0;
        int ACE_count = 0;
        int ACE_MAX = 11;
        int ACE_MIN = 1;
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i).isACE()){
                ACE_count++;
                sum += ACE_MAX;
                continue;
            }
            sum += cards.get(i).getCardScore();
        }
        while(sum > 21 && ACE_count != 0){
            sum -= (ACE_MAX - ACE_MIN);
            ACE_count--;
        }

        return sum;
    }

    public String getName(){
        return "딜러";
    }

    @Override
    public String toString(){
        String card = "";
        card = String.join(",", cards.toString());
        return getName() + " : " + card;
    }


}
