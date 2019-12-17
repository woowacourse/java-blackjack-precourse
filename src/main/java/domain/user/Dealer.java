package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends People {

    static final int MAXIMUM_VALUE = 21;

    public Dealer() {
    }


    // TODO 추가 기능 구현

    public boolean isDealerWinner(Player player) {
        if (player.getTotalNumber() > MAXIMUM_VALUE
                || this.getTotalNumber() > player.getTotalNumber())
            return true;
        if (this.getTotalNumber() == player.getTotalNumber())
            return cardCountCompare(player);
        return false;
    }

    public boolean isTie(Player player) {
        return (player.getTotalNumber() == this.getTotalNumber())
                && (player.cardCount() == this.cardCount());
    }

    private boolean cardCountCompare(Player player){
        return this.cardCount() < player.cardCount();
    }
}
