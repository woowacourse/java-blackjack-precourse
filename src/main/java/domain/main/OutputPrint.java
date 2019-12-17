package domain.main;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutputPrint {

    private static InputScanner inputScanner;
    private static PlayerList playerList;
    private static CardFactory cardFactory;
    private static Dealer dealer;
    List<Card> cardsList;
    private static int theTopCardNumber = 0;                        // 카드 덱의 가장 위에 있는 카드의 인덱스 번호.
    private static final int FIRST_CARD_DISTRIBUTE_NUMBER = 2;      // 처음 카드가 모두에게 분배될 때의 분배 갯수.
    private static final int BOUNDARY_DEALER_MUST_MORE_CARD = 16;   // 딜러가 이보다 적은 점수를 가졌다면 카드를 반드시 더 받아야 한다.
    private static final int BLACK_JACK_NUMBER = 21;
    private static double dealerMoney = 0;

    OutputPrint() {
        this.inputScanner = new InputScanner();
        this.playerList = new PlayerList();
        this.cardFactory = new CardFactory();
        this.dealer = new Dealer();
        this.cardsList = new ArrayList<>(cardFactory.create());
        Collections.shuffle(cardsList);
    }

    public void getPlayerNames() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] playerNames = inputScanner.getStringLine().split(",");
        for (String name : playerNames) {
            playerList.addPlayer(new Player(name, getBettingMoney(name)));
        }
    }

    private Double getBettingMoney(String playerName) {
        println(playerName + "의 배팅 금액은?");
        return inputScanner.getDouble();
    }

    public void giveCardsFirstToPlayers() {
        println("딜러와 " + playerList.toStringNames() + "에게 2장의 카드를 나누어주었습니다.");
        for (int i = 0; i < FIRST_CARD_DISTRIBUTE_NUMBER; i ++ ) {
            dealer.addCard(getCard());
            distributeCardsToPlayers();
        }
        for (int i = 0; i < playerList.getSize(); i ++) {
            isBlackJack(playerList.getPlayer(i));
        }
        printLog();
    }

    private void isBlackJack(Player player) {
        if (isBlackJackDealer() && !isBlackJackPlayer(player)) {
            println("딜러는 BLACKJACK 이지만 플레이어 "+ player.getName() +"(은)는 BLACKJACK 이 아닙니다.");
            dealerMoney += player.getBettingMoney();
            player.setBettingMoney(-player.getBettingMoney());
        }
        if (isBlackJackDealer() && isBlackJackPlayer(player)) {
            println("플레이어" + player.getName() + "와 딜러 모두 BLACKJACK 입니다.");
        }
        if (!isBlackJackDealer() && isBlackJackPlayer(player)) {
            println("플레이어 " + player + "는 BLACKJACK 입니다.");
            player.setBettingMoney(player.getBettingMoney()*1.5);
        }
    }

    private boolean isBlackJackPlayer(Player player) {
        return player.getScore() == BLACK_JACK_NUMBER;
    }

    private boolean isBlackJackDealer() {
        return dealer.getScore() == BLACK_JACK_NUMBER;
    }

    /*
    모든 플레이어에게 카드를 나눠주는 메소드
     */
    private void distributeCardsToPlayers() {
        for (int i = 0; i < playerList.getSize() ; i ++) {
            distributeCardToPlayer(i);
        }
    }

    /*
    특정 플레이어에게만 카드를 나눠주는 메소드
     */
    public void distributeCardToPlayer(int playerNumber) {
        playerList.getPlayer(playerNumber).addCard(getCard());
    }

    private Card getCard() {
        return cardsList.get(theTopCardNumber++);
    }

    public void choiceMoreCard() {
        for (int i = 0; i < playerList.getSize() ; i ++) {
            Player currentPlayer = playerList.getPlayer(i);
            if (!isBlackJackPlayer(currentPlayer)) {
                getMoreCard(i, currentPlayer);
            }
        }
    }

    private void getMoreCard(int playerNumber, Player player) {
        println(player.getName()+"(은)는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        if (inputScanner.getString().equals("y")) {
            distributeCardToPlayer(playerNumber);
            System.out.println(player);
            printBust(player);
            isNotBust(playerNumber, player);
        }
    }

    private void printBust(Player player) {
        if (isBust(player)) {
            println("플레이어 " + player.getName() + "(은)는 BUST 로 패배하였습니다.");
            dealerMoney += player.getBettingMoney();
            player.setBettingMoney(- player.getBettingMoney());
        }
    }

    private void isNotBust(int playerNumber, Player player) {
        if (player.getScore() <= BLACK_JACK_NUMBER) {
            getMoreCard(playerNumber, player);
        }
    }

    private boolean isBust(Player player) {
        return player.getScore() > BLACK_JACK_NUMBER;
    }

    public void dealerLessThan16() {
        if (dealer.getScore() <= BOUNDARY_DEALER_MUST_MORE_CARD) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            dealer.addCard(getCard());
            dealerLessThan16();
        }
    }

    private boolean isBustDealer() {
        return dealer.getScore() > BLACK_JACK_NUMBER;
    }

    public void finalReturn() {
        println("## 최종 수익");
        println("딜러: " + dealerMoney);
        for (int i = 0; i < playerList.getSize(); i ++) {
            Player currentPlayer = playerList.getPlayer(i);
            println(currentPlayer.getName() + ": " +currentPlayer.getBettingMoney());
        }
    }

    private void printLog() {
        System.out.println(dealer.toLog());
        System.out.println(playerList.toLog());
    }

    public void printFinalLog() {
        System.out.println(dealer);
        System.out.println(playerList);
    }

    private void println(String message) {
        System.out.println("\n" + message);
    }
}
