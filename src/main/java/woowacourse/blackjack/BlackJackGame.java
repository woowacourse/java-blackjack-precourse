package woowacourse.blackjack;

import java.util.List;
import java.util.ArrayList;

import domain.user.Dealer;
import domain.user.Player;
import domain.card.CardFactory;
import domain.card.Card;

public class BlackJackGame {
    private final Input input = new Input();
    private final Output output = new Output();
    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();
    private final List<Card> cards =  CardFactory.create();
    private final List<Card> getCardsList = new ArrayList<>();

    public void runGame() {
        this.inputNameAndBettingMoney();
        this.getFirstCardsAllPeople();
        this.output.printPlayersAndDealerCards(this.dealer, this.players);
        if (this.isBlackJack()) {
            return;
        }
        this.checkHit();
        this.output.printSumScore(this.dealer, this.players);
        this.output.printFinalRevenue(this.dealer, this.players);
    }

    private void inputNameAndBettingMoney() {
        List<String> playerNames = this.input.getPlayerNames();
        this.getBettingMoney(playerNames);
    }

    private void getBettingMoney(List<String> playerNames) {
        for (String playerName : playerNames) {
            this.players.add(new Player(playerName, this.input.getPlayerBettingMoney(playerName)));
        }
    }

    private void getFirstCardsAllPeople() {
        for (Player player: this.players) {
            player.getFirstCards(this.cards, this.getCardsList);
        }
        this.dealer.getFirstCards(this.cards, this.getCardsList);
    }

    private boolean isBlackJack() {
        if (this.output.isBlackJack(this.dealer, this.players)) {
            this.output.printSumScore(this.dealer, this.players);
            this.output.printBlackJack(this.dealer, this.players);
            return true;
        }
        return false;
    }

    private void checkHit() {
        for (Player player: this.players) {
            this.checkHitPlayer(player);
        }
        this.checkHitDealer();
    }

    private void checkHitPlayer(Player player) {
        while (this.isHitPlayer(player)) {
            this.output.printPlayerCards(player);
        }
        System.out.println();
    }

    private boolean isHitPlayer(Player player) {
        return player.isAbleHitPlayer() && this.choiceCardYesOrNo(player);
    }

    private boolean choiceCardYesOrNo(Player player) {
        if (this.input.isYesOrNo(player)) {
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