package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public String toString() {
        List<String> stringCardList = cards.stream()
                .map(c -> c.toString())
                .collect(Collectors.toList());
        return String.join(", ", stringCardList);
    }

    public int getSumScore() {
        // TODO: 2019-12-16 ACE 11로 계산하는 처리
        int sum = 0;
        for (Card c : cards) {
            sum += c.getScore();
        }
        return sum;
    }

    public boolean isSumUnderCondition(int condition) {
        return getSumScore() <= condition;
    }
}
