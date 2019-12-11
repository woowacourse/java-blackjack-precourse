import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.user.*;
/**
 * CardGame
 */
public class CardGame {
    
    CardDeck deck = new CardDeck();
    List<Card> cards = deck.getDeck();
    List<Player> players = new ArrayList<Player>();
    Dealer dealer = new Dealer();

    public CardGame(){
    }

    public void start(){
        createUsers();
        giveInitialCards();
        for (Player each : players){
            playerProcess(each);
            System.out.println(totalAmount(each));
        }
        dealerProcess(dealer);
        System.out.println(totalAmount(dealer));
    }

    public void playerProcess(Player player){
        while(true){
            if (totalAmount(player)>=21){
                return;
            }
            System.out.println(player.getName()+"는 한장의 카드를 더 받겠습니까?");
            if (true){
                giveCard(player);
            }   
        } 
    }

    public void dealerProcess(Dealer deal){
        if (totalAmount(dealer)<=16){
            giveCard(dealer);
        }       
    }


    public void createUsers(){
        String[] name = {"pobi","jason"};
        int[] bet = {10000,20000};
        for (int i=0; i<name.length; i++){
            players.add(new Player(name[i], bet[i]));
        }
    }

    public void giveInitialCards(){
        for (int i =0; i<2; i++){
            for(Player each : players){
                each.addCard(deck.pick());
            }
            dealer.addCard(deck.pick());
        }
    }

    public void giveCard(User user){
        user.addCard(deck.pick());
        user.showCards();
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