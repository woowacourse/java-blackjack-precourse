package domain.user;

import domain.card.Card;
import domain.game.Judgement;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Participant {

    public Dealer() {
    }

    public void openOneCard() {
        System.out.print("\n딜러: " + cards.get(0));
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
        if (cards.size() == Judgement.CONDITION_INIT_CARDS) {
            openOneCard();
        }
    }

    @Override
    public double doBalancing(double totalSettlement) {
        double settlement = totalSettlement;
        System.out.println("딜러: " + settlement);
        return settlement;
    }
}
