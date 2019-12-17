package system;

import domain.user.Dealer;
import domain.user.Player;
import view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static view.InputView.inputChoiceGetCard;
import static view.OutputView.*;

public class BlackjackSystem {
    private static int DEALER_GET_CARD_CONDITION = 16;
    private static int PLAYER_GET_CARD_CONDITION = 21;
    private static int BUST_CONDITION = 21;

    private List<Player> playerList = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private SettingSystem settingSystem = new SettingSystem(dealer, playerList);
    private HashMap<String, Integer> resultMoney = new HashMap<>();

    private char answer;
    private int dealerMoney = 0;

    public void run() {
        settingSystem.setGame();

        printInitStatus();
        for (Player p : playerList) {
            AskPlayerToGetCard(p);
        }
        getCardDealerIfAvailable();
        printUserStatus();
        setResultMoney();
        printResultMoney();
    }

    private void printInitStatus() {
        printInitDistributionMessage(playerList);
        printCardStatus(dealer);
        for (Player p : playerList) {
            printCardStatus(p);
        }
        System.out.println();
    }

    private void getCardDealerIfAvailable() {
        while (isAvailableGetCard(dealer)) {
            settingSystem.giveCard(dealer);
            printDealerGetCardMessage();
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
        return p.isSumUnderCondition(PLAYER_GET_CARD_CONDITION) && isHit();
    }

    private boolean isAvailableGetCard(Dealer d) {
        return d.isSumUnderCondition(DEALER_GET_CARD_CONDITION);
    }

    private void choiceGetCard(Player player) {
        answer = inputChoiceGetCard(player);
        if (isStay()) return;
        settingSystem.giveCard(player);
        printCardStatus(player);
    }

    private boolean isHit() {
        return answer == 'y';
    }

    private boolean isStay() {
        return answer == 'n';
    }

    private void printUserStatus() {
        printResultStatus(dealer);
        for (Player player : playerList) {
            printResultStatus(player);
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
