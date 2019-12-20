package domain.user;

import domain.card.Card;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 게임의 전체적인 운영을 담당하는 객체
 */
public class GameManager {
    private ArrayList<Player> players;
    private Dealer dealer;
    private Deck deck;

    public GameManager(ArrayList<Player> players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void playGame(Scanner scanner) {
        firstStep();
        loopPlayers(scanner);
        loopDealer();
        result();
    }

    private void result() {
        double dealerProfit = 0;
        System.out.println(dealer.cardInfo());
        System.out.println("## 최종수익");
        for (Player player: players) {
            Score dealerOptimizedSum = dealer.getOptimizedSum();
            double playerMoney = player.getMoney(dealerOptimizedSum.getScore());
            String playerName = player.getName();
            System.out.println(playerName + ": " + playerMoney);
            dealerProfit -= playerMoney;
        }
        System.out.println("딜러: " + dealerProfit);
    }

    private void loopDealer() {
        while (dealer.getOptimizedSum().getScore() < Score.DEALER_BASIS) {
            dealer.addCard(popDeck());
            System.out.println("딜러가 카드 한 장을 받았습니다.");
            if (!dealer.isSurvive()) {
                System.out.println("딜러가 파산하였습니다");
                return;
            }
        }
    }

    private void loopPlayers(Scanner scanner) {
        for (Player player: players) {
            loopPlayerQuery(player, scanner);
        }
    }

    private void loopPlayerQuery(Player player, Scanner scanner) {
        System.out.println(player.getName() + "은 카드 한 장을 더 받겠습니까?");
        String response = inputResponseOnlySmallYOrN(scanner);
        while (checkSmallY(response)) {
            player.addCard(deck.draw());
            System.out.println(player.cardInfo());
            if (!player.isSurvive()) {
                System.out.println(player.getName() + "이 파산하였습니다.");
                return;
            }
            response = inputResponseOnlySmallYOrN(scanner);
        }
    }

    private String inputResponseOnlySmallYOrN(Scanner scanner) {
        String string;
        string = scanner.next();
        while (!string.equals("y") && !string.equals("n")) {
            System.out.println("y나 n만 입력해주세요.");
            string = scanner.next();
        }
        return string;
    }

    private boolean checkSmallY(String string) {
        YesOrNo yesOrNo = new YesOrNo(string);
        return yesOrNo.isYes();
    }

    private void firstStep() {
        offerCardToAll();
        offerCardToAll();
        System.out.println(cardInfosOfAllMemberWithHidden());
    }

    private void putInDeckRandomLocation(Card card) {
        deck.putRandomLocation(card);
    }

    private Card popDeck() {
        Card poped;
        if (deck.isEmpty()) {
            return null;
        }
        poped = deck.draw();

        return poped;
    }

    private void offerCardToAll() {
        Card poped;
        for (Player player: players) {
            poped = popDeck();
            player.addCard(poped);
        }
        poped = popDeck();
        dealer.addCard(poped);
    }

    private void offerCardToPlayer(int index) {
        Card poped = popDeck();
        players.get(index).addCard(poped);
    }

    private void offerCardToDealer() {
        Card poped = popDeck();
        dealer.addCard(poped);
    }

    public String bettingInfos() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player: players) {
            stringBuilder.append(player.bettingInfo());
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public String cardInfosOfAllMemberNotHidden() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealer.cardInfo());
        for (Player player: players) {
            stringBuilder.append(player.cardInfo());
        }
        return stringBuilder.toString();
    }

    private String cardInfosOfAllMemberWithHidden() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealer.oneCardInfo());
        for (Player player: players) {
            stringBuilder.append(player.cardInfo());
        }
        return stringBuilder.toString();
    }
}
