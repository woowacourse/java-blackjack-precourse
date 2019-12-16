package domain.user;

import domain.BenefitMultipleType;
import domain.outcome.Outcome;
import domain.outcome.Outcomes;
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

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public Users initUsers(String[] names) {
        List<User> userList = new ArrayList<>();
        userList.add(new Dealer());
        for (String name : names) {
            // TODO : OutputUtil과의 의존성이 생기는데... 더 나은 방법이 없나 고민해보기
            OutputUtil.printBettingMoneyDemand(name);
            userList.add(new Player(name, InputUtil.inputBettingMoney()));
        }
        return new Users(userList);
    }

    public void receiveBeginningCard() {
        users.forEach(user -> {
            user.addRandomCard();
            user.addRandomCard();
        });
    }

    public void startAddCardQuestion() {
        users.stream()
                .filter(user -> user.isPlayer())
                .forEach(player -> {
                    ((Player) player).startAddCardLoop();
                });
    }

    public void decideOutcome(int dealderScore, Outcomes outcomes) {
        decideWinner(dealderScore, outcomes);
        decideLoswer(dealderScore, outcomes);
        decideExcessLoswer(outcomes);
    }

    public void addOutcomes(Player player, Outcomes outcomes, Double benefit) {
        outcomes.addOutcomes(
                new Outcome(player.getName(),
                        benefit));
    }

    private void decideWinner(int dealerScore, Outcomes outcomes) {
        users.stream()
                .filter(User::isPlayer)
                .map(player -> (Player) player)
                .filter(player -> player.isWinBy(dealerScore))
                .filter(player -> !player.checkExcess())
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(player -> {
                    addOutcomes(player, outcomes,
                            player.getBenefit(BenefitMultipleType.USER_WIN));
                });
    }

    private void decideExcessLoswer(Outcomes outcomes) {
        users.stream()
                .filter(User::isPlayer)
                .map(player -> (Player) player)
                .filter(player -> player.checkExcess())
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(player -> {
                    addOutcomes(player, outcomes,
                            player.getBenefit(BenefitMultipleType.USER_LOSE));
                });
    }

    private void decideLoswer(int dealerScore, Outcomes outcomes) {
        users.stream()
                .filter(User::isPlayer)
                .map(player -> (Player) player)
                .filter(player -> !player.isWinBy(dealerScore))
                .filter(player -> !player.checkExcess())
                .filter(player -> !outcomes.isHavePlayer(player.getName()))
                .forEach(player -> {
                    addOutcomes(player, outcomes,
                            player.getBenefit(BenefitMultipleType.USER_LOSE));
                });
    }
}
