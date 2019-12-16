package domain.user;

import domain.card.Card;
import domain.card.RandomCardFactory;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

abstract public class User {
    private final List<Card> cards = new ArrayList<>();
    private static final int LIMIT = 21;
    private static final int ONE = 21;

    public void addRandomCard() {
        cards.add(RandomCardFactory.create());
    }

    public void addFixCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String printCards() {
        return cards.stream()
                .map(i -> i.getCardInfo())
                .collect(Collectors.joining(", "));
    }

    public void checkCardsEmpty() {
        if (cards.size() == 0) {
            throw new IndexOutOfBoundsException("딜러의 카드가 존재하지 않습니다.");
        }
    }

    public int getAceCount() {
        return (int) cards.stream().filter(card ->
                card.getSymbol() == Symbol.ACE
        ).count();
    }

    public boolean checkExcess() {
        int sum = cards.stream()
                .map(card -> card.getSymbol().getScore())
                .reduce(Integer::sum)
                .get();
        return sum > LIMIT;
    }

    public boolean isBlackJack() {
        if (getAceCount() == ONE)     // A가 있고
            return false;

        return cards.stream().anyMatch(card ->      // K,J,Q 중 하나가 존재해야 블랙잭이다.
                card.getSymbol() == Symbol.JACK &&
                        card.getSymbol() == Symbol.KING &&
                        card.getSymbol() == Symbol.QUEEN);
    }

    public boolean isPlayer() {
        return this instanceof Player;
    }

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public int calcurateScore() {
        int scoreExceptAce = calcurateScoreExceptAce();
        return startCalcurateAceScoreLoop(scoreExceptAce);
    }

    public int startCalcurateAceScoreLoop(int score) {
        for (int i = getAceCount(); i > 0; i--) {
            score += calcureateAceScore(score);
        }
        return score;
    }

    private int calcureateAceScore(int score) {
        if (score + Symbol.ACE.getBonusScore() > LIMIT) {
            return Symbol.ACE.getScore();
        }

        return Symbol.ACE.getBonusScore();
    }

    private int calcurateScoreExceptAce() {
        Optional<Integer> scoreExceptAce = cards.stream()
                .filter(card -> card.getSymbol() != Symbol.ACE)
                .map(card -> card.getSymbol().getScore())
                .reduce(Integer::sum);
        if (scoreExceptAce.isPresent()) {
            return scoreExceptAce.get();
        }
        return 0;
    }

    abstract public void printUserInfo();

    abstract public void printFinalOutput();
}
