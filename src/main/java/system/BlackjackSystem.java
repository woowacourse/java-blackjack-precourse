package system;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BlackjackSystem {
    private static int CARD_COUNT = 52;
    private static int DISTRIBUTE_CARD_COUNT = 2;

    private List<Player> playerList = new ArrayList<>();
    private List<Card> cardList = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private int remainCardMount = CARD_COUNT;

    public void run() {
        setGame();
        distributeCard();
    }

    private void setGame() {
        String names = InputView.inputPlayerName();
        StringTokenizer st = splitPlayerName(names);
        setPlayer(st);
    }

    private StringTokenizer splitPlayerName(String names) {
        return new StringTokenizer(names, ",");
    }

    private void setPlayer(StringTokenizer st) {
        while (st.hasMoreTokens()) {
            String playerName = st.nextToken();
            double bettingMoney = InputView.inputPlayerMoney(playerName);
            addPlayer(new Player(playerName, bettingMoney));
        }
    }

    private void addPlayer(Player player) {
        playerList.add(player);
    }
    
    private void distributeCard() {
        cardList.addAll(CardFactory.create());
        for (int i = 0; i < DISTRIBUTE_CARD_COUNT; i++) {
            giveCard(dealer);
            giveCard(playerList);
        }
    }

    private void giveCard(Dealer dealer) {
        int randomNumber = getRandomNumber(remainCardMount--);
        dealer.addCard(cardList.get(randomNumber));
        cardList.remove(randomNumber);
    }

    private void giveCard(List<Player> playerList) {
        for (Player p : playerList) {
            int randomNumber = getRandomNumber(remainCardMount--);
            p.addCard(cardList.get(randomNumber));
            cardList.remove(randomNumber);
        }
    }

    private int getRandomNumber(int range) {
        return (int) (Math.random() * range);
    }
}
