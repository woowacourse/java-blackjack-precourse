package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Contender {
    public static final String NAME = "Dealer";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getBettingMoney() {
        return 0;
    }

    // TODO 추가 기능 구현
}
