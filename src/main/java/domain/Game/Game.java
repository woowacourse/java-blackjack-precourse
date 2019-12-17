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

public class Game {

    private final Input input = new Input();
    private final Dealer dealer = new Dealer();
    private final Output output = new Output(dealer);
    private final List<Player> players = new ArrayList<>();
    private final List<Card> cardDeck = new ArrayList<>(CardFactory.create());
    private Map<String, Double> bettingMoneyMap = new LinkedHashMap<>();
    private List<Player> winners = new ArrayList<>();

    public void Play() {
        this.playerObjectCreate();
        this.startCardDraw();
        if (this.startBlackJack()) {
            this.addCardDraw();
            output.startCardState(players);
            output.finalCardResult(players);
        }
        this.finalResult();
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
        bettingMoneyMap.put("딜러", totalBettingMoney);
        players.stream().forEach(player -> bettingMoneyMap.put(player.getName(), 0.0));
    }

    public void startCardDraw(){
        for (int i = 0; i < 2; i++) {
            dealer.cardDraw(cardDeck);
            players.stream().forEach(player -> player.cardDraw(cardDeck));
        }
    }

    public void playerAddCardDraw(Player player) {
        while (input.addCardDrawInput(player).equals("y")) {
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
        for (Player winner : winners) {
            Double winnerMoney = winner.getBettingMoney() * 1.5;
            bettingMoneyMap.put("딜러", bettingMoneyMap.get("딜러") - winnerMoney);
            bettingMoneyMap.put(winner.getName(), winnerMoney);
        }
        if (winners.isEmpty()) {
            return true;
        }
        return false;
    }

    public void playerWinBettingMoney() {
        for (Player winner : winners) {
            Double winnerMoney = winner.getBettingMoney() * 2;
            bettingMoneyMap.put("딜러", bettingMoneyMap.get("딜러") - winnerMoney);
            bettingMoneyMap.put(winner.getName(), winnerMoney);
        }
    }

    public boolean finalResult(){
        if(this.finalResultBlackJack()){
            return true;
        }
        return false;
    }

    public boolean finalResultBlackJack() {
        winners = players.stream().filter(player -> player.blackJack() == true).collect(Collectors.toList());
        if (!winners.isEmpty()) {
            this.playerWinBettingMoney();
            return true;
        }
        if (dealer.blackJack()) {
            return true;
        }
        return false;
    }
}
