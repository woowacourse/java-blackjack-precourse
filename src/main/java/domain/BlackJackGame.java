package domain;

import domain.user.Users;
import domain.view.InputUtil;
import domain.view.OutputUtil;

public class BlackJackGame {
    public static void start() {
        initGameSetting();
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
        users.printUsersCard();
    }
}
