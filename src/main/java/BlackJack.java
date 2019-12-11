import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;
import utils.InputHandler;
import view.InputView;

public class BlackJack {
    private ArrayList<Player> players = new ArrayList<>();
    private List<Card> deck = new ArrayList<>();

    void play() {
        setPlayers();
        setFirstState();
    }

    private void setPlayers() {
        for(String playerName : InputHandler.splitByComma(InputView.playerNames())) {
            int bettingMoney = InputView.bettingMoney(playerName);
            Player player = new Player(playerName, bettingMoney);
            players.add(player);
        }
    }

    private void setFirstState() {
        setDeck();
    }

    private void setDeck() {
        deck = CardFactory.create();
        Collections.shuffle(deck);
    }

}
