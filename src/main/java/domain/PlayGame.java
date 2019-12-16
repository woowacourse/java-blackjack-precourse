package domain;

import domain.user.*;
import domain.card.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayGame {

    Dealer          dealer;
    List<Player>    players;
    List<Card>      cards;
    int             usedCardIndex = -1;
    Score           score;

    private static final int DEALER_THRESHOLD = 17;

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
        checkWinner();
        printFinalResult();
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
        System.out.println('\n'+dealer.print());
        for (Player player : players) {
            System.out.println(player.print());
        }
    }

    private void checkBlackJack() {
        List<Player> blackJackPlayers = new ArrayList<>();
        for (Player player : players)
            isBlackJack(blackJackPlayers, player);
        if (!blackJackPlayers.isEmpty() || dealer.cardSum() == Score.BLACK_JACK) {
            score.blackJack(blackJackPlayers, dealer.cardSum());
            printFinalResult();
            System.exit(0);
        }
    }

    private void isBlackJack(List<Player> blackJackPlayers, Player player) { //BlackJack인 player return
        if (player.cardSum() == Score.BLACK_JACK) blackJackPlayers.add(player);
    }

    private void addShuffle() {
        for (Player player : players)
            askPlayers(player);

        dealerAdd();
    }

    private void askPlayers(Player player) {
        Scanner s = new Scanner(System.in);
        boolean requested = true;
        while (requested && player.cardSum() < Score.BLACK_JACK) {
            System.out.println('\n' + player.getName() + "은(는) 카드를 한 장 더 받겠습니까?(예는 y, 아니오는 n)");
            String requestStr = s.next();
            requested = makeValidRequest(yesOrNo(requestStr));
            answerPlayers(requested, player);
            System.out.println(player.print());
        }
    }

    private boolean makeValidRequest(int requestCode) {
        while (requestCode == 2) {
            System.out.println("y 또는 n을 입력해주세요.");
            String requestStr = new Scanner(System.in).next();
            requestCode = yesOrNo(requestStr);
        }
        if (requestCode == 1) return true;
        return false;
    }

    private int yesOrNo(String str) {
        if (str.equals("Y") || str.equals("y"))
            return 1;
        if (str.equals("N") || str.equals("n"))
            return 0;
        return 2;
    }

    private void answerPlayers(boolean request, Player player) {
        if (request)
            player.addCard(pickCard());
    }

    private void dealerAdd() {
        if (dealer.cardSum() < DEALER_THRESHOLD) {
            dealer.addCard(pickCard());
            System.out.println("\n딜러는 16 이하라 한 장 더 받았습니다.");
        }
    }

    private void checkWinner() { //최고점 출력
        List<Player> winners = new ArrayList<>();
        winners = checkDealerWin();
        score.returnBettingMoney(winners);
    }

    private List<Player> checkDealerWin() {
        if (isOver21(dealer.cardSum())) { //플레이어들 승
            return players;
        }
        return getWinner();
    }

    private List<Player> getWinner() {
        List<Player> winners = new ArrayList<>();
        int maxSum = dealer.cardSum();
        for (Player player : players) {
            if (isOver21(player.cardSum())) continue;
            if (player.cardSum() == maxSum)
                winners.add(player);
            if (player.cardSum() > maxSum) {
                winners = new ArrayList<>();
                winners.add(player);
                maxSum = player.cardSum();
                continue;
            }
        }
        return winners;
    }

    private boolean isOver21(double value) {
        if (value > Score.BLACK_JACK) return true;
        return false;
    }

    private void printFinalResult() {
        System.out.println('\n' + dealer.print() + " - 결과 :" + (int)dealer.cardSum());
        for (Player player : players) {
            System.out.println(player.print() + " - 결과 :" + (int)player.cardSum());
        }
        printFinalScore();
    }

    private void printFinalScore() {
        System.out.println("\n## 최종수익");
        System.out.println("딜러: " + dealer.getProfit());
        for (Player player : players)
            System.out.println(player.getName() + ": " + player.getProfit());
    }

}
