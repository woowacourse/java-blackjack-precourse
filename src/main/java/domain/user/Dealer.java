package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User{
    private static final String NAME ="딜러";
    private static final int HIT_BOUNDARY_VALUE = 16;

    public Dealer() {}

    public boolean isHaveToHit() {
        return getTotalScore() <= HIT_BOUNDARY_VALUE;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
