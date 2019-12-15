package controller;

import java.util.ArrayList;
import deck.CardDeck;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import io.InputSystem;
import io.OutputSystem;

public class GameController {

    final static int MAXIUM_VALUE = 21;
    final static int DEALER_MAXIUMM_VALUE = 16;

    private CardDeck cardDeck;
    private Dealer dealer;
    private ArrayList<Player> players;
    private InputSystem inputSystem;

    public GameController() {
        players = new ArrayList<>();
        dealer = new Dealer();
        inputSystem = new InputSystem();
        cardDeck = new CardDeck(CardFactory.create());
    }

    public void initGame() {
        OutputSystem.printGetName();
        String names[] = commaNameSlice(inputSystem.inputUserName());
        for (int i = 0; i < names.length; i++) {
            OutputSystem.printBettingPrice(names[i]);
            players.add(new Player(names[i], inputSystem.inputBettingPirce()));
        }
    }

    public void gameStart() {
        OutputSystem.printGetTwoCards(players);
        drawTwoCard();
        for (int i = 0; i < players.size(); i++) {
            drawPlayerCard(i);
        }
        drawDealerCard();
    }

    public void gameResult() {
        OutputSystem.printResultValue(dealer, players);
        OutputSystem.printResultMoney(dealer, players);
    }

    private String[] commaNameSlice(String names) {
        return names.split(",");
    }

    private void drawPlayerCard(int playerIdx) {
        boolean answer = false;
        if (players.get(playerIdx).getTotalNumber() < MAXIUM_VALUE) {
            OutputSystem.printUserGetCard(players.get(playerIdx));
            answer = inputSystem.inputBettingAnswer();
        }
        if (answer) {
            players.get(playerIdx).addCard(cardDeck.drawCard());
            drawPlayerCard(playerIdx);
        }
    }

    private void drawDealerCard() {
        if (dealer.getTotalNumber() <= DEALER_MAXIUMM_VALUE) {
            OutputSystem.printDealerGetCard();
            dealer.addCard(cardDeck.drawCard());
            drawDealerCard();
        }
    }

    private void drawTwoCard() {
        for (int i = 0; i < 2; i++) {
            dealer.addCard(cardDeck.drawCard());
            playersDrawTwoCard(cardDeck.drawCard());
        }
    }

    private void playersDrawTwoCard(Card card) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).addCard(card);
        }
    }
}