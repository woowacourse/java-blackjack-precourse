package system;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

import static view.InputView.inputChoiceGetCard;
import static view.OutputView.printCardStatus;
import static view.OutputView.printDealerGetCardMessage;

public class BlackjackSystem {
    private static int DEALER_GET_CARD_CONDITION = 16;
    private static int PLAYER_GET_CARD_CONDITION = 21;

    private List<Player> playerList = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private SettingSystem settingSystem = new SettingSystem(dealer, playerList);
    private char answer;

    public void run() {
        settingSystem.setGame();

        for (Player p : playerList) {
            AskPlayerToGetCard(p);
        }
        getCardDealerIfAvailable();

        ResultSystem resultSystem = new ResultSystem(dealer, playerList);
        resultSystem.getResult();
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
}
