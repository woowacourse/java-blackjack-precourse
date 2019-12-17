/*
 * Blackjack
 * 
 * ver 1.0.0
 * 
 * December, 17th, 2019
 * 
 * Copyright(c) Hwangbo Kyeong 2019
 */

package domain.game;

import domain.user.*;
import domain.card.*;

import java.util.*;

public class Blackjack {

    Scanner sc = new Scanner(System.in);

    private static double dealerResult = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private List<Card> cardDeck = new ArrayList<>();
    private List<Integer> playerBlackjack = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private Initialize init = new Initialize();
    private static String result = new String();
    private int isFinish;

    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();
        blackjack.startGame();
        if (blackjack.isFinish == 1) {
            showWinner(result);
            //System.exit(0);
        } else if (blackjack.isFinish == 0) {
            blackjack.gameSet();
        }
    }

    public void startGame() {
        ArrayList<String> playerNames = init.setPlayerNames();
        List<Card> cards;

        participatePlayers(playerNames);
        cards = CardFactory.create();
        for (Card card : cards) {
            cardDeck.add(card);
        }
        Collections.shuffle(cardDeck);
        firstDraw(playerNames);
    }

    public void firstDraw(ArrayList<String> playerNames) {
        System.out.println("딜러와 " + String.join(",", playerNames) + "에게 2장을 나누었습니다.");

        cardDeck = dealer.drawCards(2, cardDeck);
        dealer.showCards();
        for (Player player : players) {
            cardDeck = player.drawCards(2, cardDeck);
            player.showCards();
            playerBlackjack.add(isBlackjack(dealer.checkBlackjack(), player));
        }
        for (int i = 0; i < players.size(); i++) {
            setIsFinish(i);
        }
    }

    public int isBlackjack(boolean isDealerBlackjack, Player player) {
        boolean isPlayerBlackjack = player.checkBlackjack();

        if (isDealerBlackjack == true && isPlayerBlackjack == true) {
            System.out.println(player.getName() + "와 딜러가 블랙잭입니다. 게임을 종료합니다.\n");
            return 1;
        } else if (isDealerBlackjack == false && isPlayerBlackjack == true) {
            System.out.println(player.getName() + "이 블랙잭입니다. 게임을 종료합니다.\n");
            return 2;
        } else if (isDealerBlackjack == true && isPlayerBlackjack) {
            System.out.println("딜러가 블랙잭입니다. 게임을 종료합니다.\n");
            return 3;
        }
        return 4;
    }

    public void setIsFinish(int count) {
        Player player = players.get(count);

        if (playerBlackjack.get(count) == 1) {
            dealerResult = 0;
            result += player.getName() + " : " + player.getMoney() + "\n";
            isFinish = 1;
        } else if (playerBlackjack.get(count) == 2) {
            dealerResult = player.getMoney() * (-0.5);
            result += player.getName() + " : " + (player.getMoney() * 1.5) + "\n";
            isFinish = 1;
        } else if (playerBlackjack.get(count) == 3) {
            dealerResult = player.getMoney();
            result += player.getName() + " : " + (player.getMoney() * -1) + "\n";
            isFinish = 1;
        }
        isFinish = 0;
    }

    public void participatePlayers(ArrayList<String> playerNames) {
        for (String playerName : playerNames) {
            double bettingMoney = init.setPlayerBettings(playerName);
            Player player = new Player(playerName, bettingMoney);
            players.add(player);
        }
    }

    public void gameSet() {

        for (Player player : players) {
            doDraw(player);
        }
        checkDealer();
        showResult();

        for (Player player : players) {
            result += checkWinner(player);
        }
        showWinner(result);
    }

    public void doDraw(Player player) {
        String input;
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        input = sc.next();
        if (input.equals("y")) {
            player.drawCards(1, cardDeck);
            doDraw(player);
        } else if (input.equals("n")) {
        } else if (input != "y" || input != "n") {
            System.out.println("잘못된 입력입니다.");
            doDraw(player);
        }
    }

    public String checkWinner(Player player) {
        String answer = new String();
        if (player.cardScore() <= 21 && player.cardScore() > dealer.cardScore()) {
            dealerResult -= player.getMoney();
            answer = player.winner();
        } else if (player.cardScore() > 21 || player.cardScore() < dealer.cardScore()) {
            dealerResult += player.getMoney();
            answer = player.loser();
        }
        return answer;
    }

    public void checkDealer() {
        if (dealer.cardScore() <= 16) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다");
            dealer.drawCards(1, cardDeck);
        }
    }

    public void showResult() {
        dealer.showFinal();
        for (Player player : players) {
            player.showFinal();
        }
    }

    public static void showWinner(String result) {
        System.out.println("\n### 최종 수익");
        System.out.println("딜러: " + dealerResult);
        System.out.println(result);
    }

}