package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GameProcess {
    private Scanner sc = new Scanner(System.in);
    private String[] playerName;
    private int[] betting;
    private List<Card> cards = new ArrayList<>();
    private List<Dealer> player = new ArrayList<>();
    private final int ZERO_BETTING = 0;
    private final int MAX_CARD_NUMBER = 52;
    private final int BLACKJACK = 21;

    public void Game() {
        inputName();
        inputBetting();
        cards = CardFactory.create();
        createPlayer();
        for(int i = 0; i < player.size(); i++){
            deal_TwoCard(player.get(i));
            System.out.println(player.get(i));
        }

    }

    private void inputName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요." +
                "(쉼표 기준으로 분리)");

        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            inputName();
            return;
        }
        playerName = input.split(",");
    }

    private void inputBetting() {
        betting = new int[playerName.length];

        for (int i = 0; i < playerName.length; i++) {
            while (betting[i] <= ZERO_BETTING) {
                System.out.println(playerName[i] + "의 배팅 금액은? (금액은 0원 이상이어야 합니다.)");
                betting[i] = sc.nextInt();
            }
        }
    }

    private void createPlayer(){
        player.add(new Dealer());
        for(int i = 0; i < playerName.length; i++){
            player.add(new Player(playerName[i],betting[i]));
        }
    }

    private void deal_OneCard(Dealer dealer){
        Random random = new Random();

        int cardChoose = random.nextInt(MAX_CARD_NUMBER);
        while(cards.get(cardChoose).isUsed()){
            cardChoose = random.nextInt(MAX_CARD_NUMBER);
        }
        cards.get(cardChoose).use();
        dealer.addCard(cards.get(cardChoose));
    }

    private void deal_TwoCard(Dealer dealer){
        int Two = 2;

        System.out.println(dealer.getName()+ "에게 두 장의 카드를 주었습니다.");
        for(int i = 0; i < Two; i++){
            deal_OneCard(dealer);
        }
    }



}
