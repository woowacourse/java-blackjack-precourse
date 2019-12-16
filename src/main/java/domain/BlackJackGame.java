package domain;

import domain.user.Users;
import domain.view.InputUtil;
import domain.view.OutputUtil;

public class BlackJackGame {
    public static void start() {
        Users users = initGameSetting();
        startDecideCardAddLoop(users);
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

    private static void startDecideCardAddLoop(Users users) {
        users.startAddCardQuestion();
        users.printFinalOutput();
    }
}
