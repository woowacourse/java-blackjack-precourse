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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        gameSetting(sc);


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

}
