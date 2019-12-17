package domain.winner;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;


public class SelectionWinner {
    static final int BLACKJACK = 21;

    public void startBlackjack(List<Player> player, Dealer dealer) {
        if (dealer.calculateSymbol() == BLACKJACK) {
            for(int i=0;i<player.size();i++){
                if(player.get(i).calculateSymbol()!=BLACKJACK){

                }
            }
        }
    }
}
