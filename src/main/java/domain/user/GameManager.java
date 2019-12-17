package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class GameManager {
    private ArrayList<Player> players;
    private Dealer dealer;
    private Stack<Card> deck;

    public GameManager(ArrayList<Player> players, Dealer dealer, Stack<Card> deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void playGame(Scanner scanner) {
        firstStep();
        loopPlayers(scanner);
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
            player.addCard(deck.pop());
            System.out.println(player.cardInfo());
            if (!player.isSurvive()) {
                System.out.println(player.getName() + "이 파산하였습니다.");
                break;
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
        return string.equals("y");
    }

    private void firstStep() {
        offerCardToAll();
        offerCardToAll();
        System.out.println(cardInfosOfAllMemberWithHidden());
    }

    private void pushDeck(Card card) {
        deck.push(card);
    }

    private Card popDeck() {
        Card poped;
        if (deck.isEmpty()) {
            return null;
        }
        poped = deck.pop();

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

    public String cardInfosOfAllMemberWithHidden() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealer.oneCardInfo());
        for (Player player: players) {
            stringBuilder.append(player.cardInfo());
        }
        return stringBuilder.toString();
    }
}
