package domain.user;

import domain.card.Card;
import domain.game.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Contender {
    public static final String NAME = "Dealer";
    public static final int DRAW_NUM = 16;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getBettingMoney() {
        return 0;
    }

    public void drawOneMoreFrom(Deck deck) {
        if (super.getSum() <= DRAW_NUM) {
            System.out.println("\n딜러의 페어 합은 16 이하이므로 한 장의 카드를 더 받았습니다.\n");
            super.addCard(deck.draw());
            return;
        }
        System.out.println("\n딜러의 페어 합은 16을 넘으므로 이대로 진행합니다.\n");
    }
}
