import domain.card.Score;
import domain.outcome.OutcomeType;
import domain.outcome.Outcomes;
import domain.user.Player;
import domain.user.Users;
import view.InputUtil;
import view.OutputUtil;

class BlackJackGame {
    private static boolean gameEndFlag = false;

    static void start() {
        Users users = initGameSetting();
        Outcomes outcomes = new Outcomes();
        checkInitBlackJack(users, outcomes);
        if (!gameEndFlag) {
            startDecideCardAddLoop(users, outcomes);
        }
        decideOutcome(users, outcomes);
    }

    private static void checkInitBlackJack(Users users, Outcomes outcomes) {
        if (users.getDealer().getCards().calcurateScore().isBlackJack()) {
            calculateInitBlackJackPlayer(users, true, outcomes);
            gameEndFlag = true;
            return;
        }
        calculateInitBlackJackPlayer(users, false, outcomes);
    }

    private static void calculateInitBlackJackPlayer(
            Users users,
            boolean isDealerBlackJack,
            Outcomes outcomes
    ) {
        users.getPlayer()
                .filter(player -> player.calcurateScore().isBlackJack() == true)
                .forEach(
                        player -> addOutcomes(player, isDealerBlackJack, outcomes)
                );
    }

    // 매개변수 네개.
    private static void addOutcomes(
            Player player,
            boolean isDealerBlackJack,
            Outcomes outcomes
    ) {
        outcomes.addPlayerOutcomes(
                player.getName(),
                player.getBettingMoney(),
                OutcomeType.calcurateBlackJackBenefit(isDealerBlackJack));
    }

    private static Users initGameSetting() {
        Users users = inputUserInfo();
        devideCard(users);
        return users;
    }

    private static Users inputUserInfo() {
        OutputUtil.printUsersNameDemand();
        String playerNames = InputUtil.inputName();
        return Users.initUsers(playerNames);
    }

    private static void devideCard(Users users) {
        OutputUtil.printDevideMessage(users.getUsersName());
        users.receiveBeginningCard();
        users.printBeginningUserCard();
    }

    private static void startDecideCardAddLoop(Users users, Outcomes outcomes) {
        users.startAddCardQuestion(outcomes);
        users.printFinalOutput();
    }

    private static Score getDealerScore(Users users) {
        return users.getDealer().getCards().calcurateScore();
    }

    private static void decideOutcome(Users users, Outcomes outcomes) {
        users.decideWinOrLose(getDealerScore(users), outcomes);
        outcomes.addDealerOutcome();
        OutputUtil.printOutcomes(outcomes);
    }
}
