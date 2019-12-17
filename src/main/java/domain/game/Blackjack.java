package domain.game;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Participant;
import domain.user.Player;

import java.util.*;

public class Blackjack {
    public static final int MAX_PLAYER_NUMBER = 5;
    public static final int MAX_NAME_LENGTH = 5;
    public static final int CONDITION_SCORE = 21;
    public static final int CONDITION_INIT_CARDS = 2;
    public static final int CONDITION_DEALER_SCORE = 17;
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
        for (int i = 0; i < CONDITION_INIT_CARDS; i++) {
            deal(dealer);
        }
        for (int i = 0; i < CONDITION_INIT_CARDS; i++) {
            for (Player player : players) {
                deal(player);
            }
        }
        System.out.println("\n딜러와 각 플레이어에게 카드를 2장씩 나누었습니다.");
    }

    public void deal(Participant participant) {
        Card card = cards.pop();
        participant.addCard(card);
    }

    public boolean isBlackJack(Participant participant) {
        return (participant.calScore() == CONDITION_SCORE) && participant.withInitCards();
    }

    public boolean isBust(Participant participant) {
        if (participant.calScore() > CONDITION_SCORE) {
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
        }
        if (isBust(player)) {
            System.out.println(" - 버스트");
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
        dealer.showOutcome();
        for (Player player : players) {
            player.showOutcome();
        }
        distributeMoney();
    }

    public void distributeMoney() {
        System.out.println("\n\n##최종 수익");
        double totalSettlement = 0;
        for (Player player : players) {
            double profitPercent = winOrLose(player);
            totalSettlement -= player.doBalancing(profitPercent);
        }
        dealer.doBalancing(totalSettlement);
    }

    public double winOrLose(Player player) {
        final double BLACKJACK_WIN = 1.5;
        final double WIN = 1;
        final double DRAW = 0;
        final double LOSE = -1;

        /* user가 버스트인 경우 승패 판단 */
        if (isBust(player)) {
            return LOSE;
        }

        /* player가 블랙잭인 경우 승패 판단 */
        if (isBlackJack(player)) {
            if (isBlackJack(dealer)) {
                return DRAW;
            }
            return BLACKJACK_WIN;
        }

        /* player가 버스트도 블랙잭도 아닌 경우 승패 판단 */
        if (isBlackJack(dealer)) {
            return LOSE;
        }
        if (isBust(dealer)) {
            return WIN;
        }
        if (player.calScore() > dealer.calScore()) {
            return WIN;
        }
        if (player.calScore() == dealer.calScore()) {
            return DRAW;
        }
        return LOSE;
    }
}
