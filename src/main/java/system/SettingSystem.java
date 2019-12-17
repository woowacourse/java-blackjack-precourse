package system;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static util.Numbers.getRandomNumber;
import static util.Strings.splitPlayerName;
import static view.InputView.inputPlayerMoney;
import static view.InputView.inputPlayerName;
import static view.OutputView.*;

public class SettingSystem {
    private static int CARD_COUNT = 52;
    private static int DISTRIBUTE_CARD_COUNT = 2;

    private List<Card> decks;
    private List<Player> playerList;
    private Dealer dealer;
    private int remainCardMount = CARD_COUNT;

    public SettingSystem(Dealer dealer, List<Player> playerList) {
        decks = new ArrayList<>();
        this.dealer = dealer;
        this.playerList = playerList;
    }

    public void setGame() {
        String names = inputPlayerName();
        StringTokenizer st = splitPlayerName(names, ",");
        setPlayer(st);
        distributeCard();
        printInitStatus();
    }

    private void setPlayer(StringTokenizer st) {
        while (st.hasMoreTokens()) {
            String playerName = st.nextToken();
            double bettingMoney = inputPlayerMoney(playerName);
            playerList.add(new Player(playerName, bettingMoney));
        }
    }


    private void distributeCard() {
        decks.addAll(CardFactory.create());
        for (int i = 0; i < DISTRIBUTE_CARD_COUNT; i++) {
            giveCard(dealer);
            giveCardToPlayer(playerList);
        }
    }

    public void giveCard(User user) {
        int randomNumber = getRandomNumber(remainCardMount--);
        user.addCard(decks.get(randomNumber));
        decks.remove(randomNumber);
    }

    private void giveCardToPlayer(List<Player> playerList) {
        for (Player p : playerList) {
            giveCard(p);
        }
    }

    private void printInitStatus() {
        printInitDistributionMessage(playerList);
        printCardStatusExceptFirst(dealer);
        for (Player p : playerList) {
            printCardStatus(p);
        }
        System.out.println();
    }
}
