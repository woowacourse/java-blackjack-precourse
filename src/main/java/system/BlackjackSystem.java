package system;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;
import view.OutputView;

import java.util.*;

public class BlackjackSystem {
    private static int CARD_COUNT = 52;
    private static int DISTRIBUTE_CARD_COUNT = 2;
    private static int DEALER_GET_CARD_CONDITION = 16;
    private static int PLAYER_GET_CARD_CONDITION = 21;
    private static int BUST_CONDITION = 21;

    private List<Player> playerList = new ArrayList<>();
    private List<Card> cardList = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private int remainCardMount = CARD_COUNT;
    private char answer;
    private int dealerMoney = 0;
    private HashMap<String, Integer> resultMoney = new HashMap<>();

    public void run() {
        setGame();
        distributeCard();
        printInitStatus();
        for (Player p : playerList) {
            AskPlayerToGetCard(p);
        }
        getCardDealerIfAvailable();
        printResultStatus();
        setResultMoney();
        printResultMoney();
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

    private void giveCard(Player player) {
        int randomNumber = getRandomNumber(remainCardMount--);
        player.addCard(cardList.get(randomNumber));
        cardList.remove(randomNumber);
    }

    private void giveCard(List<Player> playerList) {
        for (Player p : playerList) {
            giveCard(p);
        }
    }

    private int getRandomNumber(int range) {
        return (int) (Math.random() * range);
    }

    private void printInitStatus() {
        OutputView.printInitDistributionMessage(playerList);
        OutputView.printCardStatus(dealer);
        for (Player p : playerList) {
            OutputView.printCardStatus(p);
        }
        System.out.println();
    }

    private void getCardDealerIfAvailable() {
        while (isAvailableGetCard(dealer)) {
            giveCard(dealer);
            OutputView.printDealerGetCardMessage();
        }
    }

    private void AskPlayerToGetCard(Player player) {
        answer = 'y';
        while (isAvailableGetCard(player)) {
            choiceGetCard(player);
        }
        System.out.println();
    }

    private boolean isAvailableGetCard(Player p) {
        return p.isSumUnderCondition(PLAYER_GET_CARD_CONDITION) && answer == 'y';
    }

    private boolean isAvailableGetCard(Dealer d) {
        return d.isSumUnderCondition(DEALER_GET_CARD_CONDITION);
    }

    private void choiceGetCard(Player player) {
        answer = InputView.inputChoiceGetCard(player);
        if (answer == 'n') return;
        giveCard(player);
        OutputView.printCardStatus(player);
    }

    private void printResultStatus() {
        OutputView.printResultStatus(dealer);
        for (Player player : playerList) {
            OutputView.printResultStatus(player);
        }
    }

    private void setResultMoney() {
        if (isDealerBust()) {
            rewardAllPlayer();
            return;
        }
        for (Player player : playerList) {
            rewardDealerIfWin(player);
            rewardPlayerIfWin(player);
            rewardNothingIfDraw(player);
        }
    }

    private boolean isDealerBust() {
        return dealer.getSumScore() > BUST_CONDITION;
    }

    private void rewardAllPlayer() {
        for (Player player : playerList) {
            resultMoney.put(player.getName(), (int) player.getBettingMoney());
        }
    }

    private void rewardDealerIfWin(Player player) {
        if (player.getSumScore() < dealer.getSumScore()) {
            resultMoney.put(player.getName(), (int) -player.getBettingMoney());
            dealerMoney += player.getBettingMoney();
        }
    }

    private void rewardPlayerIfWin(Player player) {
        if (player.getSumScore() > dealer.getSumScore()) {
            resultMoney.put(player.getName(), (int) player.getBettingMoney());
            dealerMoney -= player.getBettingMoney();
        }
    }

    private void rewardNothingIfDraw(Player player) {
        if (player.getSumScore() == dealer.getSumScore()) {
            resultMoney.put(player.getName(), 0);
        }
    }

    private void printResultMoney() {
        OutputView.printResultMoney(dealerMoney);
        for (Map.Entry<String, Integer> e : resultMoney.entrySet()) {
            OutputView.printResultMoney(e.getKey(), e.getValue());
        }
    }
}
