package controller;

import java.util.ArrayList;

import domain.user.Dealer;
import domain.user.Player;
import io.InputSystem;
import io.OutputSystem;

public class GameController {

    final static int MAXIUM_VALUE = 21;
    final static int DEALER_MAXIUMM_VALUE = 16;

    private Dealer dealer;
    private ArrayList<Player> players;
    private InputSystem inputSystem;

    public GameController() {
        players = new ArrayList<>();
        inputSystem = new InputSystem();
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
            // 카드를 뽑는다.
            drawPlayerCard(playerIdx);
        }
    }

    private void drawDealerCard() {
        if(dealer.getTotalNumber() <= DEALER_MAXIUMM_VALUE) {
            OutputSystem.printDealerGetCard();
            //카드를 뽑는다
            drawDealerCard();
        }
    }
}