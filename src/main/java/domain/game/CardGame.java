package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardGame {

    static Scanner sc = new Scanner(System.in);
    static final int FIRST_CARD_COUNT = 2;
    static final int NORMAL_CARD_COUNT = 1;
    static final int DEALER_COUNT = 1;
    public static boolean[] isDuplicateCard;
    public static boolean[] isBlackJackArr;

    public static List<String> insertPlayerNameStr() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerStr = sc.nextLine();
        String[] playerNameArr = dividePlayerNameStrToRest(playerStr);
        List<String> playerNameList = deleteSpacePlayerNameArr(playerNameArr);
        while (!checkPlayerNameList(playerNameList)) {
            System.out.println("이름에 공백이 있습니다! 다시 입력해주세요");
            playerStr = sc.nextLine();
            playerNameArr = dividePlayerNameStrToRest(playerStr);
            playerNameList = deleteSpacePlayerNameArr(playerNameArr);
        }
        return playerNameList;
    }

    public static String[] dividePlayerNameStrToRest(String playerStr) {
        return playerStr.split(",");
    }

    public static List<String> deleteSpacePlayerNameArr(String[] playerNameArr) {
        return Arrays.asList(playerNameArr).stream()
                .map(element -> element.replace(" ", ""))
                .collect(Collectors.toList());
    }

    public static boolean checkPlayerNameList(List<String> playerNameList) {
        return playerNameList.stream()
                .allMatch(element -> !element.equals(""));
    }

    public static List<Player> insertPlayerMoneyStr(List<String> playerNameList) {
        List<Player> playerList = new ArrayList<>();
        playerNameList.stream().
                forEach(element -> {
                    System.out.println(element + "의 베팅 금액은?");
                    int bettingMoney = insertBettingMoney();
                    playerList.add(new Player(element, bettingMoney));
                });
        return playerList;
    }

    public static int insertBettingMoney() {
        int bettingMoney = sc.nextInt();
        sc.nextLine();
        while (checkBettingMoney(bettingMoney)) {
            System.out.println("베팅 금액이 너무 적습니다! 다시 입력해주세요!");
            bettingMoney = sc.nextInt();
        }
        return bettingMoney;
    }

    public static boolean checkBettingMoney(int bettingMoney) {
        if (bettingMoney <= 0) {
            return true;
        }
        return false;
    }

    public static void printYourCards(Dealer dealer, List<Player> playerList) {
        dealer.printMyCards(dealer.getName());
        playerList.forEach(element -> element.printMyCards(element.getName()));
    }

    public static void printGiveCards(Dealer dealer, List<Player> playerList) {
        List<String> playerStrList = new ArrayList<>();
        playerList.forEach(element -> playerStrList.add(element.getName()));
        String result = dealer.getName()
                + "와 "
                + String.join(", ", playerStrList)
                + "에게 "
                + FIRST_CARD_COUNT +
                "장의 카드를 나누었습니다.";
        System.out.println(result);
    }

    public static void giveCards(Dealer dealer, List<Player> playerList, List<Card> originalCardList) {
        printGiveCards(dealer, playerList);
        dealer.takeCards(FIRST_CARD_COUNT, originalCardList, isDuplicateCard);
        playerList.forEach(element -> {
            element.takeCards(FIRST_CARD_COUNT, originalCardList, isDuplicateCard);
        });
    }

    public static boolean isBlackJack(List<Card> cardList) {
        if (cardList.size() != 2) return false;
        int cardSum = 0;
        for (int i = 0; i < cardList.size(); i++) {
            cardSum += cardList.get(i).getSymbol().getScore();
        }
        if (cardSum == 21) {
            return true;
        }
        return false;
    }

    public static boolean isOverTwentyOne(int number) {
        if (number > 21) {
            return true;
        }
        return false;
    }

    public static int sumNormalCardList(List<Card> cardList) {
        int cardListSum = 0;
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getSymbol() == Symbol.ACE) {
                cardListSum += 11;
                continue;
            }
            cardListSum += cardList.get(i).getSymbol().getScore();
        }
        return cardListSum;
    }

    public static int sumCardList(List<Card> cardList) {
        int cardListSum = sumNormalCardList(cardList);
        if (cardListSum == 21) {
            return 21;
        }
        if (cardListSum > 21 && countAceCard(cardList) > 0) {
            for (int i = 1; i <= countAceCard(cardList); i++) {
                if (cardListSum - 10 <= 21) {
                    return cardListSum - 10;
                }
                cardListSum -= 10;
            }
        }
        return cardListSum;
    }

    public static int countAceCard(List<Card> cardList) {
        return (int) cardList.stream().filter(element -> element.getSymbol() == Symbol.ACE).count();
    }

    public static int answerMoreCard(Player player, List<Card> originalCardList) {
        System.out.println(player.getName() + "는/은 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String answer = sc.nextLine();
        if (answer.equals("y")) {
            player.takeCards(NORMAL_CARD_COUNT, originalCardList, isDuplicateCard);
            player.printMyCards(player.getName());
            return 1;
        }
        if (answer.equals("n")) {
            player.printMyCards(player.getName());
            return 0;
        }
        System.out.println("잘못된 문자를 입력하셨으므로 자동으로 더 받지 않습니다.");
        player.printMyCards(player.getName());
        return 0;
    }

    public static int checkplayerMoreCard(Player player, List<Card> originalCardList) {
        while (answerMoreCard(player, originalCardList) == 1) {

        }
        return sumCardList(player.getCards());
    }

    public static int checkDealerMoreCard(Dealer dealer, List<Card> originalCardList) {
        int sumDealerCardList = sumCardList(dealer.getCards());
        while (sumDealerCardList <= 16) {
            dealer.takeCards(NORMAL_CARD_COUNT, originalCardList, isDuplicateCard);
            sumDealerCardList = sumCardList(dealer.getCards());
        }
        return sumDealerCardList;
    }

    public static int playDealerScore(Dealer dealer, List<Card> originalCardList) {
        return subtractTwentyOne(checkDealerMoreCard(dealer, originalCardList));
    }

    public static List<Integer> playPlayerScore(List<Player> playerList, List<Card> originalCardList) {
        List<Integer> playerScoreList = new ArrayList<>();
        playerList.forEach(element -> {
            playerScoreList.add(subtractTwentyOne(checkplayerMoreCard(element, originalCardList)));
        });
        return playerScoreList;
    }

    public static double[] endCardGame(int dealerScore, List<Integer> playerScoreList, List<Player> playerList) {
        int dealerMoney = 0;
        double[] resultplayerMoneyArr = new double[playerScoreList.size() + 1];
        for (int i = 0; i < playerScoreList.size(); i++) {
            if (isBlackJackArr[i]) {
                resultplayerMoneyArr[i] = (playerList.get(i).getBettingMoney() * 1.5);
                dealerMoney -= (playerList.get(i).getBettingMoney() * 1.5);
                continue;
            }
            if (isOverTwentyOne(dealerScore) || dealerScore > playerScoreList.get(i)) {
                resultplayerMoneyArr[i] = playerList.get(i).getBettingMoney();
                dealerMoney -= playerList.get(i).getBettingMoney();
                continue;
            }
            resultplayerMoneyArr[i] -= playerList.get(i).getBettingMoney();
            dealerMoney += playerList.get(i).getBettingMoney();
        }
        resultplayerMoneyArr[playerScoreList.size()] = dealerMoney;
        return resultplayerMoneyArr;
    }

    public static int subtractTwentyOne(int number) {
        return Math.abs(number - 21);
    }

    public static void printResultCardGame(double[] resultScoreList, List<Player> playerList) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + (int) resultScoreList[resultScoreList.length - DEALER_COUNT]);
        for (int i = 0; i < resultScoreList.length - DEALER_COUNT; i++) {
            System.out.println(playerList.get(i).getName() + ": " + (int) resultScoreList[i]);
        }
    }

    public static void checkBlackJack(Dealer dealer, List<Player> playerList) {
        isBlackJackArr = new boolean[playerList.size() + DEALER_COUNT];
        isBlackJackArr[playerList.size()] = isBlackJack(dealer.getCards());
        for (int i = 0; i < playerList.size(); i++) {
            isBlackJackArr[i] = isBlackJack(playerList.get(i).getCards());
        }
    }

    public static void run() {
        List<String> playerNameList = insertPlayerNameStr();
        List<Player> playerList = insertPlayerMoneyStr(playerNameList);
        List<Card> originalCardList = CardFactory.create();
        isDuplicateCard = new boolean[originalCardList.size()];
        Dealer dealer = new Dealer();
        giveCards(dealer, playerList, originalCardList);
        checkBlackJack(dealer, playerList);
        printYourCards(dealer, playerList);
        int dealerScore = playDealerScore(dealer, originalCardList);
        List<Integer> playerScoreList = playPlayerScore(playerList, originalCardList);
        double[] resultScoreList = endCardGame(dealerScore, playerScoreList, playerList);
        printResultCardGame(resultScoreList, playerList);
    }


}
