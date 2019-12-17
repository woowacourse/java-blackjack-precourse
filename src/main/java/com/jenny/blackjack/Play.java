package com.jenny.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

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
        getCard(INITIAL_CARDS, dealer);
        for(Player p : players){
            getCard(INITIAL_CARDS, p);
        }
    }

    public void getCard(int qty, User user){
        for(int q = 0; q < qty ; q++){
            int card;
            while(true){
                card = (int)(Math.random() * TOTAL_CARDS_AMOUNT);
                if(!isAlreadyUsed(card)){
                    alreadyUsedCards.add(card);
                    break;
                }
            }
            user.addCard(cards.get(card));
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

    public void showOneCardStatus(User user){
        StringBuilder sb = new StringBuilder(user.getCards());
        System.out.println(user.getName() + " 카드 : " + sb.substring(0, sb.length()-1));
    }

    public void showDealerFirstCard(Dealer dealer){
        Card dealerFirstCard = dealer.getFirstCard();
        System.out.println("딜러 카드 : " + dealerFirstCard.getSymbol() + " " + dealerFirstCard.getType());
    }

    public void askAddCard(Dealer dealer, List<Player> players){
        Scanner sc = new Scanner(System.in);
        Result result = new Result();
        for(Player p : players){
            while(true){
                System.out.println("현재 " + p.getName() + "님의 총합은 " + p.getSumOfCards() + "입니다.");
                if(result.calcResultValue(p) == 22){
                    System.out.println("블랙잭을 만족합니다.");
                    break;
                }
                System.out.println("한 장의 카드를 더 받겠습니까?(y/n)");
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
                    getCard(1, p);
                    showOneCardStatus(p);
                    if(isBurst(p)){
                        System.out.println("21 초과로 패배하셨습니다.");
                        break;
                    }
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
        getCard(1, dealer);
        System.out.println("\n딜러는 카드 총합이 16이하라 카드를 한장 더 받았습니다.");
    }

    public boolean isBurst(User user){
        return user.getSumOfCards() > 21;
    }
}
