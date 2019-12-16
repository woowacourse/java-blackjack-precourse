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

    private int dealerResult = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private List<Card> cardDeck = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private Initialize init = new Initialize();

    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();
        blackjack.startGame();
        blackjack.gameSet();
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
        }
    }

    public void participatePlayers(ArrayList<String> playerNames) {
        for (String playerName : playerNames) {
            double bettingMoney = init.setPlayerBettings(playerName);
            Player player = new Player(playerName, bettingMoney);
            players.add(player);
        }
    }

    public void gameSet() {
        String result = new String();
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

    public void showWinner(String result) {
        System.out.println("### 최종 수익");
        System.out.println("딜러: " + dealerResult);
        System.out.println(result);
    }

}