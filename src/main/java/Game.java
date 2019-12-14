import java.util.ArrayList;
import java.util.List;

import domain.game.Deck;
import domain.game.Stake;
import domain.user.Contender;
import domain.user.Dealer;
import domain.user.Player;
import ui.Input;

public class Game {
    private Input input = new Input();
    private Deck deck = new Deck();
    private List<Contender> contenders = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private Stake stake;

    public void play() {
        addPlayers();
        giveFirstPair();
        printContenders();
        askContenders();
        showResult();
    }

    private void addPlayers() {
        for (String name : input.getNames()) {
            double bettingMoney = input.getMoney(name);
            contenders.add(new Player(name, bettingMoney));
        }
        stake = new Stake(contenders);
    }

    private void giveFirstPair() {
        for (Contender contender : contenders) {
            contender.drawPairFrom(deck);
        }
        dealer.drawPairFrom(deck);
    }

    private void printContenders() {
        System.out.println("\n" + dealer);
        for (Contender contender : contenders) {
            System.out.println(contender);
        }
    }

    private void askPlayer(Contender contender) {
        while (!contender.isBusted() && input.wantsMoreCard(contender.getName())) {
            contender.addCard(deck.draw());
            System.out.println(contender);
        }
    }

    private void askContenders() {
        for (Contender contender : contenders) {
            askPlayer(contender);
        }
        dealer.drawOneMoreFrom(deck);
    }

    private void showResult() {
        System.out.println(dealer + " - 결과: " + dealer.getSum());
        for (Contender contender : contenders) {
            System.out.println(contender + " - 결과: " + contender.getSum());
        }
    }
}
