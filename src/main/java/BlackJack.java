import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import domain.Rule;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import utils.InputHandler;
import view.InputView;
import view.OutputView;

public class BlackJack {
    private static final int FIRST_PLAYER_CARD_COUNTS = 2;

    private ArrayList<Player> players = new ArrayList<>();
    private Stack<Card> deck = new Stack<>();
    private Dealer dealer = new Dealer();

    void play() {
        setFirstState();
        OutputView.printFirstDistributionMessage(players);
        OutputView.printCardsState(dealer, players);
        for (Player player : players) {
            runAddCardPhase(player);
        }
    }

    private void setFirstState() {
        setPlayers();
        setDeck();
        distributeCards();
    }

    private void setPlayers() {
        for (String playerName : InputHandler.splitByComma(InputView.playerNames())) {
            int bettingMoney = InputView.bettingMoney(playerName);
            Player player = new Player(playerName, bettingMoney);
            players.add(player);
        }
    }

    private void setDeck() {
        List<Card> defaultCards = CardFactory.create();
        List<Card> cards = new ArrayList<>(defaultCards);
        Collections.shuffle(cards);
        for (Card card : cards) {
            deck.push(card);
        }
    }

    private void distributeCards() {
        for (int i = 0; i < FIRST_PLAYER_CARD_COUNTS; i++) {
            addCard(dealer, deck.pop());
            distributeCardsToPlayers();
        }
    }

    private void addCard(User user, Card card) {
        user.addCard(card);
        Rule.setScore(user, card);
    }

    private void distributeCardsToPlayers() {
        for (Player player : players) {
            addCard(player, deck.pop());
        }
    }

    private void runAddCardPhase(Player player) {
        boolean isStop = false;
        while (Rule.canDrawMore(player) && !isStop) {
            isStop = getIsStop(InputView.playerIntent(player), player);
            OutputView.printPlayerCardState(player);
        }
    }

    private boolean getIsStop(String playerIntent, Player player) {
        if (playerIntent.equals("y")) {
            addCard(player, deck.pop());
            return false;
        }
        return true;
    }
}
