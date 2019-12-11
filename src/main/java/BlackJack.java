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
import view.OutputView;

public class BlackJack {
    private static final int FIRST_PLAYER_CARD_COUNTS = 2;

    private ArrayList<Player> players = new ArrayList<>();
    private Stack<Card> deck = new Stack<>();
    private Dealer dealer = new Dealer();

    void play() {
        setPlayers();
        setFirstState();
        printFirstResult();
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
        List<Card> defaultCards = CardFactory.create();
        List<Card> cards = new ArrayList<>(defaultCards);
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
        OutputView.printFirstDistributionMessage(players);
    }

    private void distributeCardsToPlayers() {
        for(Player player : players) {
            player.addCard(deck.pop());
        }
    }

    private void printFirstResult() {

    }
}
