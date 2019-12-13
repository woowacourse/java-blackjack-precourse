package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void pickCardFromDeck(Deck deck) {
        Collections.shuffle(deck.getDeck());
        Card card = deck.getDeck()
                .stream()
                .findAny()
                .orElseThrow(IllegalStateException::new);
        deck.removeCardFromDeck(card);
        addCard(card);
    }

    public int calculateScore(int blackJackNumber) {
        int symbolCount = cards.stream()
                .mapToInt(num -> num.getSymbol().getScore())
                .filter(num -> num > Symbol.ACE.getScore())
                .reduce(Integer::sum).orElse(0);

        return calculateAce(blackJackNumber, symbolCount);
    }

    private int calculateAce(int blackJackNumber, int symbolCount) {
        if (calculateScoreAce() && symbolCount <= blackJackNumber) {
            return symbolCount + Symbol.ACE.getScore() + Symbol.TEN.getScore();
        }
        if (calculateScoreAce()) {
            return symbolCount + Symbol.ACE.getScore();
        }
        return symbolCount;
    }

    private boolean calculateScoreAce() {
        return cards.stream()
                .mapToInt(num -> num.getSymbol()
                        .getScore())
                .anyMatch(num -> num == Symbol.ACE.getScore());
    }
}
