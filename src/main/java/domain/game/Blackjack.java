package domain.game;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Participant;
import domain.user.Player;

import java.util.*;

public class Blackjack {
    public static final int MAX_PLAYER_NUMBER = 5;
    public static final int MAX_NAME_LENGTH = 5;
    public static final String YES = "y";
    public static final String NO = "n";

    public Judgement judgement = new Judgement();
    public Dealer dealer;
    public Scanner sc = new Scanner(System.in);
    public List<Player> players = new ArrayList<Player>();
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
        for (int i = 0; i < Judgement.CONDITION_INIT_CARDS; i++) {
            deal(dealer);
        }
        for (int i = 0; i < Judgement.CONDITION_INIT_CARDS; i++) {
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


    public void dealCardsAgain() {
        for (Player player : players) {
            giveExtraCard(player);
        }
        giveExtraCard(dealer);
    }

    public void giveExtraCard(Player player) {
        if (judgement.isBlackJack(player)) {
            System.out.println(player.getName() + " 블랙잭!!\n");
            return;
        }
        while ((!judgement.isBust(player)) && (isReceivingCard(player))) {
            deal(player);
        }
        if (judgement.isBust(player)) {
            System.out.println(" - 버스트");
        }
    }

    public void giveExtraCard(Dealer dealer) {
        while (dealer.calScore() < Judgement.CONDITION_DEALER_SCORE) {
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
        dealer.showOutcome("딜러");
        for (Player player : players) {
            player.showOutcome(player.getName());
        }
        distributeMoney();
    }

    public void distributeMoney() {
        System.out.println("\n\n##최종 수익");
        double totalSettlement = 0;
        for (Player player : players) {
            double profitPercent = judgement.winOrLose(player, dealer);
            totalSettlement -= player.doBalancing(profitPercent);
        }
        dealer.doBalancing(totalSettlement);
    }
}
