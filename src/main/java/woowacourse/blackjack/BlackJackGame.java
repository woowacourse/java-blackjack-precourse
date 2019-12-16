package woowacourse.blackjack;

import java.util.List;
import java.util.ArrayList;

import domain.user.Dealer;
import domain.user.Player;
import domain.card.CardFactory;
import domain.card.Card;

public class BlackJackGame {
    private Input input = new Input();
    private Output output = new Output();
    private List<Player> players = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private List<Card> cards =  CardFactory.create();
    private List<Card> getCardsList = new ArrayList<>();

    public void runGame() {
        this.inputNameAndBettingMoney();
        this.getFirstCardsAllPeople();
        this.output.printPlayersAndDealerCards(this.dealer, this.players);
        this.checkHit();
//        this.printCardsResult();
//        this.gameResult();
    }

    private void inputNameAndBettingMoney() {
        List<String> playerNames = this.input.getPlayerNames();
        this.getBettingMoney(playerNames);
    }

    private void getBettingMoney(List<String> playerNames) {
        System.out.println();
        for (String playerName : playerNames) {
            this.players.add(this.setPlayer(playerName, this.input.getPlayerBettingMoney(playerName)));
            System.out.println();
        }
    }

    private Player setPlayer(String playerName, double bettingMoney) {
        return new Player(playerName, bettingMoney);
    }

    private void getFirstCardsAllPeople() {
        for (Player player: this.players) {
            player.getFirstCards(this.cards, this.getCardsList);
        }
        this.dealer.getFirstCards(this.cards, this.getCardsList);
    }

    private void checkHit() {
        for (Player player: this.players) {
            this.checkHitPlayers(player);
        }
        this.checkHitDealer();
    }

    private void checkHitPlayers(Player player) {
        boolean booleanChoice = true;
        boolean booleanScore = player.getBooleanSumScore();
        while (booleanScore && booleanChoice){
            booleanChoice = choiceCardYesOrNo(player);
            booleanScore = player.getBooleanSumScore();
            this.output.printPlayerCards(player);
        }
        System.out.println();
    }

    private boolean choiceCardYesOrNo(Player player) {
        String choice = this.input.getYesOrNo(player);
        if (choice.equals("y")) {
            player.addCard(player.getRandomCard(this.cards, this.getCardsList));
            return true;
        }
        return false;
    }

    private void checkHitDealer() {
        if (this.dealer.isHitDealerCard()) {
            this.dealer.addCard(this.dealer.getRandomCard(this.cards, this.getCardsList));
            this.output.printAddDealerCard();
        }
    }
}