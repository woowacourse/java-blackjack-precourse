package domain.game;

import domain.card.Card;
import domain.card.Symbol;
import domain.user.Dealer;

public class Scorer {
    public static int sumCardScores(Dealer dealer) {
        return dealer.getCards().stream()
                                .map(Card::getSymbol)
                                .mapToInt(Symbol::getScore)
                                .sum();
    }
}
