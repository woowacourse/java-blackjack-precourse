package computer;

import domain.card.CardsOnGame;
import domain.user.Dealer;
import domain.user.EntryManager;
import domain.user.Player;
import ui.Input;
import ui.Output;

import java.util.stream.Collectors;

public class Controller {
    private CardsOnGame gameCards;
    private EntryManager players;

    public Controller() {
        gameCards = new CardsOnGame();
        players = new EntryManager(new Input().getPlayerEntry().stream()
                .map(x -> new Player(x, new Input().getBettingMoney(x)))
                .collect(Collectors.toList()));
        players.getPlayers().add(0, new Dealer());
    }

    public void startGame() {
        initPhase();
        hitPhase();
        dealerPhase();
    }

    public void initPhase() {
        Output.displayForInitInformation(players.getPlayers()
                .stream().map(Player::getName)
                .collect(Collectors.joining(", ")));
        players.initPlayerCard(gameCards);
        Output.displayEntryInfo(players);
    }

    public void hitPhase() {
        players.hitCard(gameCards);
        players.getDealer().addCard(gameCards.pickUpCard());
    }

    public void dealerPhase() {
        if (!players.getDealer().isBlackJack()) {
            players.getDealer().hitUntilStay(gameCards);
        }
    }
}
