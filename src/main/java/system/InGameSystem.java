package system;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

import static view.InputView.inputChoiceGetCard;
import static view.OutputView.printCardStatus;
import static view.OutputView.printDealerGetCardMessage;

public class InGameSystem {
    private static int DEALER_GET_CARD_CONDITION = 16;
    private static int PLAYER_GET_CARD_CONDITION = 21;

    private Dealer dealer;
    private List<Player> playerList;
    private SettingSystem settingSystem;
    private char answer;

    public InGameSystem(Dealer dealer, List<Player> playerList, SettingSystem settingSystem) {
        this.dealer = dealer;
        this.playerList = new ArrayList<>(playerList);
        this.settingSystem = settingSystem;
    }

    public void play() {
        for (Player p : playerList) {
            AskPlayerToGetCard(p);
        }
        getCardDealerIfAvailable();
    }

    private void getCardDealerIfAvailable() {
        System.out.println();
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
