package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends People {

    final static int MAXIMUM_VALUE = 21;

    public Dealer() {
    }


    // TODO 추가 기능 구현

    public boolean isDealerWinner(Player player) {
        if (player.getTotalNumber() <= MAXIMUM_VALUE
                && this.getTotalNumber() < player.getTotalNumber())
            return true;
        return false;
    }

}
