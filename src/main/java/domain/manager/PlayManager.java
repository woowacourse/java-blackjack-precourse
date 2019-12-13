package domain.manager;

import domain.card.CardFactory;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayManager {
    public final List<Gamer> gamers = new ArrayList<>();
    private final int dealerIndex = 0;
    private final Deck deck = new Deck(CardFactory.create());

    public PlayManager(List<String> playerNameList, List<Integer> bettingMoneyList) {
        gamers.add(new Dealer());
        this.getPlayers(playerNameList, bettingMoneyList);
    }

    private void getPlayers(List<String> playerNameList, List<Integer> bettingMoneyList) {
        for (int i = 0; i < playerNameList.size(); i++) {
            gamers.add(new Player(playerNameList.get(i), bettingMoneyList.get(i)));
        }
    }

    public void PlayGame() {
        setBasicCards();
    }

    private void setBasicCards() {
        int basicCardCount = 2;
        for (int i = 0; i < basicCardCount; i++) {
            dealCards();
        }
    }

    private void dealCards() {
        for (Gamer gamer : gamers) {
            gamer.addCard(deck.giveRandomCard());
        }
    }
}
