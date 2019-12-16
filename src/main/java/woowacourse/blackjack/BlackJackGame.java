package woowacourse.blackjack;

import java.util.*;

import domain.user.Dealer;
import domain.user.Player;
import domain.card.CardFactory;
import domain.card.Card;

public class BlackJackGame {
    private Scanner sc = new Scanner(System.in);
    private List<Player> players = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private HashMap<String, Double> revenue = new HashMap<String, Double>();
    private List<Card> cards =  CardFactory.create();
    private List<Card> getCardsList = new ArrayList<>();
    private List<String> blackJackMembers = new ArrayList<>();

    public void runGame() {
        this.inputData();
        this.getFirstCardsAllPeople();
        this.printPlayersAndDealerCards();
        System.out.println(this.revenue);
        if (this.isBlackJackResult()) {
            return ;
        }
        this.checkHit();
        this.printCardsResult();

    }

    private void inputData() {
        this.revenue.put("딜러", 0.0);
        List<String> playerNames = this.getPlayerNames();
        List<Double> bettingMoney = this.getBettingMoney(playerNames);
        this.setPlayerInformation(playerNames, bettingMoney);
    }

    private List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(sc.nextLine().split(","));
    }

    private List<Double> getBettingMoney(List<String> playerNames) {
        System.out.println();
        List<Double> bettingMoney = new ArrayList<>();
        for (String playerName : playerNames) {
            System.out.println("" + playerName + "의 배팅 금액은?");
            bettingMoney.add(sc.nextDouble());
            sc.nextLine();
            System.out.println();
        }
        return bettingMoney;
    }

    private void setPlayerInformation(List<String> playerNames, List<Double> bettingMoney) {
        int amount = playerNames.size();
        for (int i = 0; i < amount; i++) {
            this.players.add(new Player(playerNames.get(i), bettingMoney.get(i)));
            this.revenue.put(playerNames.get(i), 0.0);
        }
    }

    private void getFirstCardsAllPeople() {
        for (Player player: this.players) {
            player.getFirstCards(this.cards, this.getCardsList);
        }
        this.dealer.getFirstCards(this.cards, this.getCardsList);
    }

    private void printPlayersAndDealerCards() {
        System.out.print("딜러와");
        for (Player player: this.players) {
            System.out.print(" "+player.getName()+"");
        }
        System.out.println("에게 2장의 카드를 나누었습니다.");
        printDealerCards();
        for (Player player: this.players) {
            printPlayersCards(player);
        }
        System.out.println();
    }

    private void printDealerCards() {
        System.out.print("딜러: ");
        System.out.println(""+this.dealer.getCards().get(0).getSymbolName()+"" + ""+this.dealer.getCards().get(0).getTypeName()+"");
    }

    private void printPlayersCards(Player player) {
        System.out.print(""+player.getName()+"카드: ");
        List<String> stringCards = new ArrayList<>();
        for (Card card: player.getCards()) {
            stringCards.add(card.getSymbolName() + card.getTypeName());
        }
        System.out.println(String.join(", ", stringCards));
    }

    private boolean isBlackJackDealer() {
        return this.dealer.isBlackJack();
    }

    private void checkBlackJackPlayers() {
        for (Player player: this.players) {
            this.checkBlackJackPlayer(player);
        }
    }

    private void checkBlackJackPlayer(Player player) {
        if (player.isBlackJack()) {
            this.blackJackMembers.add(player.getName());
        }
    }

    private boolean isBlackJackResult() {
        if (this.isBlackJackDealer()) {
            double getMoney = this.getPlayersBettingMoney();
            this.revenue.put("딜러", getMoney);
            return true;
        }
        return this.isBlackJackPlayers();
    }

    private double getPlayersBettingMoney() {
        double getMoney = 0;
        for (Player player: this.players) {
            getMoney += getNoBlackJackPlayerMoney(player);
        }
        return getMoney;
    }

    private double getNoBlackJackPlayerMoney(Player player) {
        if (player.isBlackJack()) {
            this.revenue.put(player.getName(), this.revenue.get(player.getName()) - player.getBettingMoney());
            return player.getBettingMoney();
        }
        return 0;
    }

    private boolean isBlackJackPlayers() {
        this.checkBlackJackPlayers();
        if (this.blackJackMembers.size() > 0) {
            this.setPlayerMoney();
            return true;
        }
        return false;
    }

    private void setPlayerMoney() {
        for (Player player: this.players) {
            if (player.isBlackJack()) {
                double dealerMoney = player.getBettingMoney() * 1.5;
                this.revenue.put(player.getName(), player.getBettingMoney() + dealerMoney);
                this.revenue.put("딜러", this.revenue.get("딜러") - dealerMoney);
            }
        }
    }

    private void checkHit() {
        for (Player player: this.players) {
            this.checkHitPlayers(player);
        }
        this.checkHitDealer();
    }

    private void checkHitPlayers(Player player) {
        boolean getBooleanChoice = true;
        boolean getBooleanScore = player.getBooleanSumScore();
        while (getBooleanScore && getBooleanChoice){
            getBooleanChoice = choiceCardYesOrNo(player);
            getBooleanScore = player.getBooleanSumScore();
            printPlayersCards(player);
        }
        System.out.println();
    }

    private boolean choiceCardYesOrNo(Player player) {
        System.out.println(""+player.getName()+"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String choice = sc.nextLine();
        if (choice.equals("y")) {
            player.addCard(player.getRandomCard(this.cards, this.getCardsList));
            return true;
        }
        return false;
    }

    private void checkHitDealer() {
        if (this.dealer.getElevenEqualsAScore() < 17) {
            this.dealer.addCard(this.dealer.getRandomCard(this.cards, this.getCardsList));
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            System.out.println();
        }
    }

    private void printCardsResult() {
        printDealerCardsResult();
        for (Player player: this.players) {
            printPlayersCardsResult(player);
        }
    }

    private void printDealerCardsResult() {
        System.out.print("딜러: ");
        List<String> stringCards = new ArrayList<>();
        for (Card card: dealer.getCards()) {
            stringCards.add(card.getSymbolName() + card.getTypeName());
        }
        System.out.println(""+String.join(", ", stringCards)+" - 결과: "+this.dealer.getSumScore()+"");
    }

    private void printPlayersCardsResult(Player player) {
        System.out.print(""+player.getName()+"카드: ");
        List<String> stringCards = new ArrayList<>();
        for (Card card: player.getCards()) {
            stringCards.add(card.getSymbolName() + card.getTypeName());
        }
        System.out.println(""+String.join(", ", stringCards)+" - 결과: "+player.getSumScoreResult()+"");
    }
}