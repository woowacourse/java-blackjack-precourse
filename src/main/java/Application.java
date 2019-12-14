import java.util.ArrayList;
import java.util.List;

import domain.game.Deck;
import domain.game.Stake;
import domain.user.Contender;
import domain.user.Player;
import ui.Input;

public class Application {
    public static void main(String[] args) {
        Input input = new Input();
        Deck deck = new Deck();
        List<Contender> contenders = new ArrayList<>();
        String[] names = input.getNames();
        for (String name : names) {
            double bettingMoney = input.getMoney(name);
            contenders.add(new Player(name, bettingMoney));
        }
        Stake stake = new Stake(contenders);
    }
}
