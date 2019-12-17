package controller;

import java.util.ArrayList;

import deck.CardDeck;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Result;
import io.InputSystem;
import io.OutputSystem;

public class GameController {

    static final int MAXIUM_VALUE = 21;
    static final int DEALER_MAXIUMM_VALUE = 16;

    private CardDeck cardDeck;
    private Dealer dealer;
    private ArrayList<Player> players;
    private InputSystem inputSystem;
    private Result dealerResult;
    private ArrayList<Result> playerResult;

    public GameController() {
        players = new ArrayList<>();
        dealer = new Dealer();
        inputSystem = new InputSystem();
        cardDeck = new CardDeck(CardFactory.create());
        playerResult = new ArrayList<>();
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
        if (dealer.isBlackJack()) {
            dealerBlackjackCalculate();
            return;
        }
        playerAndDealerDrawCard();
    }

    public void gameResult() {
        calculateMoney();
        OutputSystem.printResultValue(dealer, players);
        OutputSystem.printResultMoney(dealerResult, playerResult);
    }

    private void playerAndDealerDrawCard() {
        for (int i = 0; i < players.size(); i++)
            drawPlayerCard(i);
        drawDealerCard();
    }

    private void calculateMoney() {
        if (dealer.isBlackJack())
            return;
        if (dealer.getTotalNumber() > MAXIUM_VALUE) {
            dealerBurstCalculate();
            return;
        }
        normalCalculate();
    }

    private void dealerBlackjackCalculate() {
        double dealerSum = 0;
        for (Player player : players) {
            dealerSum += notBlackjackPlayerGetMoney(player);
        }
        dealerResult = new Result("dealer", dealerSum);
    }

    private double notBlackjackPlayerGetMoney(Player player) {
        if (player.isBlackJack()) {
            playerResult.add(new Result(player.getName(), 0));
            return 0;
        }
        playerResult.add(new Result(player.getName(), -player.getBettingMoney()));
        return player.getBettingMoney();
    }

    private void dealerBurstCalculate() {
        double dealerSum = 0;
        for (Player player : players) {
            dealerSum += playerBurstGetMoney(player);
        }
        dealerResult = new Result("dealer", dealerSum);
    }

    private double playerBurstGetMoney(Player player) {
        if (player.getTotalNumber() > MAXIUM_VALUE) {
            playerResult.add(new Result(player.getName(), -player.getBettingMoney()));
            return player.getBettingMoney();
        }
        playerResult.add(new Result(player.getName(), player.getBettingMoney() + player.blackJackBonus()));
        return -(player.getBettingMoney() + player.blackJackBonus());
    }

    private void normalCalculate() {
        double dealerSum = 0;
        for (Player player : players) {
            dealerSum += dealerToPlayerCompare(player);
        }
        dealerResult = new Result("dealer", dealerSum);
    }

    public double dealerToPlayerCompare(Player player) {
        if (dealer.isDealerWinner(player))
            return dealerWinnerSimulation(player);
        if (dealer.isTie(player))
            return dealerTieSimulation(player);
        return dealerLoseSimulation(player);
    }

    private double dealerWinnerSimulation(Player player) {
        playerResult.add(new Result(player.getName(), -player.getBettingMoney()));
        return player.getBettingMoney();
    }

    private double dealerTieSimulation(Player player) {
        playerResult.add(new Result(player.getName(), 0));
        return 0;
    }

    private double dealerLoseSimulation(Player player) {
        playerResult.add(new Result(player.getName(), player.getBettingMoney() + player.blackJackBonus()));
        return -(player.getBettingMoney() + player.blackJackBonus());
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
            drawCardAndAnswer(playerIdx);
        }
    }

    private void drawCardAndAnswer(int playerIdx){
        players.get(playerIdx).addCard(cardDeck.drawCard());
        OutputSystem.printUserCardList(players.get(playerIdx));
        drawPlayerCard(playerIdx);
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