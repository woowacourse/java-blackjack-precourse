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

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        Players players = new Players(createPlayers(receiveInput(PROMPT_PLAYER_NAMES)));

        Deck deck = new Deck();
        deck.shuffle();

        dealCards(dealer, players, deck);

        System.out.println(dealer.getCardInfo());
        players.getCardInfo().forEach(System.out::println);

        System.out.println(dealer.sumCardScores());
        players.sumCardScores().forEach(System.out::println);
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