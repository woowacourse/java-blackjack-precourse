import domain.game.Deck;
import ui.Input;

public class Application {
    public static void main(String[] args) {
        Input input = new Input();
        Deck deck = new Deck();
        input.getNames();
        System.out.println(deck.draw());
    }
}
