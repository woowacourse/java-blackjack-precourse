package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Symbol;

public class Gamer {
    private static final Integer BLACKJACK = 21;
    private static final int TWO_CARD_BLACKJACK = 2;
    private static final int MAX_SUM_WITHOUT_ACE = 11;
    private static final int PLUS_ACE = 10;
    private static final int ZERO = 0;

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBurst() {
        return sumOfCard() > BLACKJACK;
    }

    public boolean isBlackJack() {
        return sumOfCard() == BLACKJACK && cards.size() == TWO_CARD_BLACKJACK;
    }

    public Integer sumOfCard() {
        int sum = sumWithoutAce();
        if (hasAce() && sumWithoutAce() <= MAX_SUM_WITHOUT_ACE)
            sum += PLUS_ACE;
        return sum;
    }

    private int sumWithoutAce() {
        int sum = ZERO;
        for (int i = 0; i < getCards().size(); i++) {
            sum += getCards().get(i).getSymbol().getScore();
        }
        return sum;
    }

    protected boolean hasAce() {
        return getCards().stream()
                .map(s -> s.getSymbol().getScore() == Symbol.ACE.getScore())
                .collect(Collectors.toList())
                .contains(true);
    }

}
