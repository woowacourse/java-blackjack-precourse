package controller;

import domain.user.Player;
import view.Input;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {
    private List<Player> players;

    public BlackJack() {
        players = new Input().asGamers()
                .stream()
                .map(x ->new Player(x, new Input().asBettingMoney(x)))
                .collect(Collectors.toList());
    }

    public void start(){

    }
}
