package controller;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Gamers;
import domain.user.Player;
import view.Input;
import view.Output;

import java.util.stream.Collectors;

public class BlackJack {
    private Gamers gamers;
    private Deck deck;

    public BlackJack() {
        gamers = new Gamers(new Input().asGamers().stream()
                .map(x -> new Player(x, new Input().asBettingMoney(x)))
                .collect(Collectors.toList()));
        gamers.getPlayers().add(new Dealer());
        deck = new Deck();
    }

    public void start() {
        initCardPhase();
        moreCardsPhase();
        dealerPhase();
        resultPhase();
        new StakeManager(gamers).start();
    }

    private void initCardPhase() {
        Output.showInitCardUi(gamers.nameString());
        gamers.initPlayersCards(deck);
        Output.showGamersInfo(gamers);
    }

    private void moreCardsPhase() {
        gamers.moreCard(deck);
        gamers.findDealer().dealerShowHiddenCard(deck);
    }

    private void dealerPhase() {
        if (!gamers.findDealer().isBlackJack()) {
            gamers.findDealer().dealerMoreCard(deck);
        }
    }

    private void resultPhase() {
        Output.showGamersResult(gamers);
    }
}
