package domain.user;

import domain.card.Card;
import domain.card.CardConfig;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class User {
    protected final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateSumOfCards() {

        List<Card> aces = splitCards(cards).get(matchesA());
        List<Card> cardsWithoutAce = splitCards(cards).get(!matchesA());

        int sum = calculateCardsWithoutAces(cardsWithoutAce);
        sum = calculateAces(aces, sum);

        return sum;
    }

    private int calculateCardsWithoutAces(List<Card> cardsWithoutAce) {
        int sum = 0;
        for (Card card : cardsWithoutAce) {
            sum += card.getSymbol().getScore();
        }
        return sum;
    }

    private int calculateAces(List<Card> aces, int sum) {
        for (Card ace : aces) {
            sum += ace.getSymbol().getScore();
        }

        for (Card ace : aces) {
            sum += calculateSurplus(sum, ace);
        }
        return sum;
    }
    private int calculateSurplus(int sum, Card ace) {
        if ( CardConfig.BLACKJACK < sum + CardConfig.SURPLUS_OF_ACE )  {
            return 0;
        }
        return CardConfig.SURPLUS_OF_ACE;
    }

    ;
    private Map<Boolean, List<Card>> splitCards(List<Card> cards) {

        return cards.stream()
                .collect(Collectors.partitioningBy(card -> card.getSymbol().equals(Symbol.ACE)));
    }
    private boolean matchesA() {
        return true;
    }
}
