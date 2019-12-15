package domain.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsersFactory {

    public static List<User> create(HashMap<String, Double> playerProperties) {
        List<User> users = new ArrayList<>();
        users.addAll(createPlayers(playerProperties));
        users.add(createDealer());
        return users;
    }

    private static List<User> createPlayers(HashMap<String, Double> playerProperties) {
        List<User> users = new ArrayList<>();
        for(String name : playerProperties.keySet()) {
            Double bettingMoney = playerProperties.get(name);
            users.add(Player.fromNameAndBettingMoney(name, bettingMoney));
        }
        return users;
    }

    private static User createDealer() {
        return Dealer.create();
    }
}
