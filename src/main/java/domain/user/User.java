package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void printCards() {
        String commaSeparatedNumbers = cards.stream()
                .map(i -> i.getCardInfo())
                .collect(Collectors.joining(", "));
        System.out.println(commaSeparatedNumbers);
    }

    public void checkCardsEmpty() {
        if (cards.size() == 0) {
            throw new IndexOutOfBoundsException("딜러의 카드가 존재하지 않습니다.");
        }
    }
}
