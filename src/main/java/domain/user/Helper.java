package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String cardsToString(){
        String cardList =  cards.stream().map(card -> String.valueOf(card.toString())).collect(Collectors.joining(", "));
        return "";
    }
}
