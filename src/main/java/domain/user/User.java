package domain.user;

import domain.card.Card;
import domain.card.RandomCardFactory;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private final List<Card> cards = new ArrayList<>();
    private static final int LIMIT = 21;

    public void addRandomCard() {
        cards.add(RandomCardFactory.create());
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

    public boolean checkAce() {
        return cards.stream().anyMatch(card ->
                card.getSymbol() == Symbol.ACE
        );
    }

    public boolean checkExcess() {
        int sum = cards.stream()
                .map(card -> card.getSymbol().getScore())
                .reduce(Integer::sum)
                .get();
        return sum > LIMIT;
    }

    public boolean isBlackJack() {
        if (!checkAce())     // A가 있고
            return false;

        return cards.stream().anyMatch(card ->      // K,J,Q 중 하나가 존재해야 블랙잭이다.
                card.getSymbol() == Symbol.JACK &&
                        card.getSymbol() == Symbol.KING &&
                        card.getSymbol() == Symbol.QUEEN);
    }

    public boolean isPlayer() {
        return this instanceof Player;
    }
}
