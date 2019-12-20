package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserCards {
    private static final String COMMA = ",";
    private final List<Card> cards;

    public UserCards() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addRandomCard() {
        cards.add(RandomCardFactory.create());
    }

    public void addFixCard(Card card) {
        cards.add(card);
    }

    public Score calcurateScore() {
        Score exceptAceBonusScore = calcurateExceptAceBonusScore();
        return addAceBonusIfNotBust(exceptAceBonusScore);
    }

    private Score calcurateExceptAceBonusScore() {
        return cards.stream()
                .map(card -> card.getSymbol().getScore())
                .reduce((score1, score2) -> score1.calculate(score2.getScore()))
                .get();
    }

    private Score addAceBonusIfNotBust(Score noneAceBonusScore) {
        int aceCount = getAceCount();
        while (aceCount-- > 0) {
            noneAceBonusScore.plusTenIfNotBust();
        }
        return noneAceBonusScore;
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(card -> card.isAce())
                .count();
    }

    // TODO : UI 로직 옮긱기
    public String printCards() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA));
    }
}