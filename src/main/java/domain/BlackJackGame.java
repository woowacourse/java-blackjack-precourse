package domain;

import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import domain.view.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    public Users initUsers(String[] names) {
        List<User> userList = new ArrayList<>();
        for (String name: names) {
            userList.add(new Player(name, InputUtil.inputBettingMoney()));
        }
        return new Users(userList);
    }
}
