package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserCards {

    private List<Card> cards;
    private static final int LIMIT = 21;

    public UserCards(List<Card> cards) {
        checkCardsEmpty();
        this.cards = cards;
    }

    private void checkCardsEmpty() {
        if (cards.size() == 0) {
            throw new IndexOutOfBoundsException("딜러의 카드가 존재하지 않습니다.");
        }
    }

    public void addRandomCard() {
        cards.add(RandomCardFactory.create());
    }

    public void addFixCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getAceCount() {
        return (int) cards.stream().filter(card ->
                card.getSymbol() == Symbol.ACE
        ).count();
    }

    public boolean checkExcess() {
        Optional<Integer> sum = cards.stream()
                .map(card -> card.getSymbol().getScore())
                .reduce(Integer::sum);
        if (sum.isPresent()) {
            return sum.get() > LIMIT;
        }
        throw new IllegalStateException("카드가 없는 경우가 발생하였습니다.");
    }

    public int calcurateScoreExceptAce() {
        Optional<Integer> scoreExceptAce = cards.stream()
                .filter(card -> card.getSymbol() != Symbol.ACE)
                .map(card -> card.getSymbol().getScore())
                .reduce(Integer::sum);
        if (scoreExceptAce.isPresent()) {
            return scoreExceptAce.get();
        }
        return 0;
    }

    public boolean isHaveTen() {
        return cards.stream().anyMatch(card ->
                card.getSymbol() == Symbol.JACK ||
                        card.getSymbol() == Symbol.KING ||
                        card.getSymbol() == Symbol.QUEEN ||
                        card.getSymbol() == Symbol.TEN);
    }

    public String printCards() {
        return cards.stream()
                .map(Card::getCardInfo)
                .collect(Collectors.joining(", "));
    }
}