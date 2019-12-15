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
    public static final int INITIAL_CARD_NUM = 2;
    public static final int MAX_SCORE = 21;
    public static final int STANDARD_DEALER_SCORE = 17;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> looser = new ArrayList<Player>();
    private Dealer dealer = new Dealer();
    private String playerNames;
    private List<Integer> isPickedCard = new ArrayList<Integer>();

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
        String money = Input();
        while (isNotInt(money) || Integer.valueOf(money) <= 0){
            System.out.println("숫자만 입력해주세요");
            System.out.println("0보다 큰 수를 입력하세요");
            money = Input();
        }
        return Integer.valueOf(money);
    }

    public Boolean isNotInt(String str){
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
        System.out.print("\n딜러와 " + playerNames + "에게 "+INITIAL_CARD_NUM+"장씩 나누었습니다.");
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
            blackjackEnding();
        }
        scratch();
    }

    public void blackjackEnding(){
        players.removeIf(n->(n.getScore()!=MAX_SCORE));
        if(dealer.getScore()==MAX_SCORE){
            draw();
        }
        for (Player player : players){
            System.out.println(player.getName()+" : "+player.getBettingMoney()*1.5);
        }
        System.exit(0);
    }

    public void draw(){
        for (Player player : players){
            System.out.println(playerNames + " : 0");
        }
        System.exit(0);
    }

    public void endGame() {
        finalScoring();  //최종 보유 카드와 점수
        System.out.println("\n\n## 최종수익\n딜러 : 0");
        if (dealer.getScore() > MAX_SCORE) {
            everyoneWin();
        }
        for (Player player : players) {
            awardWinner(player);
        }
        for (Player looser : looser) {
            System.out.println(looser.getName() + " : -" + looser.getBettingMoney());
        }
        System.exit(0);
    }

    public void everyoneWin() {
        for (Player player : players) {
            System.out.println(playerNames + " : " + player.getBettingMoney());
        }
        System.exit(0);
    }

    public void finalScoring() {
        for (Player looser : looser) {
            players.remove(looser);
        }
        Iterator iterator = makeScoreList().iterator();
        dealer.showFinalCard();
        System.out.print("- 결과 : " + iterator.next());
        for (Player player : players) {
            player.showCard();
            System.out.print("- 결과 : " + iterator.next());
        }
    }

    public void awardWinner(Player player) {
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
            System.out.println("\n" + player.getName() + "는 한장의 카드를 더 받으시겠습니까?");
            inputHitOrNo(player);
        }
    }

    public void inputHitOrNo(Player player) {
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
        if (player.getScore() > MAX_SCORE) {
            System.out.println("스코어가 21을 넘어 패하였습니다.");
            looser.add(player);
        }
    }

    public void addDealerCard() {
        while (dealer.getScore() < STANDARD_DEALER_SCORE) {
            dealer.addCard(makeRandomCard());
        }
    }

}
