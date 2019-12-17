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
    private static int theTopCardNumber = 0;        // 카드 덱의 가장 위에 있는 카드의 인덱스 번호.

    OutputPrint() {
        this.inputScanner = new InputScanner();
        this.playerList = new PlayerList();
        this.cardFactory = new CardFactory();
        this.dealer = new Dealer();
        this.cardsList = new ArrayList<>(cardFactory.create());
        Collections.shuffle(cardsList);
    }

    public PlayerList getPlayerNames() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] playerNames = inputScanner.getStringLine().split(",");
        for (String name : playerNames) {
            playerList.addPlayer(new Player(name, getBettingMoney(name)));
        }
        return playerList;
    }

    private Double getBettingMoney(String playerName) {
        println(playerName + "의 배팅 금액은?");
        return inputScanner.getDouble();
    }

    public void giveCardsFirstToPlayers() {
        println("딜러와 " + playerList.toStringNames() + "에게 2장의 카드를 나누어주었습니다.");
        dealer.addCard(getCard());
    }

    public Card getCard() {
        return cardsList.get(theTopCardNumber++);
    }

    public void wantMoreCards(String playerName) {
        println(playerName+"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n");
    }

    public void dealerLessThan16() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void finalReturn() {
        println("## 최종 수익");
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void println(String message) {
        System.out.println(message);
    }

}
