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
    private Dealer dealer = new Dealer();
    private List<Player> player = new ArrayList<>();
    private final int ZERO_BETTING = 0;

    public void Game() {
        inputName();
        inputBetting();
        cards = CardFactory.create();
        createPlayer();
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
        for(int i = 0; i < playerName.length; i++){
            player.add(new Player(playerName[i],betting[i]));
        }
    }

    private Card deal(){
        Random random = new Random();

        int cardChoose = random.nextInt(52);
        while(cards.get(cardChoose).isUsed()){
            cardChoose = random.nextInt(52);
        }
        cards.get(cardChoose).use();
        return cards.get(cardChoose);
    }

}
