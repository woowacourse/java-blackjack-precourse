package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private final String name = "딜러";
    private final List<Card> cards = new ArrayList<>();

    public String printMyCards(){
        List<String> cardStrList = new ArrayList<>();
        cards.forEach(element -> cardStrList.add(element.toCardString()));
        return this.name + "의 카드 : " + String.join(",", cardStrList);
    }
}
