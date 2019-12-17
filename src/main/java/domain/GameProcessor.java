package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
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

    static final int ACE_TO_ONE_LIMIT = 11;
    static final int DEALER_HIT = 17;
    static final int BLACKJACK = 21;

    static int calculateDealerHandValue(Dealer dealer) {
        int dealerScore = 0;
        List<Card> dealerCards = dealer.getCards();
        for (int i = 0; i < dealerCards.size(); i++) {
            dealerScore += dealerCards.get(i).getScore();
        }
        return dealerScore;
    }

    static boolean checkDealerBlackjack(int dealerScore, Dealer dealer) {
        List<Card> dealerCards = dealer.getCards();
        if (dealerScore == ACE_TO_ONE_LIMIT && dealerCards.contains(Symbol.ACE)) {
            return true;
        }
        return false;
    }

    static List<Card> checkOverTwentyOne(int score, List<Card> cardArray) {
        if (score > BLACKJACK && cardArray.contains(Symbol.ACE)) {
            // 카드의 총합이 21을 넘을 경우 ACE의 symbol 값을 1로 바꿔주는 로직
        }
        return cardArray;
    }

    static boolean evaluateDealerHandValue(int dealerScore, Dealer dealer, List<Card> cards) {
        if (checkDealerBlackjack(dealerScore, dealer)) { // Blackjack condition
            return true;
        }
        if (dealerScore >= DEALER_HIT) { // Stop condition
            return true;
        }
        System.out.println("딜러는 현재 카드의 합이 16 이하라 카드를 한 장 더 받습니다.");
        dealer.addCard(dealCard(cards));
        return false;
    }

    static void printGameResult(ArrayList<Player> playersArray, Dealer dealer) {
        int dealerScore = calculateDealerHandValue(dealer);
        System.out.println("딜러의 카드 : " + dealer.getCards() + "; 결과 : " + dealerScore);
        for (int i = 0; i < playersArray.size(); i++) {
            System.out.println(playersArray.get(i).getName() + "의 카드 : " + playersArray.get(i).getCards() + "; 결과 : " + calculateAllPlayersHandValue(playersArray).get(i));
        }
    }
}