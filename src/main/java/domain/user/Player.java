package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.manager.Manual;
import view.output.Output;

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

        try {
            Card card = deck.getDeck().stream().findAny().orElseThrow(IllegalStateException::new);
            deck.removeCardFromDeck(card);
            addCard(card);
        } catch (IllegalStateException e) {
            new Output().showMessageCardEmptyClose();
            System.exit(0);
        }
    }

    public int calculateScore() {
        int symbolCount = cards.stream()
                .mapToInt(num -> num.getSymbol().getScore())
                .filter(num -> num > Symbol.ACE.getScore())
                .reduce(Integer::sum).orElse(0);

        if (hasNotAce()) {
            return symbolCount;
        }

        return calculateScoreAce(symbolCount);
    }

    private boolean hasNotAce() {
        return cards.stream()
                .mapToInt(num -> num.getSymbol().getScore())
                .noneMatch(num -> num == Symbol.ACE.getScore());
    }

    public int calculateScoreAce(int symbolCount) {
        if (containAce() > Manual.EMPTY.getValue() && symbolCount <= Manual.SAFE.getValue()) {
            return symbolCount + Symbol.TEN.getScore() + (Symbol.ACE.getScore() * containAce());
        }

        if (containAce() > Manual.EMPTY.getValue()) {
            return symbolCount + (Symbol.ACE.getScore() * containAce());
        }

        return symbolCount;
    }

    public int containAce() {
        return (int) cards.stream()
                .mapToInt(num -> num.getSymbol().getScore()).filter(num -> num == Symbol.ACE.getScore()).count();
    }

    boolean hasBlackjackPlayer(Player player) {
        return player.calculateScore()
                == Manual.BLACKJACK.getValue() && player.getCards().size() == Manual.Pair.getValue();
    }
}
