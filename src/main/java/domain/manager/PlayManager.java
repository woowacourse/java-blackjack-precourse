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

    boolean blackjackStatus = true;

    public PlayManager(List<String> playerNameList, List<Integer> bettingMoneyList) {
        gamers.add(new Dealer());
        this.getPlayers(playerNameList, bettingMoneyList);
    }

    private void getPlayers(List<String> playerNameList, List<Integer> bettingMoneyList) {
        for (int i = 0; i < playerNameList.size(); i++) {
            gamers.add(new Player(playerNameList.get(i), bettingMoneyList.get(i)));
        }
    }

    public void playGame() {
        setBasicCards();
        printAllGamersHand();
        checkMidway();
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

    private void printAllGamersHand() {
        for (Gamer gamer : gamers) {
            System.out.println(gamer.toString());
        }
    }

    private List<Integer> makeScoreList() {
        List<Integer> scoreList = new ArrayList<>();
        for (Gamer gamer : gamers) {
            scoreList.add(gamer.calculateScore());
        }
        return scoreList;
    }

    private List<Integer> makeBlackjackIndexList() {
        List<Integer> scoreList = makeScoreList();
        List<Integer> blackjackIndexList = new ArrayList<>();
        for (int i = 0; i < scoreList.size(); i++) {
            findBlackjack(scoreList, blackjackIndexList, i);
        }
        return blackjackIndexList;
    }
}
