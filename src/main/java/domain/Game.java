package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

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
        //boolean flag = false;
        //딜러와 플레이어에게 처음 2장씩 카드 나눠줌
        initDistributeCard();
        //처음 두 장의 카드 합이 21이면 블랙잭
        /*
        flag = checkBlackJack(flag);
        //flag가 false인 경우만 뒤에 코드 동작
        if(flag == false){
            while(dealer.sumScore() < DEALER_STANDARD){
                dealer.addCard(cards.remove(cards.size() - 1));
            }
        }
*/
        for(int i = 0; i < player.size(); i++) {
            //블랙잭 여부 확인
            askOneMoreCard(i);
        }
    }

    public void initDistributeCard() {
        shuffleCard();
        distributeCard();
        printInfo();
    }

    public void shuffleCard() {
        Collections.shuffle(cards);
    }

    public void distributeCard() {
        for(int i = 0; i < 2; i++) {
            distributeCardToPlayer();
            distributeCardToDealer();
        }
    }

    public void distributeCardToPlayer() {
        for(int i = 0; i < player.size(); i++) {
            player.get(i).addCard(cards.remove(cards.size() - 1));
        }
    }

    public void distributeCardToDealer() {
        dealer.addCard(cards.remove(cards.size() - 1));
    }

    public void printInfo() {
        System.out.print("딜러와 ");
        for(int i = 0; i < player.size(); i++) {
            System.out.print(player.get(i).getinfo());
        }
        printDealerCard();
        printPlayerCard();
    }

    public void printDealerCard() {
        System.out.println("에게 2장의 카드를 나누었습니다.");
        System.out.print("딜러: ");
        System.out.println(dealer.toString());
    }

    public void printPlayerCard() {
        for(int i = 0; i < player.size(); i++) {
            System.out.print(player.get(i).getinfo()+"카드: ");
            System.out.println(player.get(i).toString());
        }
    }

    public void askOneMoreCard(int i) {
        System.out.println(player.get(i).getinfo() + "는 한장의 카드를 더 받겠습니까? (y, n)");
        String answer = sc.next();
        boolean flag = true;
        if (Objects.equals(answer, "y") && flag) {
            player.get(i).addCard(cards.remove(cards.size() - 1));
            flag = checkCurrScore(i);
        }
        if(Objects.equals(answer, "n") || !flag){
            return;
        }
        askOneMoreCard(i);
    }

    public boolean checkCurrScore(int i) {
        if(player.get(i).sumScore() > BLACKJACK) {
            player.get(i).getBettingMoney(0);
            System.out.println("21을 초과하여 게임에서 지셨습니다.");
            return false;
        }
        return true;
    }

   /* public boolean checkBlackJack(boolean flag) {
        //블랙잭이면 flag true
        boolean dealerBJ = false;
        List<Integer> bjPlayer = new ArrayList<>();

        if(dealer.sumScore() == BLACKJACK) {
            flag = true;
            dealerBJ = true;
        }
        for(int i = 0; i < player.size(); i++){
            if(player.get(i).sumScore() == BLACKJACK){
                flag = true;
                bjPlayer.add(i);
            }
        }
        if(bjPlayer.size() > 0) {
            if(dealerBJ == true) {
                //배팅한 금액만 돌려받음
                for(int i = 0; i< bjPlayer.size(); i++) {
                    player.get(bjPlayer.get(i)).getBettingMoney(1);
                }
            }
            if(dealerBJ == false) {
                //배팅 금액의 1.5배를 받음
                for(int i = 0; i< bjPlayer.size(); i++) {
                    player.get(bjPlayer.get(i)).getBettingMoney(1.5);
                }
            }
            //블랙잭이 아닌 나머지 플레이어들 수익 0으로
            for(int i = 0; i < player.size(); i++){
                if(!bjPlayer.contains(i)){
                    player.get(bjPlayer.get(i)).getBettingMoney(0);
                }
            }
        }

        return flag;
    }*/
}
