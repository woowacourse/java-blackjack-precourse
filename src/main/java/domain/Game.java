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
        if(dealer.ifBlackJack()) {
            dealerIsBlackJack();
            return;
        }
        for(index = 0; index < player.size(); index++){
            askOneMoreOnlyForNotBJPlayer();
        }
        giveMoreCardToDealer();
        finishGame();
    }

    public void dealerIsBlackJack() {
        for(int i=0;i<player.size();i++) {
            checkIfPlayerIsBJWhenDealerIsBJ(i);
        }
    }

    public void checkIfPlayerIsBJWhenDealerIsBJ(int i) {
        if(player.get(i).ifBlackJack()){
            player.get(index).multiInterestToBettinfMoney(0);
        }
        player.get(index).multiInterestToBettinfMoney(-1);
    }

    public void askOneMoreOnlyForNotBJPlayer() {
        if(!checkPlayerBlackJack()){
            askOneMoreCard();
        }
    }

    public void initDistributeCard() {
        shuffleCard();
        distributeCard();
        printInfo();
        printDealerCard();
        printPlayerCard();
        System.out.println();
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
        System.out.print("\n딜러와 ");
        for(int i = 0; i < player.size(); i++) {
            System.out.print(player.get(i).getName());
        }
        System.out.println("에게 2장의 카드를 나누었습니다.\n");
    }

    public void printDealerCard() {
        System.out.print("딜러: ");
        System.out.println(dealer.toStringFirstTurn());
    }

    public void printPlayerCard() {
        for(int i = 0; i < player.size(); i++) {
            System.out.print(player.get(i).getName()+"카드: ");
            System.out.println(player.get(i).toString());
        }
    }

    public boolean checkPlayerBlackJack() {
        if(player.get(index).ifBlackJack()) {
            player.get(index).multiInterestToBettinfMoney(1.5);
            dealer.minusCost(player.get(index).getReward());
            return true;
        }
        return false;
    }

    public String askQuestion() {
        String answer;
        System.out.println(player.get(index).getName() + "는 한장의 카드를 더 받겠습니까? (y, n)");
        answer = sc.next();
        if (!Objects.equals(answer, "y") && !Objects.equals(answer, "n")) {
            System.out.println("y 혹은 n으로 답해주세요.");
            return askQuestion();
        }
        return answer;
    }

    public void askOneMoreCard() {
        String answer;
        answer = askQuestion();
        boolean ifUnder21 = true;
        if (Objects.equals(answer, "y") && ifUnder21) {
            actAddOneMoreCard();
            ifUnder21 = checkIfOver21();
        }
        if(!Objects.equals(answer, "n") && ifUnder21){
            askOneMoreCard();
        }

    }

    public void actAddOneMoreCard() {
        player.get(index).addCard(cards.remove(cards.size() - 1));
        System.out.print(player.get(index).getName()+"카드: ");
        System.out.println(player.get(index).toString());
    }

    public boolean checkIfOver21() {
        if(player.get(index).sumScore() > BLACKJACK) {
            player.get(index).burstPlayer = true;
            System.out.println("21을 초과하여 게임에서 지셨습니다.\n");
            LoosePlayerBurst();
            return false;
        }
        return true;
    }

    public void giveMoreCardToDealer() {
        if(dealer.sumScore() <= DEALER_STANDARD) {
            System.out.println("딜러가 16 이하라 한장 더 받았습니다.\n");
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
            player.get(i).multiInterestToBettinfMoney(1);
            dealer.minusCost(player.get(i).reward);
        }
    }

    public void LoosePlayerBurst() {
        player.get(index).multiInterestToBettinfMoney(-1);
        dealer.addCost(player.get(index).getBetting());
    }

    public void finishGame() {
        for(int i = 0; i < player.size(); i++) {
            findWinner(i);
        }
        printFinalDealerCard();
        printFinalPlayerCard();
        System.out.println("\n최종 수익");
        System.out.println("딜러: "+dealer.finalCost());
        for(int i = 0; i < player.size(); i++) {
            System.out.println(player.get(i).getName()+": "+player.get(i).getReward());
        }
    }

    public void findWinner(int i) {
        if(player.get(i).ifBlackJack() || player.get(i).burstPlayer || dealer.ifBurst){
            return;
        }
        comparePlayerAndDealer(i);
    }

    public void comparePlayerAndDealer(int i) {
        if (player.get(i).sumScore() > dealer.sumScore()) {
            player.get(i).multiInterestToBettinfMoney(1);
            dealer.minusCost(player.get(i).getBetting());
        }
        if (player.get(i).sumScore() < dealer.sumScore()) {
            player.get(i).multiInterestToBettinfMoney(-1);
            dealer.addCost(player.get(i).getBetting());
        }
    }

    public void printFinalDealerCard() {
        System.out.print("딜러: ");
        System.out.println(dealer.toString()+" - 결과: "+dealer.sumScore());
    }

    public void printFinalPlayerCard() {
        for(int i = 0; i < player.size(); i++) {
            System.out.print(player.get(i).getName()+"카드: ");
            System.out.println(player.get(i).toString()+" - 결과: "+player.get(i).sumScore());
        }
    }
}

