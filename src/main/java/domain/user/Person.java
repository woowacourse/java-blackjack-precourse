package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private final List<Card> cards = new ArrayList<>();

    public void printMyCards(String name){
        List<String> cardStrList = new ArrayList<>();
        cards.forEach(element -> cardStrList.add(element.toCardString()));
        System.out.println(name + "의 카드 : " + String.join(", ", cardStrList));
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void takeCards(int cardCount, List<Card> originalCardList){
        for(int i = 0 ; i < cardCount ; i++){
            int randomNumber = (int) (Math.random() * originalCardList.size());
            addCard(originalCardList.get(randomNumber));
        }
    }
}
