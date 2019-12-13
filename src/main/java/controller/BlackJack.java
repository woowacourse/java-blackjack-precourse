package controller;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import view.Input;
import view.Output;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {
    private List<Player> players;
    private Deck deck;

    public BlackJack() {
        players = new Input().asGamers()
                .stream()
                .map(x -> new Player(x, new Input().asBettingMoney(x)))
                .collect(Collectors.toList());
        players.add(new Dealer());
        deck = new Deck();
    }

    public void start() {
        Output.showInitCardUi(players);
        players.forEach(x -> x.initCard(deck));
    }
}
