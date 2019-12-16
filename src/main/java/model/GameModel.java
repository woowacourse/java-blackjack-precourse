package model;

import java.util.ArrayList;
import java.util.List;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;

public class GameModel {
    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();
    private static final Deck deck = Deck.getInstance();
    private int totalBettingMoney = 0;

    public GameModel(List<String> userNames, List<Double> userBettingMoneys) {
        for(int i=0;i<userNames.size(); i++){
            players.add(new Player(userNames.get(i),userBettingMoneys.get(i)));
            totalBettingMoney += userBettingMoneys.get(i);
        }
    }

    public void play() {

    }
}
