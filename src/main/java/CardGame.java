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
    final static int BLACKJACK_SCORE = 21;
    final static int DEALER_HANDI = 16;
    CardDeck deck = new CardDeck();
    List<Card> cards = deck.getDeck();
    List<Player> players = new ArrayList<Player>();
    Dealer dealer = new Dealer();
    double[] money;
    int [] sum;

    public void start(){
        createUsers();
        giveInitialCards();
        for (Player each : players){
            playerProcess(each);
            System.out.println(totalAmount(each));
        }
        dealerProcess(dealer);
        System.out.println(totalAmount(dealer));
        results();

        System.out.println("\n##최종 수익");
        System.out.println("dealer : " + money[0]);
        for (Player each : players){
            System.out.println(each.getName() + " : " + money[players.indexOf(each)+1]);
        }
    }

    public void results(){
        sum[0] = totalAmount(dealer);
        System.out.println("dealer : "+ sum[0]);
        for (Player each : players){
            sum[players.indexOf(each)+1] = totalAmount(each);
            System.out.println(each.getName()+ " : " + sum[players.indexOf(each)+1]);
            winOrLose(each);
        }
    }

    public void winOrLose(Player p){
        //dealer went over 21
        if (sum[0]>BLACKJACK_SCORE){
            money[players.indexOf(p)+1]=(p.getBettingMoney());
            money[0] = money[0]-(p.getBettingMoney());
            return;
        }
        //player went over 21
        if (sum[players.indexOf(p)+1]>BLACKJACK_SCORE){
            money[players.indexOf(p)+1]=-(p.getBettingMoney());
            money[0] = money[0]+(p.getBettingMoney());
            return;
        }
        //dealer won
        if (sum[0]>sum[players.indexOf(p)+1]){
            money[players.indexOf(p)+1]=-(p.getBettingMoney());
            money[0] = money[0]+(p.getBettingMoney());
            return;
        }
        //player won
        if (sum[players.indexOf(p)+1]>=sum[0]){
            money[players.indexOf(p)+1]=-(p.getBettingMoney());
            money[0] = money[0]+(p.getBettingMoney());
            return;
        }
        return;
       
                
    }

    public void playerProcess(Player player){
        if (totalAmount(player)==BLACKJACK_SCORE){
            if (totalAmount(dealer)==BLACKJACK_SCORE){
                return;
            }
            money[players.indexOf(player)+1] = player.winBlackJackFirst();
            money[0] = money[0]-(player.winBlackJackFirst());
            return;
        }
        while(true){
            if (totalAmount(player)>=BLACKJACK_SCORE){
                
                return;
            }
            System.out.println(player.getName()+"는 한장의 카드를 더 받겠습니까?");
            if (true){
                giveCard(player);
            }   
        } 
    }

    public void dealerProcess(Dealer deal){
        if (totalAmount(dealer)<=DEALER_HANDI){
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            giveCard(dealer);
            return;
        }       
        System.out.println("딜러는 17이상이라 카드를 받지 않았습니다.");
    }


    public void createUsers(){
        String[] name = {"pobi","jason"};
        int[] bet = {10000,20000};
        for (int i=0; i<name.length; i++){
            players.add(new Player(name[i], bet[i]));
        }
        money = new double[players.size()+1];
        sum = new int[players.size()+1];
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
            if(nums[i]==1){
                hasAce = true;
            }
        }
        if (hasAce && total<=11){
            total = total+10;
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