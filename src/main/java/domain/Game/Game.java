package domain.Game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Kim SeYun
 * @ClassName : Game
 * @ClassExplanation : 실질적인 게임을 진행하는 곳
 * @Date : 2019. 12. 17
 * @Copyright (c) 2019 SeYun Kim
 */
public class Game {

    private final Input input = new Input();
    private final Dealer dealer = new Dealer();
    private final Output output = new Output(dealer);
    private final List<Player> players = new ArrayList<>();
    private final List<Card> cardDeck = new ArrayList<>(CardFactory.create());
    private final String DEALER_NAME = "딜러";
    private Map<String, Double> bettingMoneyMap = new LinkedHashMap<>();
    private List<Player> winners = new ArrayList<>();
    private List<Player> losers = new ArrayList<>();
    private int winnerScore = 0;

    public void Play() {
        this.playerObjectCreate();
        this.startCardDraw();
        if (this.startBlackJack()) {
            output.startCardState(players);
            this.addCardDraw();
            output.finalCardResult(players);
            this.finalResult();
        }
        output.bettingMoneyResult(bettingMoneyMap);
    }

    public void playerObjectCreate() {
        String[] playerNames = input.playerNameInput();
        Double totalBettingMoney = 0.0;
        for (String playerName : playerNames) {
            Double bettingMoney = input.bettingMoneyInput(playerName);
            totalBettingMoney += bettingMoney;
            players.add(new Player(playerName, bettingMoney));
        }
        this.bettingMoneyMapCreate(totalBettingMoney);
    }

    public void bettingMoneyMapCreate(double totalBettingMoney) {
        bettingMoneyMap.put(DEALER_NAME, totalBettingMoney);
        players.stream().forEach(player -> bettingMoneyMap.put(player.getName(), 0.0));
    }

    public void startCardDraw() {
        for (int i = 0; i < 2; i++) {
            dealer.cardDraw(cardDeck);
            players.stream().forEach(player -> player.cardDraw(cardDeck));
        }
    }

    public void playerAddCardDraw(Player player) {
        while (player.scoreExcess() && input.addCardDrawInput(player).equals("y")) {
            player.cardDraw(cardDeck);
            System.out.println(player.cardsToString());
        }
    }

    public void dealerAddCardDraw() {
        if (output.dealerAddCardDraw()) {
            dealer.cardDraw(cardDeck);
        }
    }

    public void addCardDraw() {
        players.stream().forEach(player -> this.playerAddCardDraw(player));
        this.dealerAddCardDraw();
    }

    public boolean startBlackJack() {
        winners = players.stream().filter(player -> player.blackJack() == true).collect(Collectors.toList());
        if (winners.isEmpty()) {
            return true;
        }
        for (Player winner : winners) {
            Double winnerMoney = winner.getBettingMoney() * 1.5;
            bettingMoneyMap.put(DEALER_NAME, bettingMoneyMap.get(DEALER_NAME) - winnerMoney);
            bettingMoneyMap.put(winner.getName(), winnerMoney);
        }
        return false;
    }

    public boolean finalResult() {
        if (this.finalResultBlackJack()) {
            return true;
        }
        if (this.dealerScoreExcess()) {
            return true;
        }
        this.finalResultNoBlackJack();
        return false;
    }

    public boolean finalResultBlackJack() {
        winners = players.stream().filter(player -> player.blackJack()).collect(Collectors.toList());
        if (!winners.isEmpty()) {
            losers = players.stream().filter(player -> !player.blackJack()).collect(Collectors.toList());
            this.playerWinBettingMoney();
            this.loserBettingMoney();
            return true;
        }
        return false;
    }

    public boolean dealerScoreExcess() {
        if (!dealer.scoreExcess()) {
            bettingMoneyMap.put(DEALER_NAME, 0.0);
            players.stream().forEach(player -> bettingMoneyMap.put(player.getName(), player.getBettingMoney()));
            return true;
        }
        return false;
    }

    public void playerWinBettingMoney() {
        for (Player winner : winners) {
            Double winnerMoney = winner.getBettingMoney() * 2;
            bettingMoneyMap.put(DEALER_NAME, bettingMoneyMap.get(DEALER_NAME) - winnerMoney);
            bettingMoneyMap.put(winner.getName(), winnerMoney);
        }
    }

    public void loserBettingMoney() {
        for (Player loser : losers) {
            bettingMoneyMap.put(loser.getName(), -loser.getBettingMoney());
        }
    }

    public void finalResultNoBlackJack() {
        if (dealer.scoreExcess()) {
            winnerScore = dealer.scoreCalculator();
        }
        players.stream().forEach(player -> this.maxScore(player));
        players.stream().forEach(player -> this.winnerFind(player));
        this.playerWinBettingMoney();
        this.loserBettingMoney();
    }

    public void maxScore(Player player) {
        if (this.winnerScore < player.scoreCalculator() && player.scoreExcess()) {
            this.winnerScore = player.scoreCalculator();
        }
    }

    public void winnerFind(Player player) {
        if (winnerScore == player.scoreCalculator()) {
            this.winners.add(player);
        }
        this.losers.add(player);
    }
}