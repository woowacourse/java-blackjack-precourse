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
    private List<Card> cards =  CardFactory.create();
    private List<Card> getCardsList = new ArrayList<>();

    public void runGame() {
        List<String> playerNames = this.getPlayerNames();
        List<Double> bettingMoney = this.getBettingMoney(playerNames);
        this.setPlayerInformation(playerNames, bettingMoney);
        this.receiveTwice();
        this.printPlayersAndDealerCards();
        for (Player player: this.players) {
            this.checkPointPlayers(player);
        }
        this.checkPointDealer();

        printDealerCardsResult();
        for (Player player: this.players) {
            printPlayersCardsResult(player);
        }
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
        }
    }

    private void receiveTwice() {
        for (int i = 0; i < 2; i++) {
            receiveOnceToPlayers();
            receiveOnceToDealer();
        }
    }

    private void receiveOnceToDealer() {
        this.dealer.addCard(getRandomCard());
    }

    private void receiveOnceToPlayers() {
        for (Player player: this.players) {
            this.handCardsToPlayer(player);
        }
    }

    private void handCardsToPlayer(Player player) {
        player.addCard(this.getRandomCard());
    }

    private Card getRandomCard() {
        Random random = new Random();
        Card getCard = null;
        boolean checkCard = true;
        while (checkCard) {
            getCard = this.cards.get(random.nextInt(this.cards.size()));
            checkCard = doubleCheck(getCard);
        }
        this.getCardsList.add(getCard);
        return getCard;
    }

    private boolean doubleCheck(Card getCard) {
        return this.getCardsList.contains(getCard);
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
        System.out.println(""+String.join(", ", stringCards)+" - 결과: "+player.getSumScore()+"");
    }

    private void checkPointPlayers(Player player) {
        boolean getBooleanChoice = true;
        boolean getBooleanScore = player.getBooleanSumScore();
        while (getBooleanScore && getBooleanChoice){
            getBooleanChoice = choiceCardYesOrNo(player);
            getBooleanScore = player.getBooleanSumScore();
            printPlayersCards(player);
        }
        System.out.println();
    }

    private void checkPointDealer() {
        if (this.dealer.getSumScore() < 17) {
            receiveOnceToDealer();
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            System.out.println();
        }
    }

    private boolean choiceCardYesOrNo(Player player) {
        System.out.println(""+player.getName()+"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String choice = sc.nextLine();
        if (choice.equals("y")) {
            this.oneMoreCard(player);
            return true;
        }
        return false;
    }

    private void oneMoreCard(Player player) {
        if (player.getSumScore() < 21) {
            this.handCardsToPlayer(player);
        }
    }
}
