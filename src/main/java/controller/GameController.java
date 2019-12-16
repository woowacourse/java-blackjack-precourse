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
        OutputSystem.printPeopleCardList(dealer, players);
        for (int i = 0; i < players.size(); i++) {
            drawPlayerCard(i);
        }
        drawDealerCard();
    }

    public void gameResult() {
        calculateMoney();
        OutputSystem.printResultValue(dealer, players);
        OutputSystem.printResultMoney(dealer, players);
    }

    private void calculateMoney() {
        if (dealer.isBlackJack()) {
            dealerBlackjackCalculate();
            return;
            // 딜러가 블랙잭이면 블랙잭인 상대 제외하고 모두 돈을 걷는다.
        }
        if (dealer.getTotalNumber() > MAXIUM_VALUE) {
            dealerBurstCalculate();
            return;
            // 딜러가 버스터라면 패가 21이하인 사람에게 모두 돈을 준다. + 블랙잭 1.5배
        }
        normalCalculate();
    }

    private void dealerBlackjackCalculate() {
        players.stream().filter(x->x.isBlackJack());
    }

    private void dealerBurstCalculate() {

    }

    private void normalCalculate() {

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
            OutputSystem.printUserCardList(players.get(playerIdx));
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
            playersDrawTwoCard();
        }
    }

    private void playersDrawTwoCard() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).addCard(cardDeck.drawCard());
        }
    }
}