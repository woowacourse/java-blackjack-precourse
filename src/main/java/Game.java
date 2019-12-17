import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private Dealer dealer;
    private List<Card> deck;
    private ArrayList<Integer> assignedCard;

    public Game(String[] users) {
        int betMoney;
        Scanner scan = new Scanner(System.in);
        players = new ArrayList<>();

        for(String user:users){
            System.out.println("input the "+user+"'s betting money");
            betMoney = scan.nextInt();
            players.add(new Player(user, betMoney));
        }

        dealer = new Dealer();
        deck = CardFactory.create();
    }

    public void startGame(){
        int nextCard;
        StringBuilder stringBuilder = new StringBuilder("Dealer");
        assignedCard = new ArrayList<>();

        for(Player player: players){
            distributeCard(player,2);
            stringBuilder.append(", "+player.getName());
        }
        distributeCard(dealer,2);
        stringBuilder.append(" are get 2 cards.");

        if(checkBlackJack()){
            blackJackEnd();
        }
        gameProcess();
    }

    private void gameProcess(){
        for(Player player:players){
            while (true){

            }
        }
    }

    private int getNextCard(){
        Random rand = new Random();
        int returnCard;
        while (true){
            returnCard = rand.nextInt(deck.size());
            if(!isAssigned(returnCard)){
                return returnCard;
            }
        }
    }

    private boolean isAssigned(int cardNum){
        for(Integer card:assignedCard){
            if(card == cardNum){
                return true;
            }
        }
        return false;
    }

    private void distributeCard(User user, int count){
        int nextCard;

        for(int i = 0; i<count; i++){
            nextCard = getNextCard();
            user.addCard(deck.get(nextCard));
            assignedCard.add(nextCard);
        }
    }

    private boolean checkBlackJack(){
        for(Player player:players){
            if(player.getMaxScore() == 21){
                return true;
            }
        }
        return false;
    }

    private void blackJackEnd(){
        ArrayList<Integer> profit = new ArrayList<Integer>(players.size());

        if(dealer.getMaxScore() == 21){
            printProfit(profit,0);
        }
    }

    private void printProfit(ArrayList<Integer> playerProf, Integer dealerProf){
        int index = 0;
        System.out.println("##Final Profits");
        System.out.println("Dealer: "+dealerProf);

        for(Player player:players){
            System.out.println(player.getName()+": "+playerProf.get(index));
            index++;
        }
    }

    private void printUserStatus(User user){

    }
}
