package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static final int BLACKJACK = 21;
    private static final int DEALER_STANDARD = 16;
    private static final int ACE_EXTRA_SCORE = 10;

    String[] name;
    double bettingMoney[];

    List<Player> player = new ArrayList<>();
    Dealer dealer = new Dealer();
    List<Card> cards = new ArrayList<>(CardFactory.create());

    Scanner sc = new Scanner(System.in);

    public void init() {
        inputPlayerName();
        inputBettingMoney();
        createPlayer();
        Game();
    }

    public void inputPlayerName(){
        String totalInputName;

        System.out.println("참여할 플레이어의 이름을 입력하세요.(이름은 쉼표 기준으로 구분) : ");
        totalInputName = sc.next();
        name = totalInputName.split(",");
        if (!checkFormAboutName()) {
            inputPlayerName();
        }
    }

    private boolean checkFormAboutName() {
        if(name.length < 2 || name.length > 8) {
            System.out.println("player는 2~8명으로 설정해주세요.");
            return false;
        }
        return true;
    }

    private void inputBettingMoney() {
        bettingMoney = new double[name.length];
        for(int i = 0; i < name.length; i++) {
            System.out.println(name[i]+"의 배팅 금액은?");
            bettingMoney[i] = sc.nextDouble();
        }
    }

    private void createPlayer() {
        for (int i = 0; i < name.length; i++) {
            Player tmpPlayer = new Player(name[i], bettingMoney[i]);
            player.add(tmpPlayer);
        }
    }

    public void Game() {
        //딜러와 플레이어에게 처음 2장씩 카드 나눠줌
        initDistributeCard();

        while(dealer.sumScore() > DEALER_STANDARD){
            dealer.addCard(cards.remove(cards.size() - 1));
        }
        

    }

    public void initDistributeCard() {
        shuffleCard();
        distributeCard();
    }

    public void shuffleCard() {
        Collections.shuffle(cards);
    }

    public void distributeCard() {
        for(int i = 0; i < 2; i++) {
            distributeCardToPlayer();
            distributeCardToDealer();
        }
       /* for(int i=0;i<player.size();i++){
            System.out.println(player.get(i).getCard());
        }*/
    }

    public void distributeCardToPlayer() {
        for(int i = 0; i < player.size(); i++) {
            player.get(i).addCard(cards.remove(cards.size() - 1));
        }
    }

    public void distributeCardToDealer() {
        dealer.addCard(cards.remove(cards.size() - 1));
    }
}
