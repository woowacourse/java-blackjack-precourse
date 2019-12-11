import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

/**
 * Scores
 */
public class Scores {
    List<Player> players;
    Dealer dealer;
    int[] score;

    public Scores(List<Player>players, Dealer dealer){
        this.dealer = dealer;
        this.players = players;
        this.score = new int[players.size()+1];
    }
    
    public int totalAmount(User user){
        int[] nums = transferNum(user.getCardList());
        int total = 0;
        boolean hasAce = false;
        for (int i=0; i<nums.length; i++){
            total = total + nums[i];
            if (nums[i]==1){
                hasAce = true;
            }
        if (hasAce == true && total<=11){
            total = total+10;
            }
        }
        return total;
    }

    public int[] transferNum(List<Card> cardList){
        int[] nums = new int[cardList.size()];
        for (int i=0; i<cardList.size(); i++){
            nums[i] = cardList.get(i).getSymbol().getScore();
        }
        
        return nums;
    }
    
}