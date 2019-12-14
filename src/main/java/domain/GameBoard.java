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
    private static final char YES = 'y';
    private static final char NO = 'n';
    private static final int BLACKJACK = 21;
    private static final int DELAER_INDEX = 0;
    private static final int SIXTEEN = 16;
    private static final int EXTRA_ACE = 10;            //ACE 11 or 1 선택 여부에 따른 추가 값
    private static final int WIN = 0;
    private static final int LOSE = -1;

    List<Card> cards = CardFactory.create();
    List<Integer> cardIndex = new ArrayList<>();            //카드에 해당하는 인덱스
    List<Gamer> gamer = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    int head = 0;                           //맨 위 카드

    public GameBoard(String[] player, List<Double> bettingMoney) {
        gamer.add(new Dealer());

        for (int i = 0; i < player.length; i++) {
            gamer.add(createPlayer(player[i], bettingMoney.get(i)));
        }

        shuffleCards();
        init();
        playerTurn();
        dealerTurn();
        endGame();
        winner();
    }

    public Player createPlayer(String name, double bettingMoney) {
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

    public void winner() {
        List<Gamer> winner;

        winner = blackjack();

        if (winner.isEmpty()) {
            winner = closeBlackjack();
        }

        if (!(winner.isEmpty()) && winner.size() == 1) {
            if (winner.get(0).getClass() == Dealer.class) {             //딜러 승리 ( 플레이어 중 동점자 없을 시)
                dealerWin();
            }
        }

        if (!(winner.isEmpty()) && winner.size() != 1) {
            if (winner.get(0).getClass() == Dealer.class) {             //딜러 승리 ( 플레이어 중 동점자 있을 시)
                draw();
            }
        }

        if (!(winner.isEmpty())) {
            if (winner.get(0).getClass()!=Dealer.class) {             //딜러 패배
                playerWin(winner);
            }
        }
        for (Gamer g : winner) {
            System.out.println(g);

        }

    }

    public List<Gamer> blackjack() {
        List<Gamer> winner = new ArrayList<>();

        for (Gamer g : gamer) {
            if (result(g) == BLACKJACK) {
                winner.add(g);
            }
        }

        return winner;
    }

    public List<Gamer> closeBlackjack() {
        List<Gamer> winner = new ArrayList<>();

        if (result(gamer.get(DELAER_INDEX)) > BLACKJACK) {                  //dealer가 21을 초과 시 전원 승리
            for (int i = 0 ; i < gamer.size(); i++) {
                winner.add(gamer.get(i));
            }

            return winner;
        }
        for (Gamer g : gamer) {
            boolean onlyOne = true;                     //중복으로 winner 에 add 방지

            if (winner.isEmpty() && result(g) <= BLACKJACK && onlyOne) {
                onlyOne = false;
                winner.add(g);
            }
            if (!(winner.isEmpty()) && result(g) > result(winner.get(0)) && result(g) <= BLACKJACK && onlyOne) {
                winner.clear();
                winner.add(g);
                onlyOne = false;

            }
            if (!(winner.isEmpty()) && result(g) == result(winner.get(0)) && onlyOne ) {
                winner.add(g);
                onlyOne = false;
            }
        }

        return winner;
    }

    public void playerWin(List<Gamer> winner) {
        System.out.println("\n###최종 수익");
        System.out.println("딜러 : 0");
        for (Gamer g : winner) {
            Player p = (Player) g;
            p.win();
        }

        for (int i = 1; i <gamer.size(); i++) {
            Player p = (Player) gamer.get(i);
            System.out.println(p.getName() + " : " + p.getStatus());
        }
    }

    public void dealerWin() {
        double sum = 0;

        System.out.println("\n###최종 수익");

        for (int i = 1; i < gamer.size(); i++) {
            Player p = (Player) gamer.get(i);
            sum = sum - p.getStatus();
        }
        System.out.print("딜러 : " + sum);
        System.out.println("");

        for (int i = 1; i < gamer.size(); i++) {
            Player p = (Player) gamer.get(i);
            System.out.println(p.getName() + " : " + p.getStatus());
        }
    }

    public void draw(List<Gamer> winner) {
        System.out.println("\n###최종 수익");
        System.out.println("딜러 : 0");
        for (Gamer g : winner) {
            Player p = (Player) g;
            p.draw();
        }

        for (int i = 1; i <gamer.size(); i++) {
            Player p = (Player) gamer.get(i);
            System.out.println(p.getName() + " : " + p.getStatus());
        }
    }

}
