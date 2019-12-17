package domain.user;

import domain.Name;

public class Dealer extends Player {

    private static final int DEALER_BOUNDARY = 17;

    public Dealer() {
        super(new Name("딜러"));
    }

    public boolean isGetMoreCard() {
        return getScore() < DEALER_BOUNDARY;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }
}
