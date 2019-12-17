package com.jenny.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Play {
    private int TOTAL_CARDS = 13;
    private int INITIAL_CARDS = 2;

    private List<Card> cards;
    private Set<Integer> alreadyUsedCards = new HashSet<>();

    public void distributeInitialCards(Dealer dealer, List<Player> players){
        cards = CardFactory.create();

        getCardForDealer(INITIAL_CARDS, dealer);
        for(Player p : player){
            getCardForPlayer(INITIAL_CARDS, p);
        }
    }
}
