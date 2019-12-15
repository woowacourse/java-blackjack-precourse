/*
 * Try
 *
 * ver 1.0
 *
 * 2019.12.15
 *
 * CopyRight
 *
 */
package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Try {
    /*
     * Try 클래스 입니다.
     * 카드를 셔플하고 Dealer 및 Person 객체에게
     * 카드를 주는 것을 담당하는 클래스입니다.
     */
    private final List<Card> cards = CardFactory.create();
    private final List<Integer> shuffleIndex = new ArrayList<Integer>();
    private int nextIndex = 0;

    public Try() {}

    public void firstTimePlayer(Player player) {
        for (int index = 0; index < 2; index++) {
            getPlayerCard(player);
        }
    }

    public void firstTimeDealer(Dealer dealer) {
        for (int index = 0; index < 2; index++) {
            getDealerCard(dealer);
        }
    }

    public void shuffleCard() {
        for (int index = 0; index < 51; index++) {
            shuffleIndex.add(index);
        }
        Collections.shuffle(shuffleIndex);
    }

    public int pickCard() {
        return shuffleIndex.get(nextIndex++);
    }

    public void getPlayerCard(Player player) {
        int randomCard = pickCard();
        player.addCard(cards.get(randomCard));
    }

    public void getDealerCard(Dealer dealer) {
        int randomCard = pickCard();
        dealer.addCard(cards.get(randomCard));
    }

    public void keepDealerGoing(Dealer dealer) {
        while(checkDealer(dealer));
    }

    public Boolean checkDealer(Dealer dealer) {
        if (dealer.getScore() <= 16) {
            dealerChance(dealer);
            return true;
        }
        return false;
    }

    public void dealerChance(Dealer dealer) {
        System.out.print( "\n");
        System.out.println("딜러는16이하라한장의카드를더받았습니다.");
        System.out.print( "\n");
        getDealerCard(dealer);
    }

}
