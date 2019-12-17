package domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.user.Contender;
import domain.user.Contenders;
import domain.user.Dealer;

public class Discriminator {
    private Contenders contenders;
    private Dealer dealer;
    private Contender firstPlaced;

    public Discriminator(Contenders contenders, Dealer dealer) {
        this.dealer = dealer;
        this.contenders = contenders;
        this.firstPlaced = this.contenders.getStream().collect(Collectors.toList()).get(0);
    }

    public List<Contender> discrimination() {
        return getWinners();
    }

    private boolean scoreEqualsToDealer(Contender contender) {
        return contender.getFinalSum() == dealer.getFinalSum();
    }

    private boolean scoreEqualsToFirst(Contender contender) {
        return contender.getFinalSum() == firstPlaced.getFinalSum();
    }

    private List<Contender> getWinners() {
        if (dealer.isBusted()) {
            Stream<Contender> survivors = contenders.getStream();
            return survivors.collect(Collectors.toList());
        }
        return getBlackJacks();
    }

    private List<Contender> getBlackJacks() {
        if (dealer.isBlackJack() || firstPlaced.isBlackJack()) {
            Stream<Contender> survivors = contenders.getStream();
            return survivors.filter(Contender::isBlackJack).collect(Collectors.toList());
        }
        return playerLoses();
    }

    private List<Contender> playerLoses() {
        if (firstPlaced.getFinalSum() < dealer.getFinalSum()) {
            return new ArrayList<>();
        }
        return draws();
    }

    private List<Contender> draws() {
        Stream<Contender> survivors = contenders.getStream();
        if (firstPlaced.getFinalSum() == dealer.getFinalSum()) {
            return survivors.filter(this::scoreEqualsToDealer).collect(Collectors.toList());
        }
        return survivors.filter(this::scoreEqualsToFirst).collect(Collectors.toList());
    }
}
