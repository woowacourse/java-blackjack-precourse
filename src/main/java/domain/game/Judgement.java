package domain.game;

import domain.user.Dealer;
import domain.user.Participant;
import domain.user.Player;

public class Judgement {
    public static final int CONDITION_SCORE = 21;
    public static final int CONDITION_INIT_CARDS = 2;
    public static final int CONDITION_DEALER_SCORE = 17;
    public static final double BLACKJACK_WIN = 1.5;
    public static final double WIN = 1;
    public static final double DRAW = 0;
    public static final double LOSE = -1;


    public boolean isBlackJack(Participant participant) {
        return (participant.calScore() == CONDITION_SCORE) && participant.withInitCards();
    }

    public boolean isBust(Participant participant) {
        if (participant.calScore() > CONDITION_SCORE) {
            return true;
        }
        return false;
    }

    public double winOrLose(Player player, Dealer dealer) {
        if (isBust(player)) {
            return inBustCase();
        }
        if (isBlackJack(player)) {
            return inBlackjackCase(dealer);
        }
        return inGeneralCase(player, dealer);
    }

    public double inBustCase() {
        return LOSE;
    }

    public double inBlackjackCase(Dealer dealer) {
        if (isBlackJack(dealer)) {
            return DRAW;
        }
        return BLACKJACK_WIN;
    }

    public double inGeneralCase(Player player, Dealer dealer) {
        if (isBlackJack(dealer)) {
            return LOSE;
        }
        if (isBust(dealer)) {
            return WIN;
        }
        if (player.calScore() > dealer.calScore()) {
            return WIN;
        }
        if (player.calScore() == dealer.calScore()) {
            return DRAW;
        }
        return LOSE;
    }
}

