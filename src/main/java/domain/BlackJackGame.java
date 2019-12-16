package domain;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.outcome.Outcomes;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import domain.view.InputUtil;
import domain.view.OutputUtil;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    static boolean gameEndFlag = false;
    static final Outcomes outcomes = new Outcomes();

    public static void start() {
        Users users = initGameSetting();
        checkInitBlackJack(users);
        if (gameEndFlag == false) {
            startDecideCardAddLoop(users);
        }
        decideOutcome(users);
    }

    public static void checkInitBlackJack(Users users) {
        // 딜러가 블랙잭일 경우
        if (users.getDealer().isBlackJack()) {
            calculateInitBlackJackPlayer(users, true, BenefitMultipleType.INIT_BOTH_BLACKJACK);
            calculateInitBlackJackPlayer(users, false, BenefitMultipleType.USER_LOSE);
            gameEndFlag = true;
            return;
        }

        if (users.getInitBlackJackPlayer() > 0) {
            calculateInitBlackJackPlayer(users, true, BenefitMultipleType.INIT_USER_BLACKJACK);
        }
    }

    private static void calculateInitBlackJackPlayer(Users users, boolean playerBlackJackFlag, BenefitMultipleType multipleType) {
        users.getPlayers().filter(player -> player.isBlackJack() == playerBlackJackFlag)
                .forEach(player ->
                        outcomes.addOutcomes(
                                player.getName(),
                                player.getBenefit(multipleType),
                                player.getCards()
                        ));
    }

    private static Users initGameSetting() {
        Users users = inputUserInfo();
        devideCard(users);
        return users;
    }

    private static Users inputUserInfo() {
        OutputUtil.printUsersNameDemand();
        String[] playerNames = InputUtil.inputName();
        Users users = Users.initUsers(playerNames);
        return users;
    }

    private static void devideCard(Users users) {
        OutputUtil.printDevideMessage(users.getUsersName());
        users.receiveBeginningCard();
        users.printInitUserCard();
    }

    static void startDecideCardAddLoop(Users users) {
        users.startAddCardQuestion(outcomes);
        users.printFinalOutput();
    }

    private static int getDealerScore(Users users) {
        return users.getDealer().calcurateScore();
    }

    static void decideOutcome(Users users) {
        users.decideOutcome(getDealerScore(users), outcomes);
        outcomes.calcurateDealerBenefit();
        OutputUtil.printOutcomes(outcomes);
    }
}
