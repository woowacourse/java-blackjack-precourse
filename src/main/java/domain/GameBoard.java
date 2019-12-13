package domain;

import domain.card.CardFactory;
import domain.card.Card;

import domain.user.Gamer;
import domain.user.Dealer;
import domain.user.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {
    List<Card> cards = CardFactory.create();
    List<Integer> cardIndex = new ArrayList<>();            //카드에 해당하는 인덱스
    List<Gamer> gamer = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    int head = 0;                           //맨 위 카드

    private static final char YES = 'y';
    private static final char NO = 'n';
    private static final int BLACKJACK = 21;
    private static final int DELAER_INDEX = 0;
    private static final int SIXTEEN = 16;
    private static final int EXTRA_ACE = 10;            //ACE 11 or 1 선택 여부에 따른 추가 값




    public GameBoard(String[] player, List<Integer> bettingMoney) {
        gamer.add(new Dealer());

        for (int i = 0; i < player.length; i++) {
            gamer.add(createPlayer(player[i], bettingMoney.get(i)));
        }

        shuffleCards();
        init();
        playerTurn();
        dealerTurn();
        endGame();
    }

    public Player createPlayer(String name, int bettingMoney) {
        Player player = new Player(name, bettingMoney);

        return player;
    }

    public void shuffleCards() {
        for (int i = 0; i < cards.size(); i++) {
            cardIndex.add(i);
        }

        Collections.shuffle(cardIndex);
    }

    public void init() {
        System.out.print("딜러와 ");
        for (int i = 1 ; i < gamer.size(); i++) {
            Player player = (Player)gamer.get(i);
            System.out.print(player.getName());
        if (i != (gamer.size() - 1)) {
            System.out.print(",");
            }
        }
        System.out.println("에게 2 장을 나누어 주었습니다.");

        for (Gamer g : gamer) {
            g.addCard(cards.get(cardIndex.get(head++)));
            g.addCard(cards.get(cardIndex.get(head++)));
            printCards(g);
        }
    }

    public void printCards(Gamer gamer) {
        if (gamer.getClass() == Dealer.class) {
            System.out.print("\n딜러 : ");

            for (Card c : gamer.getCards()){
                System.out.print(c.getSymbol().getScore() + " " + c.getType().name() + " ");
            }
        }

        if (gamer.getClass() == Player.class) {
            Player player = (Player) gamer;
            System.out.print("\n" + player.getName() + " : ");

            for (Card c : gamer.getCards())
                System.out.print(c.getSymbol().getScore() + " " + c.getType().name() + " ");
        }
    }

    public int calValue(Gamer gamer) {
        int sum = 0;

        for (Card c :gamer.getCards()) {
            sum += c.getSymbol().getScore();
        }

        return sum;
    }

    public void playerTurn() {
        for (int i = 1; i < gamer.size(); i++) {
            turn(gamer.get(i));
        }
    }

    public void turn(Gamer gamer) {
        boolean oneMore = true;

        while (calValue(gamer) < 21 && oneMore) {
            Player p = (Player) gamer;

            System.out.println("\n" + p.getName() + "는 한 장의 카드를 더 받겠습니까? (예는 y 아니오는 n)");

            char answer = sc.next().charAt(0);
            if (YES == answer) {
                gamer.addCard(cards.get(cardIndex.get(head++)));
                printCards(gamer);
            }
            if (NO == answer) {
                oneMore = false;
                printCards(gamer);
            }
        }
    }

    public void dealerTurn() {
        if (calValue(gamer.get(DELAER_INDEX)) <= SIXTEEN) {
            System.out.println("\n딜러는 16이하라 카드를 한 장 더 받았습니다.");
            gamer.get(DELAER_INDEX).addCard(cards.get(cardIndex.get(head++)));
        }
    }

    public void endGame() {
        for (Gamer g : gamer) {
            printCards(g);
            System.out.println(result(g));
        }
    }

    public int result(Gamer gamer) {
        int result = calValue(gamer);

        if (gamer.hasACE() && (result + EXTRA_ACE) <= BLACKJACK) {
            result += EXTRA_ACE;
        }

        return result;
    }









}
