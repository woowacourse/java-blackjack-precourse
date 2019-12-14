package domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.user.Contender;
import domain.user.Dealer;

public class Discriminator {
    private List<Contender> survivors;
    private Dealer dealer;

    public Discriminator(List<Contender> contenders, Dealer dealer) {
        this.dealer = dealer;
        this.survivors = contenders
            .stream()
            .filter(Contender::notBusted)
            .sorted()
            .collect(Collectors.toList());
    }

    public List<Contender> discrimination() {
        return getWinners();
    }

    private boolean scoreEqualsToDealer(Contender survivor) {
        return survivor.getSum() == dealer.getSum();
    }

    private boolean scoreEqualsToFirst(Contender survivor) {
        return survivor.getSum() == survivors.get(0).getSum();
    }

    private List<Contender> getWinners() {
        if (dealer.isBusted()) {
            return survivors;
        }
        return getBlackJacks();
    }

    private List<Contender> getBlackJacks() {
        if (dealer.isBlackJack() || survivors.get(0).isBlackJack()) {
            return survivors.stream().filter(Contender::isBlackJack).collect(Collectors.toList());
        }
        return playerLoses();
    }

    private List<Contender> playerLoses() {
        if (survivors.get(0).getSum() < dealer.getSum()) {
            return new ArrayList<>();
        }
        return draws();
    }

    private List<Contender> draws() {
        if (survivors.get(0).getSum() == dealer.getSum()) {
            return survivors.stream().filter(this::scoreEqualsToDealer).collect(Collectors.toList());
        }
        return survivors.stream().filter(this::scoreEqualsToFirst).collect(Collectors.toList());
    }
}
