package domain;

import domain.user.*;
import domain.card.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayGame {

    Dealer dealer;
    List<Player> players;
    List<Card> cards;
    int usedCardIndex = -1;
    Score score;

    private static final int dealerThreshold = 17;

    public PlayGame(List<Player> players) {
        dealer = new Dealer();
        this.players = players;
        this.cards = CardFactory.create();
        score = new Score(players, dealer);
    }

    public void play() {
        firstShuffle();
        checkBlackJack();
        addShuffle();
//        checkWinner();
//        printFianlResult();
    }

    public void firstShuffle() {
        shuffleCard();
        shuffleCard();
        printMidResult();
    }

    private void shuffleCard() { //한 장씩 할당
        dealer.addCard(pickCard());
        for (Player player : players) {
            player.addCard(pickCard());
        }
    }

    private Card pickCard() {
        usedCardIndex++;
        return cards.get(usedCardIndex);
    }

    private void printMidResult() {
        System.out.println(dealer.print());
        for (Player player : players) {
            System.out.println(player.print());
        }
    }

    private void checkBlackJack() {
        List<Player> blackJackPlayers = new ArrayList<>();
        for (Player player : players)
            isBlackJack(blackJackPlayers, player);
        if (!blackJackPlayers.isEmpty() || dealer.cardSum() == Score.BLACK_JACK) {
            System.out.println("BlackJack Exists");
            score.blackJack(blackJackPlayers, dealer.cardSum());
            printFinalResult();
            System.exit(0);
        }
    }

    private void isBlackJack(List<Player> blackJackPlayers, Player player) { //BlackJack인 player return
        if (player.cardSum() == Score.BLACK_JACK) blackJackPlayers.add(player);
    }

    private void addShuffle() {
        System.out.println("ADD SHUFFLE");
        for (Player player : players)
            askPlayers(player);

        dealerAdd();
    }

    private void askPlayers(Player player) {
        Scanner s = new Scanner(System.in);
        boolean request = true;
        while (request) {
            System.out.println(player.getName() + "은(는) 카드를 한 장 더 받겠습니까?(예는 y, 아니오는 n)");
            String requestStr = s.next();
            request = yesOrNo(requestStr);
            answerPlayers(request, player);
            System.out.println(player.print());
        }
    }

    private boolean yesOrNo(String str) {
        if (str.equals("Y") || str.equals("y"))
            return true;
        if (str.equals("N") || str.equals("n"))
            return false;
        System.out.println("예외처리");
        return true;
    }

    private void answerPlayers(boolean request, Player player) {
        if (request)
            player.addCard(pickCard());
    }

    private void dealerAdd() {
        if (dealer.cardSum() < dealerThreshold) dealer.addCard(pickCard());
    }

    private void printFinalResult() {
        System.out.println(dealer.print() + "- 결과 :" + dealer.cardSum());
        for (Player player : players) {
            System.out.println(player.print() + "- 결과 :" + player.cardSum());
        }
        printFinalScore();
    }

    private void printFinalScore() {
        System.out.println("## 최종수익");
        System.out.println("딜러: " + dealer.getProfit());
        for (Player player : players)
            System.out.println(player.getName() + ": " + player.getProfit());
    }

}
