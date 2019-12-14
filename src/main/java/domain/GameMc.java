package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameMc {
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> dead = new ArrayList<Player>();
    private String playerNames;
    private List<Integer> isPickedCard = new ArrayList<Integer>();
    private Dealer dealer = new Dealer();

    public void gameMc() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        playerNames = Input();
        for (String name : playerNames.split(",")) {
            System.out.println(name + "의 베팅 금액은?");
            players.add(new Player(name, bettingMoneyInput()));
        }
    }

    public String Input() {
        Scanner input = new Scanner(System.in);
        return input.next();
    }

    public int bettingMoneyInput() {
        int money = Integer.valueOf(Input());
        while (money <= 0) {
            System.out.println("0보다 큰 수를 입력하세요");
            money = Integer.valueOf(Input());
        }
        return money;
    }

    public Card makeRandomCard() {
        int cardIdx;
        List<Card> card = CardFactory.create();
        Random ran = new Random();
        cardIdx = ran.nextInt(card.size());
        while (isPickedCard.contains(cardIdx)) {
            cardIdx = ran.nextInt(card.size());
        }
        isPickedCard.add(cardIdx);
        return card.get(cardIdx);
    }

    public void init(Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            dealer.addCard(makeRandomCard());
        }
    }

    public void init(Player player) {
        for (int i = 0; i < 2; i++) {
            player.addCard(makeRandomCard());
        }
    }

    public void gameStart() {
        System.out.print("\n딜러와 " + playerNames + "에게 2장씩 나누었습니다.");
        init(dealer);
        for (Player player : players) {
            init(player);
        }
        dealer.showInitCard();
        for (Player player : players) {
            player.showCard();
        }
    }

    public ArrayList<Integer> makeScoreList() {
        ArrayList<Integer> scoreList = new ArrayList<Integer>();
        scoreList.add(dealer.getScore());
        for (Player player : players) {
            scoreList.add(player.getScore());
        }
        return scoreList;
    }

    public void isBlackJack() {
        if (makeScoreList().contains(21)) {
            endGame();
        }
    }

    public void endGame() {
        finalScoring();  //최종 보유 카드와 점수
        System.out.println("\n\n## 최종수익\n딜러 : 0");
        for (Player player : players) {
            awardWinner(player);
        }
        for (Player dead : dead){
            System.out.println(dead.getName()+" : -"+dead.getBettingMoney());
        }
        System.exit(0);
    }

    public void finalScoring() {
        for (Player dead : dead){
            players.remove(dead);
        }
        Iterator iter = makeScoreList().iterator();
        dealer.showFinalCard();
        System.out.print("- 결과 : " + iter.next());
        for (Player player : players) {
            player.showCard();
            System.out.print("- 결과 : " + iter.next());
        }
    }

    public void awardWinner(Player player) {
        if (player.getScore() < dealer.getScore()) {
            System.out.println(player.getName() + " : -" + player.getBettingMoney());
        } else if (player.getScore() == dealer.getScore()) {
            System.out.println(player.getName() + " : 0");
        } else if (player.getScore() == 21 && dealer.getScore() != 21) {
            System.out.println(player.getName() + " : " + player.getBettingMoney() * 1.5);
        } else if (player.getScore() > dealer.getScore()) {
            System.out.println(player.getName() + " : " + player.getBettingMoney());
        }
    }

    public void askHit() {
        for (Player player : players) {
            isHit(player);
        }
    }

    public void isHit(Player player) {
        System.out.println("\n" + player.getName() + "는 한장의 카드를 더 받으시겠습니까?");
        String answer = Input();
        if ("y".equals(answer)) {
            hit(player, answer);
        }
    }

    public void hit(Player player, String answer) {
        while ("y".equals(answer)) {
            player.addCard(makeRandomCard());
            player.showCard();
            System.out.println("\n" + player.getName() + "는 한장의 카드를 더 받으시겠습니까?");
            answer = Input();
        }
        if (player.getScore() > 21) {
            System.out.println("스코어가 21을 넘어 패하였습니다.");
            dead.add(player);
        }
    }

}
