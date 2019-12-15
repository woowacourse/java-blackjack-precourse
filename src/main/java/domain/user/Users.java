package domain.user;

import domain.card.Card;
import domain.card.RandomCardFactory;
import domain.view.InputUtil;
import domain.view.OutputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Users {
    private static List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public static List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public static Users initUsers(String[] names) {
        List<User> userList = new ArrayList<>();
        userList.add(new Dealer());
        for (String name : names) {
            // TODO : OutputUtil과의 의존성이 생기는데... 더 나은 방법이 없나 고민해보기
            OutputUtil.printBettingMoneyDemand(name);
            userList.add(new Player(name, InputUtil.inputBettingMoney()));
        }
        return new Users(userList);
    }

    public static void receiveBeginningCard(List<Card> cards) {
        users.forEach(user -> {
            user.addCard(RandomCardFactory.create(cards));
            user.addCard(RandomCardFactory.create(cards));
        });
    }
}
