package domain.game;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

public class Blackjack {
    public final int MAX_PLAYER_NUMBER = 5;
    public final int MAX_NAME_LENGTH = 5;
    public Dealer dealer;
    public List<Player> players = new ArrayList<Player>();
    public Scanner sc = new Scanner(System.in);
    private Stack<Card> cards = new Stack<>();

    public Blackjack(List<Card> cards) {
        this.cards.addAll(cards);
        dealer = new Dealer();
    }

    public void ready() {
        String[] nameArr = getNames();
        for (String name : nameArr) {
            System.out.println(name + "의 배팅 금액을 입력해주세요.");
            double bettingMoney = getBettingMoney();
            players.add(new Player(name, bettingMoney));
        }

        Collections.shuffle(cards);
        System.out.printf("딜러와 %s에게 2장의 카드를 나누었습니다.", String.join(",", nameArr));
        deal(dealer);
        for (Player player : players) {
            deal(player);
            deal(player);
        }
    }

    public String[] getNames() {
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

    public void deal(Player player) {
        Card card = cards.pop();
        player.addCard(card);
    }

    public void deal(Dealer dealer) {
        Card card = cards.pop();
        dealer.addCard(card);
    }
}
