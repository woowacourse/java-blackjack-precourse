package domain.Game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Input input = new Input();
    private final Dealer dealer = new Dealer();
    private final Output output = new Output(dealer);
    private final List<Player> players = new ArrayList<>();
    private final List<Card> cardDeck = new ArrayList<>(CardFactory.create());

    private String[] playerNames;

    public void Play() {
        this.playerObjectCreate();
        for (int i = 0; i < 2; i++) {
            dealer.cardPush(cardDeck);
            players.stream().forEach(player -> player.cardPush(cardDeck));
        }
        output.StartCardState(players);
    }

    public void playerObjectCreate() {
        playerNames = input.playerNameInput();
        for (String playerName : playerNames) {
            Double bettingMoney = input.bettingMoneyInput(playerName);
            players.add(new Player(playerName, bettingMoney));
        }
    }
}
