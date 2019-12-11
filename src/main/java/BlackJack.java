import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import utils.InputHandler;
import view.InputView;

public class BlackJack {
    private static final int FIRST_PLAYER_CARD_COUNTS = 2;

    private ArrayList<Player> players = new ArrayList<>();
    private Stack<Card> deck = new Stack<>();
    private Dealer dealer = new Dealer();

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
        distributeCards();
    }

    private void setDeck() {
        List<Card> cards = CardFactory.create();
        Collections.shuffle(cards);
        for(Card card : cards) {
            deck.push(card);
        }
    }

    private void distributeCards() {
        dealer.addCard(deck.pop());
        for(int i = 0; i < FIRST_PLAYER_CARD_COUNTS; i++) {
            distributeCardsToPlayers();
        }
    }

    private void distributeCardsToPlayers() {
        for(Player player : players) {
            player.addCard(deck.pop());
        }
    }

}
