package domain.game;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

public class Blackjack {
    public static final int MAX_PLAYER_NUMBER = 5;
    public static final int MAX_NAME_LENGTH = 5;
    public final int CONDITION_SCORE = 21;
    public final int CONDITION_NUMBER_OF_CARDS = 2;
    public final int CONDITION_DEALER_SCORE = 17;
    public final String YES = "y";
    public final String NO = "n";
    public Dealer dealer;
    public List<Player> players = new ArrayList<Player>();
    public Scanner sc = new Scanner(System.in);
    private Stack<Card> cards = new Stack<>();

    public Blackjack(List<Card> cards) {
        this.cards.addAll(cards);
        dealer = new Dealer();
    }

    public void start() {
        createPlayers();
        Collections.shuffle(cards);
        dealCards();
        dealCardsAgain();
        showResult();
    }


    public void createPlayers() {
        String[] nameArr = inputNames();
        for (String name : nameArr) {
            System.out.println(name + "의 배팅 금액을 입력해주세요.");
            double bettingMoney = getBettingMoney();
            players.add(new Player(name, bettingMoney));
        }
    }

    public String[] inputNames() {
        String names;
        String[] nameArr;
        do {
            System.out.printf("게임에 참여할 사람의 이름을 입력하세요. (최대 %d명, %d글자 이내, 쉼표로 분리)%n",
                    MAX_PLAYER_NUMBER, MAX_NAME_LENGTH);
            names = sc.nextLine();
            nameArr = names.split(",", -1);
        } while (!checkNumber(nameArr) || !validateNames(nameArr));
        return nameArr;
    }

    public boolean checkNumber(String[] nameArr) {
        return nameArr.length <= MAX_PLAYER_NUMBER;
    }

    public boolean validateNames(String[] nameArr) {
        for (int i = 0; i < nameArr.length; i++) {
            nameArr[i] = nameArr[i].trim();
            if ((nameArr[i].length() == 0) || (nameArr[i].length() > MAX_NAME_LENGTH)) {
                return false;
            }
        }
        return true;
    }

    public double getBettingMoney() {
        String betting = sc.nextLine();
        while (!validateBetting(betting)) {
            System.out.printf("잘못된 배팅 금액입니다. (입력값: %s)%n", betting);
            betting = sc.nextLine();
        }
        return Double.parseDouble(betting);
    }

    public boolean validateBetting(String betting) {
        try {
            return (Double.parseDouble(betting) > 0);
        } catch (Exception e) {
            return false;
        }
    }


    public void dealCards() {
        System.out.println("딜러와 각 플레이어에게 카드를 2장씩 나누었습니다.");
        deal(dealer);
        deal(dealer);
        dealer.openOneCard();
        for (Player player : players) {
            deal(player);
            deal(player);
            player.showCards();
        }
    }

    public void deal(Player player) {
        Card card = cards.pop();
        player.addCard(card);
    }

    public void deal(Dealer dealer) {
        Card card = cards.pop();
        dealer.addCard(card);
    }


    public boolean isBlackJack(Player player) {
        return (player.calScore() == CONDITION_SCORE) && (player.getNumberOfCards() == CONDITION_NUMBER_OF_CARDS);
    }

    public boolean isBlackJack(Dealer dealer) {
        return (dealer.calScore() == CONDITION_SCORE) && (dealer.getNumberOfCards() == CONDITION_NUMBER_OF_CARDS);
    }

    public boolean isBust(Player player) {
        if (player.calScore() > CONDITION_SCORE) {
            System.out.println(player.getName() + " 버스트");
            return true;
        }
        return false;
    }

    public boolean isBust(Dealer dealer) {
        if (dealer.calScore() > CONDITION_SCORE) {
            System.out.println("딜러 버스트");
            return true;
        }
        return false;
    }


    public void dealCardsAgain() {
        for (Player player : players) {
            giveExtraCard(player);
        }
        giveExtraCard(dealer);
    }

    public void giveExtraCard(Player player) {
        if (isBlackJack(player)) {
            System.out.println(player.getName() + " 블랙잭!!\n");
            return;
        }
        while ((!isBust(player)) && (isReceivingCard(player))) {
            deal(player);
            player.showCards();
        }
    }

    public void giveExtraCard(Dealer dealer) {
        while (dealer.calScore() < CONDITION_DEALER_SCORE) {
            deal(dealer);
            System.out.println("딜러는 카드의 합이 16이하라 한 장의 카드를 더 받았습니다.");
        }
    }

    public boolean isReceivingCard(Player player) {
        String decision = player.needMoreCard(sc);
        while (!isValidAnswer(decision)) {
            System.out.println("입력이 잘못되었습니다. 한 장의 카드를 더 받으시겠습니까? (예: y, 아니오: n)");
            decision = sc.nextLine();
        }
        return YES.equals(decision);
    }

    public boolean isValidAnswer(String decision) {
        return (decision.equals(YES) || decision.equals(NO));
    }


    public void showResult() {
        dealer.showCards();
        System.out.println("결과: " + dealer.calScore() + "점");
        for (Player player : players) {
            player.showCards();
            System.out.println("결과: " + player.calScore() + "점");
        }

        double dealerSettlement = 0;
        for (Player player : players) {
            double result = getResult(player);
            dealerSettlement -= player.doBalancing(result);
        }
        dealer.doBalancing(dealerSettlement);
    }

    public double getResult(Player player) {
        final double BLACKJACK_WIN = 1.5;
        final double WIN = 1;
        final double DRAW = 0;
        final double LOSE = -1;

        if (isBlackJack(player)) {
            if (isBlackJack(dealer)) {
                return DRAW;
            } else {
                return BLACKJACK_WIN;
            }
        }

        if (isBust(player)) {
            return LOSE;
        }

        if (isBlackJack(dealer)) {
            return LOSE;
        } else if (isBust(dealer)) {
            return WIN;
        } else {
            if (player.calScore() > dealer.calScore()) {
                return WIN;
            } else if (player.calScore() == dealer.calScore()) {
                return DRAW;
            } else {
                return LOSE;
            }
        }
    }
}
