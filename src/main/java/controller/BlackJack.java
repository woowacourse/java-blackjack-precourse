package controller;

import domain.card.Deck;
import domain.user.Player;
import view.Input;

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
        deck = new Deck();
    }

    public void start() {

    }
}
