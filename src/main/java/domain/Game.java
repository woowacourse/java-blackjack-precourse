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
    int index = 0;

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
        initDistributeCard();

        for(index = 0; index < player.size(); index++) {
            //블랙잭 여부 확인

            askOneMoreCard();
        }
        giveMoreCardToDealer();
        finishGame();

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

    public void askOneMoreCard() {
        System.out.println(player.get(index).getinfo() + "는 한장의 카드를 더 받겠습니까? (y, n)");
        String answer = sc.next();
        boolean flag = true;
        if (Objects.equals(answer, "y") && flag) {
            actAddOneMoreCard();
            flag = checkIfOver21();
        }
        if(Objects.equals(answer, "n") || !flag){
            return;
        }
        askOneMoreCard();
    }

    public void actAddOneMoreCard() {
        player.get(index).addCard(cards.remove(cards.size() - 1));
        System.out.print(player.get(index).getinfo()+"카드: ");
        System.out.println(player.get(index).toString());
    }

    public boolean checkIfOver21() {
        if(player.get(index).sumScore() > BLACKJACK) {
            player.remove(index--);
            System.out.println("21을 초과하여 게임에서 지셨습니다.");
            return false;
        }
        return true;
    }

    public void giveMoreCardToDealer() {
        if(dealer.sumScore() <= DEALER_STANDARD) {
            System.out.println("딜러가 16 이하라 한장 더 받았습니다.");
            dealer.addCard(cards.remove(cards.size() - 1));
            giveMoreCardToDealer();
            return;
        }
        if(dealer.sumScore() > BLACKJACK) {
            LooseDealerBurst();
        }
    }

    public void LooseDealerBurst() {
        System.out.println("딜러가 21을 초과하여 패하였습니다.\n");
        dealer.ifBurst = true;
        for(int i = 0; i < player.size(); i++) {
            calculateLooseDealerBurst(i);
        }
    }

    public void calculateLooseDealerBurst(int i) {
        if (!player.get(i).burstPlayer && !player.get(i).ifBlackJack()) {
            player.get(i).getBettingMoney(1);
            dealer.minusCost(player.get(i).reward);
            System.out.println("*****딜러: "+dealer.finalCost()+"  플레이어 : "+player.get(i).getReward());
        }
    }

    public void finishGame() {
        for(int i = 0; i < player.size(); i++) {
            System.out.println(player.get(i).getinfo()+" 승부 확인");
            findWinner(i);
        }
        printDealerCard();
        printPlayerCard();
        System.out.println("최종 수익");
        System.out.println("딜러: "+dealer.finalCost());
        for(int i = 0; i < player.size(); i++) {
            System.out.println(player.get(i).getinfo()+": "+player.get(i).getReward());
        }
    }

    public void findWinner(int i) {
        if(player.get(i).ifBlackJack() || player.get(i).burstPlayer || dealer.ifBurst){
            System.out.println("블랙잭으로 끝");
            return;
        }
        System.out.println(player.get(i).getinfo()+" 비교시작");
        //comparePlayerAndDealer(i);
    }

}