package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends BlackjackMember {
    private static final String DEALER = "딜러";

    public Dealer() {}

    // TODO 추가 기능 구현
    @Override
    public String getName() {
        return DEALER;
    }
}
