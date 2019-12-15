package domain.user;

import domain.card.Card;
import domain.card.CardsOnGame;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Player {
    private static final int DEALER_SHOULD_STAY = 17;


    public Dealer() {
        super("딜러", 0);
    }

    public boolean isDealerCanHit() {
        return getCountedScoreWithAceBonus() < DEALER_SHOULD_STAY;
    }

    public void hitUntilStay(CardsOnGame gameCards) {
        if (isDealerCanHit()) {
            addCard(gameCards.pickUpCard());
            // 뽑음을 알릴것 추가 구현
            hitUntilStay(gameCards);
        }
    }
}
