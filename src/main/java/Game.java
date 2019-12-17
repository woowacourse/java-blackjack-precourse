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
    private ArrayList<Integer> assignedCard = new ArrayList<>();

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
        StringBuilder stringBuilder = new StringBuilder("Dealer");

        for(Player player: players){
            distributeCard(player,2);
            stringBuilder.append(", "+player.getName());
        }
        distributeCard(dealer,2);
        stringBuilder.append(" are get 2 cards.");
        System.out.println(stringBuilder.toString());
        printUserStatus();

        if(checkBlackJack()){
            blackJackEnd();
        }
        gameProcess();
    }

    private void gameProcess(){

        for(Player player:players){
            playerProcess(player);
        }
    }

    private void playerProcess(Player player){
        Scanner scanner = new Scanner(System.in);
        String inputCommand;

        while (true){
            System.out.println(player.getName()+" get one more card? (y/n)");
            inputCommand = scanner.nextLine();

            if(inputCommand.equals("n")){
                break;
            }
            distributeCard(player,1);
            System.out.println(player.getName()+" card: "+player.getCardString());

            if(player.getNormalScore() > 21){
                System.out.println("Over 21");
                player.profit -= player.getBettingMoney();
                dealer.profit += player.getBettingMoney();
                break;
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

        if(dealer.getMaxScore() == 21){
            printProfit();
            return;
        }

        for(Player player:players){

            if(player.getMaxScore() == 21){
                dealer.profit -= player.getBettingMoney()*1.5;
                player.profit += player.getBettingMoney()*1.5;
            }
        }
        printProfit();
    }

    private void printProfit(){
        int index = 0;
        System.out.println("##Final Profits");
        System.out.println("Dealer: "+ dealer.profit);

        for(Player player:players){
            System.out.println(player.getName()+": "+ player.profit);
            index++;
        }
    }

    private void printUserStatus(){
        StringBuilder stringBuilder = new StringBuilder("Dealer card: ");
        stringBuilder.append(dealer.getCardString());
        System.out.println(stringBuilder.toString());

        for(Player player:players){
            stringBuilder = new StringBuilder(player.getName()+" card: ");
            stringBuilder.append(player.getCardString());
            System.out.println(stringBuilder.toString());
        }
    }
}
