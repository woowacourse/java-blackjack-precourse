package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

public class BlackJackGame {
    public static ArrayList<Player> player;
    public static Dealer dealer;
    public static CardFactory cardFactory;
    public static List<Card> cardList;
    public static boolean[] isTaken;
    public static int moneyTaken;
    public static boolean blackJackHappend;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        gameSetting(sc);
        defaultTurn();


    }

    public static void gameSetting(Scanner sc) {
        String[] names = nameSetting(sc);
        int[] bettingMoney = bettingMoneySetting(sc, names);
        player = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            player.add(new Player(names[i], bettingMoney[i]));
        }
        dealer = new Dealer();
        cardSetting();
        System.out.println();
    }

    public static String[] nameSetting(Scanner sc) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String namesInput = sc.nextLine();
        String[] names = namesInput.split(",");
        return names;
    }

    public static int[] bettingMoneySetting(Scanner sc, String[] names) {
        int[] bettingMoney = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + "의 베팅 금액은?");
            bettingMoney[i] = sc.nextInt();
        }
        return bettingMoney;
    }

    public static void cardSetting() {
        cardList = new ArrayList<Card>();
        cardFactory = new CardFactory();
        isTaken = new boolean[52];
        cardList = cardFactory.create();
    }

    public static void defaultTurn() {
        defaultInstruction();
        defaultDistributionPrint();
        calculatingRevenueFirstTurn();
        System.out.println();
    }

    public static void defaultInstruction() {
        StringBuilder sb = new StringBuilder(player.get(0).getName());
        for (int i = 1; i < player.size(); i++) {
            sb.append(", " + player.get(i).getName());
        }
        System.out.println("딜러와 " + sb + "에게 2장의 카드를 나누었습니다.");
    }

    public static void defaultDistributionPrint() {
        defaultDistribution();
        System.out.println("딜러: " + dealer.getCards().get(1).toString());
        for (int i = 0; i < player.size(); i++) {
            System.out.println(player.get(i).getName() + "카드: " + player.get(i).cardListing());
        }
    }

    public static void defaultDistribution() {
        dealer.cardDistribution();
        dealer.cardDistribution();
        for (int i = 0; i < player.size(); i++) {
            player.get(i).cardDistribution();
            player.get(i).cardDistribution();
        }
    }

    public static void calculatingRevenueFirstTurn() {
        blackJackHappend = false;
        for (int i = 0; i < player.size(); i++) {
            individualBlackJack(player.get(i));
        }
    }

    public static void individualBlackJack(Player individualPlayer) {
        if (individualPlayer.isFirstBlackJack() && dealer.isFirstBlackJack()) {
            blackJackHappend = true;
            return;
        }
        if (dealer.isFirstBlackJack()) {
            individualPlayer.revenue -= (int) individualPlayer.getBettingMoney();
            moneyTaken += individualPlayer.revenue;
            blackJackHappend = true;
            return;
        }
        if (individualPlayer.isFirstBlackJack()) {
            individualPlayer.revenue = (int) (individualPlayer.getBettingMoney()*1.5);
            moneyTaken -= individualPlayer.revenue;
            blackJackHappend = true;
            return;
        }
    }




    public static void finalResult() {
        System.out.println("딜러: " + dealer.cardListing() + " - 결과: " + dealer.getTotal());
        for (int i = 0; i < player.size(); i++) {
            System.out.println(player.get(i).getName() + "카드: " + player.get(i).cardListing() + " - 결과: " + player.get(i).getTotal());
        }
        System.out.println();
    }

    public static void finalRevenue() {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealer.revenue);
        for (int i = 0; i < player.size(); i++) {
            System.out.println(player.get(i).getName() + ": " + player.get(i).revenue);
        }
    }




    public static void calculatingRevenueFinal() {
        moneyTaken = 0;
        for (int i = 0; i < player.size(); i++) {
            individualCalculate(player.get(i));
        }
        dealer.revenue = moneyTaken;
    }

    public static void individualCalculate(Player individualPlayer) {
        if (individualPlayer.hasBeatDealer() == 1) {
            individualPlayer.revenue = (int) individualPlayer.getBettingMoney();
            moneyTaken -= individualPlayer.getBettingMoney();
            return;
        }
        if (individualPlayer.hasBeatDealer() == 0) {
            individualPlayer.revenue = 0;
            return;
        }
        if (individualPlayer.hasBeatDealer() == -1) {
            individualPlayer.revenue = -(int) individualPlayer.getBettingMoney();
            moneyTaken += individualPlayer.getBettingMoney();
        }
    }


}
