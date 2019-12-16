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
    public static final int INITIAL_CARD_NUM = 2; //처음 받는 카드의 수
    public static final int MAX_SCORE = 21;
    public static final int STANDARD_DEALER_SCORE = 17;
    public static final double BONUS = 1.5;

    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> buster = new ArrayList<Player>();//21이 넘어 패한 플레이어 혹은 딜러보다 점수가 낮은 플레이어
    private Dealer dealer = new Dealer();
    private String playerNames;
    private List<Integer> isPickedCard = new ArrayList<Integer>();

    public void gameMc() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Scanner input = new Scanner(System.in);
        playerNames = input.next();
        for (String name : playerNames.split(",")) {
            System.out.println(name + "의 베팅 금액은?");
            players.add(new Player(name, inputMoney()));
        }
    }

    public String inputIsHit() {
        Scanner input = new Scanner(System.in);
        String answer = input.next();
        while (!("y".equals(answer) || "n".equals(answer))) {
            System.out.println("y 또는 n을 입력하세요.");
            answer = input.next();
        }
        return answer;
    }

    public int inputMoney() {
        Scanner input = new Scanner(System.in);
        String money = input.next();
        while (isNotInt(money) || Integer.parseInt(money) <= 0) {
            System.out.println("숫자만 입력해주세요");
            System.out.println("0보다 큰 수를 입력하세요");
            money = input.next();
        }
        return Integer.parseInt(money);
    }

    public Boolean isNotInt(String str) {
        String pattern = "^[0-9]+$";
        return !str.matches(pattern);
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
        for (int i = 0; i < INITIAL_CARD_NUM; i++) {
            dealer.addCard(makeRandomCard());
        }
    }

    public void init(Player player) {
        for (int i = 0; i < INITIAL_CARD_NUM; i++) {
            player.addCard(makeRandomCard());
        }
    }

    public void initGame() {
        System.out.print("\n딜러와 " + playerNames + "에게 " + INITIAL_CARD_NUM + "장씩 나누었습니다.");
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

    /**
     * 처음 2장이 블랙잭인지 확인
     */
    public void isBlackJack() {
        if (makeScoreList().contains(MAX_SCORE)) {
            blackJackEnding();
        }
        scratch();
    }

    public void blackJackEnding() {
        splitBlackJackOrNot();
        finalScoring(); //최종 보유 카드와 점수 출력 + "##최종 수익" 출력
        if (dealer.getScore() == MAX_SCORE) {
            draw();
        }
        for (Player player : players) {
            System.out.println(player.getName() + " : " + player.getBettingMoney() * BONUS);
        }
        for (Player buster : buster) {
            System.out.println(buster.getName() + " : -" + buster.getBettingMoney());
        }
        System.exit(0);
    }

    /**
     * 처음 2장이 블랙잭일 경우 블랙잭인 플레이어와 아닌 플레이어를 나눔
     */
    public void splitBlackJackOrNot() {
        buster = players;
        players.removeIf(x -> (x.getScore() != MAX_SCORE));
        buster.removeIf(x -> (players.contains(x)));
    }

    public void draw() {
        for (Player player : players) {
            System.out.println(playerNames + " : 0");
        }
        for (Player buster : buster) {
            System.out.println(buster.getName() + " : -" + buster.getBettingMoney());
        }
        System.exit(0);
    }

    public void endGame() {
        finalScoring();
        if (dealer.getScore() > MAX_SCORE) {
            everyoneWin();
        }
        for (Player player : players) {
            awardSurvivor(player);
        }
        for (Player buster : buster) {
            System.out.println(buster.getName() + " : -" + buster.getBettingMoney());
        }
        System.out.println("게임을 종료합니다.");
        System.exit(0);
    }

    public void everyoneWin() {
        for (Player player : players) {
            System.out.println(player.getName() + " : " + player.getBettingMoney());
        }
        System.exit(0);
    }

    public void finalScoring() {
        for (Player looser : buster) {
            players.remove(looser);
        }
        Iterator iterator = makeScoreList().iterator();
        dealer.showFinalCard();
        System.out.print("- 결과 : " + iterator.next());
        for (Player player : players) {
            player.showCard();
            System.out.print("- 결과 : " + iterator.next());
        }
        System.out.println("\n\n## 최종수익\n딜러 : 0");
    }

    public void awardSurvivor(Player player) {
        if (player.getScore() < dealer.getScore()) {
            System.out.println(player.getName() + " : -" + player.getBettingMoney());
        } else if (player.getScore() == dealer.getScore()) {
            System.out.println(player.getName() + " : 0");
        } else if (player.getScore() > dealer.getScore()) {
            System.out.println(player.getName() + " : " + player.getBettingMoney());
        }
    }

    /**
     * hit(카드를 더 받는 행위)을 할지 입력
     */
    public void scratch() {
        for (Player player : players) {
            System.out.println("\n" + player.getName() + "는 한장의 카드를 더 받으시겠습니까?(예는 y, 아니오는 n)");
            inputHitOrNo(player);
        }
    }

    public void inputHitOrNo(Player player) {
        String answer = inputIsHit();
        if ("y".equals(answer)) {
            hit(player, answer);
        }
    }

    public void hit(Player player, String answer) {
        while ("y".equals(answer)) {
            player.addCard(makeRandomCard());
            player.showCard();
            System.out.println("\n" + player.getName() + "는 한장의 카드를 더 받으시겠습니까?");
            answer = inputIsHit();
        }
        if (player.getScore() > MAX_SCORE) {
            System.out.println("스코어가 21을 넘어 패하였습니다.");
            buster.add(player);
        }
    }

    public void addDealerCard() {
        while (dealer.getScore() < STANDARD_DEALER_SCORE) {
            System.out.println("딜러는 16이하라 한장의 카드를 받았습니다.");
            dealer.addCard(makeRandomCard());
        }
    }
}
