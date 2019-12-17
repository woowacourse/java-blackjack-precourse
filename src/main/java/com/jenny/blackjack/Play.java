package com.jenny.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Play {
    private int TOTAL_CARDS_AMOUNT = 13*4;
    private int INITIAL_CARDS = 2;

    private List<Card> cards;
    private Set<Integer> alreadyUsedCards = new HashSet<>();

    public void distributeInitialCards(Dealer dealer, List<Player> players){
        cards = CardFactory.create();

        getCardForDealer(INITIAL_CARDS, dealer);
        for(Player p : players){
            getCardForPlayer(INITIAL_CARDS, p);
        }
    }

    public void getCardForDealer(int qty, Dealer dealer){
        for(int q = 0; q < qty ; q++){
            int card;
            while(true){
                card = (int)(Math.random() * TOTAL_CARDS_AMOUNT);
                if(!isAlreadyUsed(card)){
                    alreadyUsedCards.add(card);
                    break;
                }
            }
            dealer.addCard(cards.get(card));
        }
    }

    public void getCardForPlayer(int qty, Player player){
        for(int q = 0; q < qty ; q++){
            int card;
            while(true){
                card = (int)(Math.random() * TOTAL_CARDS_AMOUNT);
                if(!isAlreadyUsed(card)){
                    alreadyUsedCards.add(card);
                    break;
                }
            }
            player.addCard(cards.get(card));
        }
    }

    public boolean isAlreadyUsed(int card){
        return alreadyUsedCards.contains(card);
    }

    public void showAllCardStatus(Dealer dealer, List<Player> players){
        StringBuilder msg = new StringBuilder("딜러와 ");
        int cnt = players.size();

        for(int p = 0; p < cnt; p++){
            msg.append(players.get(p).getName() + ",");
        }
        msg.append("에게 " + INITIAL_CARDS + "장의 카드를 나누었습니다.\n");
        System.out.println(msg);
        showDealerFirstCard(dealer);
        for(int p = 0; p < cnt; p++){
            Player pl = players.get(p);
            showOneCardStatus(pl);
        }
    }

    public void showOneCardStatus(Player player){
        System.out.println(player.getName() + " 카드 : " + player.getCards() + "\n");
    }

    public void showDealerFirstCard(Dealer dealer){
        Card dealerFirstCard = dealer.getFirstCard();
        System.out.println("딜러 카드 : " + dealerFirstCard.getSymbol() + " " + dealerFirstCard.getType() + "\n");
    }

    public void showDealerCardStatus(Dealer dealer){
        System.out.println("딜러 카드 : " + dealer.getCards() + "\n");
    }

    public void askAddCard(Dealer dealer, List<Player> players){
        Scanner sc = new Scanner(System.in);
        for(Player p : players){
            while(true){
                System.out.println(p.getName() + "는 한 장의 카드를 더 받겠습니까?(y/n)");
                String input;
                while(true){
                    input = sc.nextLine();
                    if(isValidYN(input)){
                        break;
                    }else{
                        System.out.println("y혹은 n으로만 응답해주십시오.");
                    }
                }
                if(input.toLowerCase().equals("n")){
                    break;
                }else{
                    getCardForPlayer(1, p);
                    showOneCardStatus(p);
                }
            }
        }
        addCardForDealer(dealer);
    }

    public boolean isValidYN(String input){
        input = input.toLowerCase();
        if(input.equals("y") || input.equals("n")){
            return true;
        }
        return false;
    }

    public void addCardForDealer(Dealer dealer){
        int sum = dealer.getSumOfCards();
        if(sum > 16){
            return;
        }
        getCardForDealer(1, dealer);
        System.out.println("딜러는 16이하라 카드를 한장 더 받았습니다.");
    }

}
