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
        assignedCard = new ArrayList<>();

        for(Player player: players){
            distributeCard(player,2);
        }
        distributeCard(dealer,2);

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
}
