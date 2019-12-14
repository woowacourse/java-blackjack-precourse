import java.util.ArrayList;
import java.util.List;

import domain.game.Deck;
import domain.game.Stake;
import domain.user.Contender;
import domain.user.Player;
import ui.Input;

public class Game {
    private Input input = new Input();
    private Deck deck = new Deck();
    private List<Contender> contenders = new ArrayList<>();
    private Stake stake;

    public void play() {
        addPlayers();
    }

    private void addPlayers() {
        for (String name : input.getNames()) {
            double bettingMoney = input.getMoney(name);
            contenders.add(new Player(name, bettingMoney));
        }
        stake = new Stake(contenders);
    }


}
