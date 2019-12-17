package application.domain.game;

import application.domain.user.User;
import application.domain.user.Users;
import application.view.Output;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private final Users users;
    private final Map<User, Double> result = new HashMap<>();

    public Result(Users users) {
        this.users = users;
    }

    public void calculateAndPrintResult() {
        calculateMoney();
        Output.printMoneyData(result);
    }

    private void calculateMoney() {

    }
}
