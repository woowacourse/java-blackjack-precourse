package domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.user.Contender;
import domain.user.Dealer;

public class Discriminator {
    List<Contender> survivors;
    List<Contender> blackJacks;
    Contender firstSurvivor;
    Dealer dealer;

    public Discriminator(List<Contender> contenders, Dealer dealer) {
        this.dealer = dealer;
        this.survivors = contenders
            .stream()
            .filter(Contender::notBusted)
            .sorted()
            .collect(Collectors.toList());
        this.blackJacks = this.survivors.stream().filter(Contender::isBlackJack).collect(Collectors.toList());
        this.firstSurvivor = survivors.get(0);
    }

    public List<Contender> discrimination() {
        return getWinners();
    }

    private boolean sameWithDealer(Contender survivor) {
        return survivor.getSum() == dealer.getSum();
    }

    private boolean sameWithFirst(Contender survivor) {
        return survivor.getSum() == firstSurvivor.getSum();
    }

    private List<Contender> getWinners() {
        if (dealer.isBusted()) {
            return survivors;
        }
        return getBlackJacks();
    }

    private List<Contender> getBlackJacks() {
        if (dealer.isBlackJack() || firstSurvivor.isBlackJack()) {
            return blackJacks;
        }
        return playerLoses();
    }

    private List<Contender> playerLoses() {
        if (firstSurvivor.getSum() < dealer.getSum()) {
            return new ArrayList<>();
        }
        return draws();
    }

    private List<Contender> draws() {
        if (firstSurvivor.getSum() == dealer.getSum()) {
            return survivors.stream().filter(this::sameWithDealer).collect(Collectors.toList());
        }
        return survivors.stream().filter(this::sameWithFirst).collect(Collectors.toList());
    }

    // 딜러의 중도 탈락
    //   탈락하지 않은 플레이어들은 베팅 금액을 돌려받음
    // 딜러의 블랙잭 (첫 페어 합 21)
    //   블랙잭인 플레이어는 베팅 금액을 돌려받음
    //   그 외 플레이어는 패배
    // 플레이어의 블랙잭 (첫 페어 합 21)
    //   베팅 금액의 1.5배를 받음
    //   그 외 블랙잭이 아닌 플레이어는 패배
    // 블랙잭이 없는 경우 카드 총합이 21에 가까운 순서로 1등을 선발
    //   딜러 1등: 모든 플레이어 패배
    //   플레이어 1등: 1등만 베팅 금액을 돌려받음
    //   무승부: 딜러와 점수가 같은 플레이어들은 베팅 금액을 돌려받음
}
