package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameProcessor {
    private static ArrayList<Player> playersArray = new ArrayList<Player>();
    static List<String> playersNamesList = new ArrayList<String>();
    static String playersNames;

    static ArrayList<Player> addPlayers(ArrayList<String> playerNamesArray) {
        for (String playerName : playerNamesArray) {
            Double playerMoney = InputProcessor.getPlayerMoney(playerName);
            playersArray.add(new Player(playerName, playerMoney));
            playersNamesList.add(playerName);
        }
        playersNames = String.join(", ", playersNamesList);
        return playersArray;
    }

    static Dealer createDealer() {
        Dealer dealer = new Dealer();
        return dealer;
    }

    static List<Card> createCard() {
        CardFactory cardFactory = new CardFactory();
        List<Card> cardsArray = new ArrayList(cardFactory.create());
        Collections.shuffle(cardsArray);
        return cardsArray;
    }

    static Card dealCard(List<Card> cardsArray) {
        Card dealtCard = cardsArray.get(0);
        cardsArray.remove(0);
        return dealtCard;
    }

    static void showFirstRoundResult(Dealer dealer, ArrayList<Player> playersArray) {
        System.out.println("딜러와 " + playersNames + "에게 2장의 카드를 딜하였습니다.");
        System.out.println("딜러 : " + dealer.getCards().get(0) + ", " +dealer.getCards().get(1));
        for (Player player : playersArray) {
            System.out.println(player.getName() + " : " + player.getCards());
        }
    }

    static int calculatePlayerHandValue(Player player) {
        List<Card> playerCards = player.getCards();
        int score = 0;
        for (int i = 0; i < playerCards.size(); i++) {
            score += playerCards.get(i).getScore();
        }
        return score;
    }

    static void askPlayerAnotherCard(Player player, List<Card> cards) {
        System.out.println(player.getName() + "님, 카드를 한장 더 받으시겠습니까?(y/n)");
        Scanner playerCardInput = new Scanner(System.in);
        String playerCardChoice = playerCardInput.next();;
        if (playerCardChoice.equals("Y") || playerCardChoice.equals("y")) {
            givePlayerAnotherCard(player, cards);
        }
    }

    static void givePlayerAnotherCard(Player player, List<Card> cards) {
        player.addCard(dealCard(cards));
        System.out.println("Get card");
        System.out.println(player.getCards());
        askPlayerAnotherCard(player, cards);
    }

    static ArrayList<Integer> calculateAllPlayersHandValue(ArrayList<Player> playersArray) {
        ArrayList<Integer> playerScoreArray = new ArrayList<Integer>();
        for (Player player : playersArray) {
            playerScoreArray.add(calculatePlayerHandValue(player));
        }
        return playerScoreArray;
    }
}