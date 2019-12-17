package domain.manager;

import domain.card.CardFactory;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayManager {
    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();
    private final Deck deck = new Deck(CardFactory.create());
    private int dealerIndex;
    private double[] benefitArray;
    private List<Integer> blackJackPlayerIndexList;

    public PlayManager(List<String> playerNameList, List<Integer> bettingMoneyList) {
        this.getPlayers(playerNameList, bettingMoneyList);
        this.benefitArray = new double[players.size() + 1];
    }

    private void getPlayers(List<String> playerNameList, List<Integer> bettingMoneyList) {
        for (int i = 0; i < playerNameList.size(); i++) {
            players.add(new Player(playerNameList.get(i), bettingMoneyList.get(i)));
        }
        dealerIndex = players.size();
    }

    public void playGame() {
        setBasicCards();
        printAllGamersHand();
        if (isDealerBlackjack()) {
            return;
        }
        progressHitStage();
        progressEndStage();
    }

    private void setBasicCards() {
        int basicCardCount = 2;
        for (int i = 0; i < basicCardCount; i++) {
            dealCards();
        }
    }

    private void dealCards() {
        for (Player player : players) {
            player.addCard(deck.giveRandomCard());
        }
        dealer.addCard(deck.giveRandomCard());
    }

    private void printAllGamersHand() {
        System.out.println(dealer.toString());
        for (Player player : players) {
            System.out.println(player.toString());
        }
    }

    private boolean isDealerBlackjack() {
        List<Integer> blackjackIndexList = makeBlackjackIndexList();
        boolean blackjackStatus = false;
        if (blackjackIndexList.size() > 0) {
            blackjackStatus = settleMidway(blackjackIndexList);
        }
        return blackjackStatus;
    }

    private List<Integer> makeScoreList() {
        List<Integer> scoreList = new ArrayList<>();
        for (Player player : players) {
            scoreList.add(player.calculateScore());
        }
        scoreList.add(dealer.calculateScore());
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

    private void findBlackjack(List<Integer> scoreList, List<Integer> blackjackIndexList, int index) {
        int blackjack = 21;
        if (scoreList.get(index) == blackjack) {
            blackjackIndexList.add(index);
        }
    }

    private boolean settleMidway(List<Integer> blackjackIndexList) {
        if (blackjackIndexList.contains(dealerIndex)) {
            checkLostPlayer(blackjackIndexList);
            printFinalResult();
            printBenefitResult();
            return true;
        }
        blackJackPlayerIndexList = blackjackIndexList;
        checkWinPlayer(blackJackPlayerIndexList);
        return false;
    }

    private void checkLostPlayer(List<Integer> blackjackIndexList) {
        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            reflectBenefitToLostPlayers(blackjackIndexList, playerIndex);
        }
    }

    private void reflectBenefitToLostPlayers(List<Integer> blackjackIndexList, int playerIndex) {
        if (!blackjackIndexList.contains(playerIndex)) {
            double benefit = players.get(playerIndex).lose();
            benefitArray[playerIndex] = benefit;
            benefitArray[dealerIndex] = -benefit;
        }
    }

    private void checkWinPlayer(List<Integer> blackjackIndexList) {
        double blackjackIncrease = 1.5;
        for (Integer blackjackPlayerIndex : blackjackIndexList) {
            double benefit = players.get(blackjackPlayerIndex).win() * blackjackIncrease;
            benefitArray[blackjackPlayerIndex] = benefit;
            benefitArray[dealerIndex] = -benefit;
        }
    }

    private void printFinalResult() {
        System.out.println();
        System.out.println(dealer.toString() + "(히든)" + dealer.getHiddenCard() + " - 결과 : " + dealer.calculateScore());
        for (Player player : players) {
            System.out.println(player.toString() + " - 결과 : " + player.calculateScore());
        }
    }

    private void printBenefitResult() {
        System.out.println("\n## 최종 수익");
        System.out.println("딜러 : " + benefitArray[dealerIndex]);
        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            System.out.println(players.get(playerIndex).getName() + " : " + benefitArray[playerIndex]);
        }
    }

    private void progressHitStage() {
        for (Player player : players) {
            askHit(player);
        }
        askHit(dealer);
    }
}
