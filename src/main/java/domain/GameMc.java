package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameMc {
    private ArrayList<Player> players = new ArrayList<Player>();
    private String nameInput;
    private List<Integer> isPickedCard = new ArrayList<Integer>();
    private Dealer dealer = new Dealer();

    public void gameMc() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        nameInput = Input();
        for (String name : nameInput.split(",")) {
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

    public Card makeRandomCard(){
        int cardIdx;
        List<Card> card = CardFactory.create();
        Random ran = new Random();
        cardIdx = ran.nextInt(card.size());
        while (isPickedCard.contains(cardIdx)){
            cardIdx = ran.nextInt(card.size());
        }
        isPickedCard.add(cardIdx);
        return card.get(cardIdx);
    }

    public void init(Dealer dealer){
        for(int i=0;i<2;i++){
            dealer.addCard(makeRandomCard());
        }
    }
    public void init(Player player){
        for(int i=0;i<2;i++){
            player.addCard(makeRandomCard());
        }
    }

    public void gameStart(){
        System.out.println("딜러와 " +nameInput+"에게 2장씩 나누었습니다.");
        init(dealer);
        for (Player player : players){
            init(player);
        }
        dealer.showCard();
        for (Player player : players){
            player.showCard();
        }
    }

    public ArrayList<Integer> makeScoreList(){
        ArrayList<Integer> scoreList = new ArrayList<Integer>();
        scoreList.add(dealer.getScore());
        for (Player player : players){
            scoreList.add(player.getScore());
        }
        return scoreList;
    }
}
