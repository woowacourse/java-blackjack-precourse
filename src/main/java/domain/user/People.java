package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import domain.card.Card;

public class People {
    private final List<Card> cards = new ArrayList<>();

    public People() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotalNumber() {
        int result = 0;
        for (Card card : cards) {
            result += card.getSymbol().getScore();
        }
        return result;
    }

    public String getCardList(){
        return cards.stream()
            .map((x) -> {return x.getCardString();})
            .collect(Collectors.joining(","));
    }
}