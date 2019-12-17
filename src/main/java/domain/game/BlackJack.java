package domain.game;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack {
    private static final String PROMPT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PROMPT_BETTING_MONEY = "의 베팅 금액은?";
    private static final String PROMPT_NEW_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String ERROR_NEW_CARD = "소문자 y와 n중에 선택해주세요.";
    private static final String MESSAGE_DEALER_HIT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        Players players = new Players(createPlayers(receiveInput(PROMPT_PLAYER_NAMES)));

        Deck deck = new Deck();
        deck.shuffle();

        dealCards(dealer, players, deck);

        // checkpoint 1. 중간점검
        // 점수 계산
        // 아직까지 죽는 유저는 없음
        // 블랙잭 확인
        // 돈 계산
        // 결과 출력
        printCards(dealer, players);

        players = hitPlayers(players, deck);
        hitDealer(dealer, deck);

        // checkpoint 2. 여기까지 오면 게임 종료 조건 만족함
        // 점수 계산
        // 생존자 확인
        // 생존자 중 승자 확인
        // 돈 계산
        // 결과 출력
        printCards(dealer, players);
    }

    private static Players hitPlayers(Players players, Deck deck) {
        List<Player> modifiedPlayers = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            hitPlayer(player, deck);
            modifiedPlayers.add(player);
        }
        return new Players(modifiedPlayers);
    }

    private static void hitPlayer(Player player, Deck deck) {
        while(shouldHit(player)) {
            player.addCard(deck.pop());
            System.out.println(player.getCardInfo());
        }
    }

    private static boolean shouldHit(Player player) {
        if (isAboveTarget(player.getScore())) {
            return false;
        }
        return makeValidChoice(player, PROMPT_NEW_CARD, ERROR_NEW_CARD);
    }

    private static boolean makeValidChoice(Player player, String prompt, String errorMessage) {
        String choice = receiveInput(player.getName() + prompt);
        while (!isValidChoice(choice)) {
            System.out.println(errorMessage);
            choice = receiveInput(player.getName() + prompt);
        }
        return choice.equals("y");
    }

    private static boolean isValidChoice(String choice) {
        return choice.equals("y") || choice.equals("n");
    }

    private static void hitDealer(Dealer dealer, Deck deck) {
        if (!isAboveThreshold(dealer.getScore())) {
            dealer.addCard(deck.pop());
            System.out.println(MESSAGE_DEALER_HIT);
        }
    }

    public static boolean isAboveTarget(int userScore) {
        return userScore >  Constant.TARGET.getScore();
    }

    public static boolean isAboveThreshold(int userScore) {
        return userScore >  Constant.DEALER_HIT.getScore();
    }

    private static void printCards(Dealer dealer, Players players) {
        System.out.println(dealer.getCardInfo());
        players.getCardInfo().forEach(System.out::println);
    }

    private static void dealCards(Dealer dealer, Players players, Deck deck) {
        dealer.addCard(deck.pop());
        dealer.addCard(deck.pop());
        players.addCards(deck);
        players.addCards(deck);
    }

    private static List<Player> createPlayers(String playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName: split(playerNames)) {
            Positive bettingMoney = new Positive(receiveInput(playerName + PROMPT_BETTING_MONEY));
            players.add(new Player(playerName, bettingMoney.getNumber()));
        }
        return players;
    }

    private static String receiveInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private static String[] split(String text) {
        return text.split(",");
    }
}