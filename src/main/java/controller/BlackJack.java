package controller;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Gamers;
import domain.user.Player;
import view.Input;
import view.Output;

import java.util.List;
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
        initGameWithCard(); //TODO 나중에 phase 단위 로 메소드 이름 변경
        moreCardsPhase();
        dealerPhase();
        resultPhase();
    }

    public void initGameWithCard() {
        Output.showInitCardUi(gamers.nameString());
        gamers.initPlayersCards(deck);
        Output.showGamersInfo(gamers);
    }

    public void moreCardsPhase() {
        gamers.moreCard(deck);
        gamers.findDealer().dealerShowHiddenCard(deck);
    }

    public void dealerPhase() {
        gamers.findDealer().dealerMoreCard(deck);
    }

    public void resultPhase() {
        Output.showGamersResult(gamers);
    }
}
