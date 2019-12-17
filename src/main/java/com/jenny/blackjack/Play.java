package com.jenny.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.HashSet;
import java.util.List;
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

    public void showCardStatus(Dealer dealer, List<Player> players){
        StringBuilder msg = new StringBuilder("딜러와 ");
        int cnt = players.size();

        for(int p = 0; p < cnt; p++){
            msg.append(players.get(p).getName() + ",");
        }
        msg.append("에게 " + INITIAL_CARDS + "장의 카드를 나누었습니다.\n");
        Card dealerFirstCard = dealer.getFirstCard();
        msg.append("딜러 카드 : " + dealerFirstCard.getSymbol() + " " + dealerFirstCard.getType() + "\n");

        for(int p = 0; p < cnt; p++){
            Player pl = players.get(p);
            msg.append(pl.getName() + " 카드 : " + pl.getCards() + "\n");
        }
        System.out.println(msg);
    }


}
