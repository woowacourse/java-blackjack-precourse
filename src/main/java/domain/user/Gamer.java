package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Symbol;

public class Gamer {
    private static final Integer BLACKJACK = 21;

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBurst(){
        return sumOfCard() > BLACKJACK;
    }

    public boolean isBlackJack(){
        return sumOfCard() == BLACKJACK && cards.size() == 2;
    }

    public Integer sumOfCard() {
        int sum = sumWithoutAce();
        if(hasAce() && sumWithoutAce()<= 11)
            sum += 10;
        return sum;
    }

    private int sumWithoutAce() {
        int sum = 0;
        for(int i=0; i<getCards().size(); i++){
            sum += getCards().get(i).getSymbol().getScore();
        }
        return sum;
    }

    protected boolean hasAce() {
        return getCards().stream()
                .map(s->s.getSymbol().getScore() == Symbol.ACE.getScore())
                .collect(Collectors.toList())
                .contains(true);
    }

}
