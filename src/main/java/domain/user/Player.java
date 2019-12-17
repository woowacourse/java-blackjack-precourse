package domain.user;

import domain.BettingMoney;
import domain.Name;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.InputView.DELIMITER;

public class Player {

    public static final int BLACK_JACK = 21;
    private static final int ACE = 1;
    private static final int TEN = 10;
    private static final double ANYTHING = 0;

    private final Name name;
    private BettingMoney bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player( Name name ) {
        this.name = name;
    }

    public void addCard( Card card ) {
        cards.add(card);
    }

    public void setBettingMoney( double bettingMoney ) {
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public String getName() {
        return name.getName();
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public int getScore() {
        int score = cards.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .orElse(0);
        if (score <= 11 && hasAce()) {
            score = applyAce(score);
        }
        return score;
    }

    public String getCardToString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER));
    }

    public boolean isBlackJack() {
        return getScore() == BLACK_JACK;
    }

    private boolean hasAce() {
        int aceCount = (int) cards.stream()
                .filter(card -> card.getScore() == ACE)
                .count();

        return aceCount >= ACE;
    }

    private int applyAce( int score ) {
        int aceCount = (int) cards.stream()
                .filter(card -> card.getScore() == ACE)
                .count();
        if (aceCount == 3) {
            return BLACK_JACK;
        }
        return score + TEN;
    }

    public boolean isBust() {
        int score = cards.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .orElse(0);
        return score > BLACK_JACK;
    }

    public double getRewardMoney( boolean isDealerBlackJack, boolean isWin ) {
        if (isDealerBlackJack) {
            return getBlackJackMoney();
        }
        if (isBlackJack()) {
            return bettingMoney.blackJack();
        }
        if (isWin) {
            return bettingMoney.earn();
        }
        return bettingMoney.loseGame();
    }

    private double getBlackJackMoney() {
        if (isBlackJack()) {
            return ANYTHING;
        }
        return bettingMoney.loseGame();
    }

    public boolean isDealer() {
        return false;
    }

    public boolean isPlayer() {
        return true;
    }
}
