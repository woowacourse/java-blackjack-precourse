package domain;

import domain.user.*;
import domain.card.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 게임을 진행하는 객체
 */
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

    /**
     * 게임 진행을 담당하는 함수
     */
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

    /**
     * 딜러와 플레이어에게 카드를 한 장씩 할당하는 함수
     */
    private void shuffleCard() {
        dealer.addCard(pickCard());
        for (Player player : players) {
            player.addCard(pickCard());
        }
    }

    private Card pickCard() {
        usedCardIndex++;
        return cards.get(usedCardIndex);
    }

    /**
     * 딜러와 플레이어들에게 각각 2장의 카드를 나눈 후 중간 결과를 출력하는 함수
     */
    private void printMidResult() {
        System.out.println('\n'+dealer.print());
        for (Player player : players) {
            System.out.println(player.print());
        }
    }

    /**
     * 블랙잭이 존재할 경우, 처리하는 함수
     */
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

    /**
     * 블랙잭인 플레이어들을 반환한다.
     */
    private void isBlackJack(List<Player> blackJackPlayers, Player player) {
        if (player.cardSum() == Score.BLACK_JACK) blackJackPlayers.add(player);
    }

    /**
     * 카드를 추가로 나눠주는 함수.
     */
    private void addShuffle() {
        for (Player player : players)
            askPlayers(player);

        dealerAdd();
    }

    private void askPlayers(Player player) {
        boolean requested = true;
        while (requested && player.cardSum() < Score.BLACK_JACK) {
            System.out.println('\n' + player.getName() + "은(는) 카드를 한 장 더 받겠습니까?(예는 y, 아니오는 n)");
            String requestStr = new Scanner(System.in).next();
            requested = makeValidRequest(yesOrNo(requestStr));
            answerPlayers(requested, player);
            System.out.println(player.print());
        }
    }

    /**
     * 사용자의 입력이 y 또는 n이 될 때까지 물어보는 함수
     */
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
        if (request) player.addCard(pickCard());
    }

    private void dealerAdd() {
        if (dealer.cardSum() < DEALER_THRESHOLD) {
            dealer.addCard(pickCard());
            System.out.println("\n딜러는 16 이하라 한 장 더 받았습니다.");
        }
    }

    /**
     * 게임의 승자를 가려내는 함수
     */
    private void checkWinner() {
        List<Player> winners = new ArrayList<>();
        winners = checkDealerWin();
        score.returnBettingMoney(winners);
    }

    /**
     * 딜러의 점수가 21을 넘어가는지 확인하는 함수.
     * 그럴 경우 전체 플레이어가 승자가 된다.
     * 승자들을 반환한다.
     */
    private List<Player> checkDealerWin() {
        if (isOver21(dealer.cardSum())) {
            return players;
        }
        return getWinner();
    }

    /**
     * 최고점인 플레이어(승자)들을 반환하는 함수
     */
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
            }
        }
        return winners;
    }

    private boolean isOver21(double value) {
        if (value > Score.BLACK_JACK) return true;
        return false;
    }

    private void printFinalResult() {
        System.out.println('\n' + dealer.print() + " - 결과 :" + dealer.cardSum());
        for (Player player : players) {
            System.out.println(player.print() + " - 결과 :" + player.cardSum());
        }
        printFinalScore();
    }

    private void printFinalScore() {
        System.out.println("\n## 최종수익");
        System.out.println("딜러: " + (int)dealer.getProfit());
        for (Player player : players)
            System.out.println(player.getName() + ": " + (int)player.getProfit());
    }

}
