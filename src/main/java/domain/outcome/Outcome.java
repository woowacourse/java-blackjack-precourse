package domain.outcome;

import domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class Outcome {
    private final String name;
    private List<Card> cards;
    private int benefit;
    private static final int CHANGE_SIGN = -1;

    public Outcome(String name, double benefit, List<Card> cards) {
        this.name = name;
        this.cards = cards;
        this.benefit = (int) benefit;
    }

    public String printOutcome() {
        StringBuilder sb = new StringBuilder();
        sb.append(name)
                .append(" : ")
                .append(benefit)
                .append("\n");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public double getBenefit() {
        return benefit;
    }

    public void setDealerBenefit(double benefit) {
        if (isDealer() == false) {
            throw new IllegalStateException("Dealer가 아니면 benefit을 변경할 수 없습니다.");
        }
        this.benefit = (int) benefit * (CHANGE_SIGN);
    }

    public boolean isNameMatch(String name) {
        return this.name.equals(name);
    }

    public boolean isDealer() {
        return this.name.equals("Dealer");
    }

    private String printCards() {
        return cards.stream()
                .map(i -> i.getCardInfo())
                .collect(Collectors.joining(", "));
    }
}
