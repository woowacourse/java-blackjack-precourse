package io;

import domain.Name;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import dto.Results;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    public static final int ZERO = 0;
    private static final int INITIAL_CARD_AMOUNT = 2;

    private final Players players;
    private final Deck deck;

    private BlackJackGame( List<Player> players ) {
        this.players = new Players(new Dealer(), players);
        deck = Deck.init();
    }

    public static BlackJackGame init() {
        List<Player> players = assemblePlayer();
        for (Player player : players) {
            double bettingMoney = InputView.inputBettingMoney(player.getName());
            player.setBettingMoney(bettingMoney);
        }
        return new BlackJackGame(players);
    }

    private static List<Player> assemblePlayer() {
        List<String> names = InputView.inputNames();
        return names.stream()
                .map(p -> new Player(new Name(p)))
                .collect(Collectors.toList());
    }

    public void run() {
        spreadCard();
        if (hasMoreTurn()) {
            playingTurn();
        }
        if (players.getDealer().isBust()) {
            resultsStage();
            System.exit(0);
        }
        resultsStage();
    }

    private void spreadCard() {
        for (int i = 0; i < INITIAL_CARD_AMOUNT; i++) {
            players.addCard(deck);
        }
        OutputView.showInitialDraw(players);
    }

    private boolean hasMoreTurn() {
        return players.isNoneBlackJack();
    }

    private void playingTurn() {
        playersDrawCard();
        dealerDrawCard();
    }

    private void playersDrawCard() {
        players.getPlayers()
                .forEach(this::isHit);
    }

    private void dealerDrawCard() {
        Dealer dealer = players.getDealer();
        while (dealer.isGetMoreCard()) {
            OutputView.showDealerDraw();
            dealer.addCard(deck.drawCard());
        }

        if (dealer.isBust()) {
            OutputView.showBust();
        }
    }

    private void isHit( Player player ) {
        if (InputView.inputHitAndStay(player.getName()) && !player.isBust()) {
            player.addCard(deck.drawCard());
        }
        if (player.isBust()) {
            OutputView.showBust();
        }
    }

    private void resultsStage() {
        OutputView.showResult(players);
        Results results = new Results(players);
        OutputView.showReusltsDetail(results.getResults());
    }
}
