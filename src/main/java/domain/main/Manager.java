package domain.main;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Manager {

    List<Player> players = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    List<Card> cards;
    List<Integer> deal_number = new ArrayList<>();
    Random rand = new Random();
    Dealer dealer = new Dealer();
//    boolean dealer_blackjack = false;
//    List<Boolean> player_blackjack;

    public Manager(List<Card> cards){
        this.cards = cards;
    }

    public void start(){
        input_player();
        deal_the_cards_all_player();
        deal_the_cards_to_dealer();

        System.out.println("딜러와 플레이어들에게 2장의 카드를 나누었습니다.");
        print_all_player_and_dealer_cards();
        for(Player player : players){
            loop_action(player);
        }

        deal_the_additional_card_to_dealer();
        print_all_player_and_dealer_cards();
        print_result();

    }

    private void input_player(){
        String input;

        System.out.println("게임에 참여할 사람의 이름을 입력하세요 : (쉼표 기준으로 분리)");
        input = sc.next();
        String[] names = input.split(",");
        for(String name : names ) {
            System.out.println(name + "의 배팅 금액은?");
            double bettingMoney = sc.nextDouble();
            players.add(new Player(name, bettingMoney));
        }
    }

    private int generate_random_number(){
        int random_number = rand.nextInt(52);
        while (cards.contains(random_number)){
            random_number = rand.nextInt(52);
        }
        deal_number.add(random_number);
        return random_number;
    }

    private void deal_the_cards_all_player(){
        int index = 0;
        for(Player player: players){
            deal_the_card(player);
            deal_the_card(player);
//            player_blackjack.add(false);
//
//            if(player.calculate_score() == 21){
//                player_blackjack.remove(index);
//                player_blackjack.add(true);
//            }
//
//            ++index;
        }
    }

    private void deal_the_cards_to_dealer(){
        deal_the_card_to_dealer();
        deal_the_card_to_dealer();

//        if(dealer.calculate_score() == 21){
//            dealer_blackjack = true;
//        }
    }

    private void deal_the_card_to_dealer(){
        int card_number = generate_random_number();
        dealer.addCard(cards.get(card_number));
    }

    private void deal_the_card(Player player){
        int card_number = generate_random_number();
        player.addCard(cards.get(card_number));
    }

    private void print_all_player_and_dealer_cards(){
        System.out.print("dealer : " );
        dealer.print_player_cards();

        for(Player player : players){
            print_player_cards(player);
        }
    }

    private void print_player_cards(Player player){
        System.out.print(player.getName() + " : " );
        player.print_player_cards();
    }


    private boolean deal_the_additional_card_to_player(Player player){
        if(player.calculate_score() > 21){
            return false;
        }
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니요는 n)");
        char answer = sc.next().charAt(0);
        if(answer == 'y'){
            deal_the_card(player);
            print_player_cards(player);
            return true;
        }
        return false;
    }

    private void deal_the_additional_card_to_dealer(){
        if(dealer.calculate_score() < 17){
            deal_the_card_to_dealer();
            System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
        }
    }

    private void loop_action(Player player){
        while(deal_the_additional_card_to_player(player)){

        }
    }

    private void print_result(){
        System.out.println("dealer's score: " + dealer.calculate_score());
        for(Player player : players){
            System.out.println(player.getName() + "'s score : " + player.calculate_score());
        }
    }

//    private void end_game(){
//        double dealer_money = 0;
//        double[] player_money = new double[players.size()];
//        int index = 0;
//
//        if(dealer_blackjack){
//            for(Boolean bool : player_blackjack){
//                if(bool){
//                    player_money[index] = players.get(index).getBettingMoney();
//                }
//                ++index;
//            }
//
//        }
//
//    }

}
