/*
 * @(#)User.java 2019/12/17
 *
 * Copyright (c) 2019 Geunwon Lim
 * All rights reserved.
 */

package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import common.BlackjackConfig;
import domain.card.Card;
import domain.card.Symbol;

public abstract class User {
    protected final List<Card> cards = new ArrayList<>();
    protected double profit = 0;

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {

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

        for (int i = 0; i < aces.size(); i++) {
            sum += calculateSurplus(sum);
        }
        return sum;
    }

    private int calculateSurplus(int sum) {
        if (BlackjackConfig.BLACKJACK < sum + BlackjackConfig.SURPLUS_OF_ACE) {
            return 0;
        }
        return BlackjackConfig.SURPLUS_OF_ACE;
    }

    private Map<Boolean, List<Card>> splitCards(List<Card> cards) {
        return cards.stream()
                .collect(Collectors.partitioningBy(card -> card.getSymbol().equals(Symbol.ACE)));
    }

    private boolean matchesA() {
        return true;
    }

    public boolean isBust() {
        int score = calculateScore();
        return BlackjackConfig.BLACKJACK < score;
    }

    ;

    public boolean isBlackjack() {
        int score = calculateScore();
        return cards.size() == BlackjackConfig.DEFAULT_CARD_NUMBER && score == BlackjackConfig.BLACKJACK;
    }

    public double getProfit() {
        return profit;
    }
}
