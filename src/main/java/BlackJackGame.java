import domain.outcome.Outcomes;
import domain.user.Users;
import view.InputUtil;
import view.OutputUtil;

public class BlackJackGame {
    private static boolean gameEndFlag = false;
    private static final Outcomes outcomes = new Outcomes();

    public static void start() {
        Users users = initGameSetting();
        checkInitBlackJack(users);
        if (gameEndFlag == false) {
            startDecideCardAddLoop(users);
        }
        decideOutcome(users);
    }

    private static void checkInitBlackJack(Users users) {
        if (users.getDealer().isBlackJack()) {
            calculateInitBlackJackPlayer(users, true, true);
            calculateInitBlackJackPlayer(users, false, true);
            gameEndFlag = true;
            return;
        }
        if (users.getInitBlackJackPlayer() > 0) {
            calculateInitBlackJackPlayer(users, true, false);
        }
    }

    private static void calculateInitBlackJackPlayer(Users users, boolean isPlayerBlackJack, boolean isDealerBlackJack) {
        users.getPlayer().filter(player -> player.isBlackJack() == isPlayerBlackJack)
                .forEach(player ->
                        outcomes.addOutcomes(
                                player.getName(),
                                player.calcurateBlackJackBenefit(isDealerBlackJack, isPlayerBlackJack),
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
        return Users.initUsers(playerNames);
    }

    private static void devideCard(Users users) {
        OutputUtil.printDevideMessage(users.getUsersName());
        users.receiveBeginningCard();
        users.printBeginningUserCard();
    }

    private static void startDecideCardAddLoop(Users users) {
        users.startAddCardQuestion(outcomes);
        users.printFinalOutput();
    }

    private static int getDealerScore(Users users) {
        return users.getDealer().calcurateScore();
    }

    private static void decideOutcome(Users users) {
        users.decideOutcome(getDealerScore(users), outcomes);
        outcomes.calcurateDealerBenefit();
        OutputUtil.printOutcomes(outcomes);
    }
}
